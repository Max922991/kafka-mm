package org.example.shippingservice.service;


import org.example.model.OrderDTO;

public interface KafkaSender {

    void send(OrderDTO orderDTO);
}
