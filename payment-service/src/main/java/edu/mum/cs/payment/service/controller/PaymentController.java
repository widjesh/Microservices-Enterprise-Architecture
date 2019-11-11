package edu.mum.cs.payment.service.controller;

import edu.mum.cs.payment.service.models.EPaymentType;
import edu.mum.cs.payment.service.models.Payment;
import edu.mum.cs.payment.service.repository.IPaymentRepository;
import edu.mum.cs.payment.service.template.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Payment controller.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private IPaymentRepository paymentRepository;

    private RestTemplate restTemplate;

    @Value("${SHIPPING_SERVICE_IP:localhost}")
    private String shippingServiceIp;

    @Value("${SHIPPING_SERVICE_PORT:3006}")
    private String shippingServicePort;

    @Value("${PAY_PAL_SERVICE_IP:localhost}")
    private String payPalServiceIp;

    @Value("${PAY_PAL_SERVICE_PORT:3003}")
    private String payPalServicePort;

    @Value("${CREDIT_CARD_SERVICE_IP:localhost}")
    private String creditCardServiceIp;

    @Value("${CREDIT_CARD_SERVICE_PORT:3004}")
    private String creditCardServicePort;

    @Value("${BANK_SERVICE_IP:localhost}")
    private String bankServiceIp;

    @Value("${BANK_SERVICE_PORT:3005}")
    private String bankServicePort;

    private static final String PROTOCOL = "http://";


    /**
     * Instantiates a new Payment controller.
     *
     * @param paymentRepository the payment repository
     * @param restTemplate      the rest template
     */
    @Autowired
    public PaymentController(IPaymentRepository paymentRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public String paymentMade(@RequestBody Payment payment) {
        String url = generateUrl(payment.getPaymentType());
        MessageTemplate messageTemplate  = restTemplate.postForObject(url, null, MessageTemplate.class);
        if(messageTemplate != null && messageTemplate.getMessage() != null
                && messageTemplate.getMessage().toLowerCase().contains("successfully")) {
            this.paymentRepository.save(payment);
            return "Payment recorded successfully";
        }else{
            return "Payment not recorded successfully";
        }
    }

    private String generateUrl(EPaymentType paymentMode) {
        String address;
        if (paymentMode.equals(EPaymentType.BANK_TRANSFER))
            address = PROTOCOL + bankServiceIp + ":" + bankServicePort + "/" +
                    EPaymentType.BANK_TRANSFER.getDescription();
        else if (paymentMode.equals(EPaymentType.CREDIT_CARD))
            address = PROTOCOL + creditCardServiceIp + ":" + creditCardServicePort + "/" +
                    EPaymentType.CREDIT_CARD.getDescription();
        else
            address = PROTOCOL + bankServiceIp + ":" + bankServicePort + "/" +
                    EPaymentType.BANK_TRANSFER.getDescription();
        return address;
    }

}


