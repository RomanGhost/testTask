 package com.example.crptapi;

import com.example.crptapi.model.Description;
import com.example.crptapi.model.Product;

public class Main {
    public static void main(String[] args) {
        Product product = new Product();
        product.setCertificate_document("someDocument");
        product.setCertificate_document_date("someDate");
        product.setCertificate_document_number("someNumber");
        product.setOwner_inn("someOwnerInn");
        product.setProducer_inn("someProducerInn");
        product.setProduction_date("someDate");
        product.setTnved_code("someCode");
        product.setUit_code("someCode");
        product.setUitu_code("someCode");
        
        Description description = new Description("Sample description");
        System.out.println(description.getDescriptionText());
    }
}
