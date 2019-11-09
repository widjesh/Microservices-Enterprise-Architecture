package edu.mum.cs.shipping.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.cs.shipping.service.models.Shipping;
import edu.mum.cs.shipping.service.repository.IShippingRepository;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/shipping")
@AllArgsConstructor
public class ShippingController{

    private IShippingRepository shippingRepository;

    @PostMapping(value="/save")
    public String postShipping(@RequestBody Shipping shipping) {
        this.shippingRepository.save(shipping);
        return "Everything have been successfully shipped";
    }

    @GetMapping(value="/find")
    public List<Shipping> getShippments() {
        return this.shippingRepository.findAll();
    }
    
}