package edu.mum.cs.payment.service.controller;

import edu.mum.cs.payment.service.models.Payment;
import edu.mum.cs.payment.service.repository.IPaymentRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
    private String bankCardServicePort;

    @Value("${PAYMENT_TYPE:bank}")
    private String paymentType;

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


    /**
     * Payment made string.
     *
     * @param payment the payment
     * @param token   the token
     *
     * @return the string
     */
    @PostMapping
    public String paymentMade(@RequestBody Payment payment, @RequestHeader (name="Authorization") String token){
        Claims claims = Jwts.parser().setSigningKey("VE9QU0VDUkVU").parseClaimsJws(token.substring(7)).getBody();
        String email = claims.get("email").toString();

        return email;
    }

}
