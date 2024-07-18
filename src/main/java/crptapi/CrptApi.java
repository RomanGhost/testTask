package com.example.crptapi;

import com.example.crptapi.model.Document;
import java.util.concurrent.ExecutorService;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor; // Импорт интерфейса Executor

public class CrptApi {

    private final HttpClient httpClient;
    private final Gson gson;
    private final Semaphore semaphore;
    private final ScheduledExecutorService scheduler;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
        this.semaphore = new Semaphore(requestLimit);
        this.scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            semaphore.release(Math.max(0, requestLimit - semaphore.availablePermits()));
        }, 0, 1, timeUnit);
    }

    public void createDocument(Document document, String signature) throws IOException, InterruptedException {
        semaphore.acquire();

        try {
            String json = gson.toJson(document);
            HttpRequest request = HttpRequest.newBuilder()
                                  .uri(URI.create("https://ismp.crpt.ru/api/v3/lk/documents/create"))
                                  .timeout(Duration.ofMinutes(1))
                                  .header("Content-Type", "application/json")
                                  .header("Signature", signature)
                                  .POST(HttpRequest.BodyPublishers.ofString(json))
                                  .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Failed to create document: " + response.body());
            }
        } finally {
            semaphore.release();
        }
    }

    public void shutdown() {
    scheduler.shutdown();
    httpClient.executor().ifPresent(exec -> {
        if (exec instanceof ExecutorService) {
            ((ExecutorService) exec).shutdown();
        }
    });
}

}
