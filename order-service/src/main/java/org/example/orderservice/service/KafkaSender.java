package org.example.orderservice.service;


import org.example.model.OrderDTO;

public interface KafkaSender {

    void send(OrderDTO orderDTO);
}
