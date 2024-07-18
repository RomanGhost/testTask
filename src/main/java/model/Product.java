package com.example.crptapi.model;

public class Product {
    private String certificateDocument;
    private String certificateDocumentDate;
    private String certificateDocumentNumber;
    private String ownerInn;
    private String producerInn;
    private String productionDate;
    private String tnvedCode;
    private String uitCode;
    private String uituCode;

    public void setCertificate_document(String certificateDocument) {
        this.certificateDocument = certificateDocument;
    }

    public void setCertificate_document_date(String certificateDocumentDate) {
        this.certificateDocumentDate = certificateDocumentDate;
    }

    public void setCertificate_document_number(String certificateDocumentNumber) {
        this.certificateDocumentNumber = certificateDocumentNumber;
    }

    public void setOwner_inn(String ownerInn) {
        this.ownerInn = ownerInn;
    }

    public void setProducer_inn(String producerInn) {
        this.producerInn = producerInn;
    }

    public void setProduction_date(String productionDate) {
        this.productionDate = productionDate;
    }

    public void setTnved_code(String tnvedCode) {
        this.tnvedCode = tnvedCode;
    }

    public void setUit_code(String uitCode) {
        this.uitCode = uitCode;
    }

    public void setUitu_code(String uituCode) {
        this.uituCode = uituCode;
    }
}
