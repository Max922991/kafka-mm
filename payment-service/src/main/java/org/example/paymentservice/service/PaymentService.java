package org.example.paymentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.OrderDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;


    @KafkaListener(topics = "new_orders", groupId = "payment_group")
    public void sendOrderDto(OrderDTO orderDto) {
        log.info("Received order: {}", orderDto);
        kafkaTemplate.send("payed_orders", orderDto);
        log.info("Sent order to payed_orders: {}", orderDto);
    }
}
