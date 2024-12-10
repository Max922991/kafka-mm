package org.example.paymentservice.service;


import org.example.model.OrderDTO;

public interface KafkaSender {

    void send(OrderDTO orderDTO);
}
