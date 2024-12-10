package org.example.shippingservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.OrderDTO;
import org.example.shippingservice.service.ShippingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping
    public void createOrder(@RequestBody OrderDTO order) {
        shippingService.sendOrderDto(order);
    }
}
