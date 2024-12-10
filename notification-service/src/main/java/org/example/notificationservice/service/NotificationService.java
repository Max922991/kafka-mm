package org.example.notificationservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.OrderDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {


    @KafkaListener(topics = "sent_orders", groupId = "notification_group")
    public void listen(OrderDTO orderDto) {
        log.info("Received order: {}", orderDto);
    }
}
