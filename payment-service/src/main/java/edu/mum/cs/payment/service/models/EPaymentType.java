package edu.mum.cs.payment.service.models;

/**
 * The enum E payment type.
 */
public enum EPaymentType {

    /**
     * Credit card e payment type.
     */
    CREDIT_CARD("creditcard"),

    /**
     * Bank transfer e payment type.
     */
    BANK_TRANSFER("banktransfer"),

    /**
     * Pay pal e payment type.
     */
    PAY_PAL("paypal");

    private String description;

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    EPaymentType(String description) {
        this.description = description;
    }
}
