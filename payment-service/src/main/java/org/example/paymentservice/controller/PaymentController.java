package org.example.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.OrderDTO;
import org.example.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public void createOrder(@RequestBody OrderDTO order) {
        paymentService.sendOrderDto(order);
    }
}
