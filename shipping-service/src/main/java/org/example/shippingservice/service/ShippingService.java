package org.example.shippingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.OrderDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippingService {
    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;


    @KafkaListener(topics = "payed_orders", groupId = "shipping_group")
    public void sendOrderDto(OrderDTO orderDto) {
        log.info("Received order: {}", orderDto);
        kafkaTemplate.send("sent_orders", orderDto);
        log.info("Sent order to payed_orders: {}", orderDto);
    }
}
