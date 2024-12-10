package org.example.shippingservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.OrderDTO;
import org.example.shippingservice.service.KafkaSender;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaSenderImpl implements KafkaSender {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    @Override
    public void send(OrderDTO orderDTO) {
        if (orderDTO == null) {
            log.warn("Attempted to send a null OrderDTO object");
            return;
        }
        try {
            log.info("Отправка OrderDTO: {}", orderDTO);
            kafkaTemplate.send("payed_orders", orderDTO);
            log.info("OrderDTO отправлен в Shipping-service");
        } catch (Exception e) {
            log.error("Ошибка отправки OrderDTO: {}", e.getMessage());
        }
    }
}