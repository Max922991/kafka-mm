package org.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.OrderDTO;
import org.example.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO order) {
        return orderService.createOrder(order);
    }
}