package edu.mum.cs.order.service.templates;

import lombok.Data;

/**
 * The type Payment.
 */
@Data
public class Payment {

    private long id;

    private String orderNumber;

    private EPaymentType paymentType;

}
