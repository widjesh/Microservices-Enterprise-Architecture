package edu.mum.cs.payment.service.models;

public enum EPaymentType {

    CREDIT_CARD("creditcard"),

    BANK_TRANSFER("banktransfer"),

    PAY_PAL("paypay");

    private String description;

    public String getDescription() {
        return description;
    }

    EPaymentType(String description) {
        this.description = description;
    }
}
