package org.example.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.OrderDTO;
import org.example.orderservice.enums.Status;
import org.example.orderservice.mapper.OrderMapper;
import org.example.orderservice.model.MessageKey;
import org.example.orderservice.model.Order;
import org.example.orderservice.model.OutboxMessage;
import org.example.orderservice.repository.MessageKeyRepository;
import org.example.orderservice.repository.OutboxMessageRepository;
import org.example.orderservice.util.JsonUtil;
import org.example.orderservice.util.KeyUtil;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutboxMessageSenderService {

    private final OutboxMessageRepository outboxMessageRepository;
    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;
    private final MessageKeyRepository messageKeyRepository;
    private final OrderMapper orderMapper;


    @Scheduled(fixedRate = 5000)
    @Transactional
    public void sendOutboxMessage() {
        List<OutboxMessage> outboxMessages = outboxMessageRepository.findNewMessages();

        for (OutboxMessage message : outboxMessages) {
            String uniqueKey = KeyUtil.generateUniqueKey();

            if (!messageKeyRepository.existsByKey(uniqueKey)) {
                try {
                    Order order = JsonUtil.fromJson(message.getPayload(), Order.class);
                    OrderDTO orderDTO = orderMapper.toDTO(order);

                    log.info("Отправка OrderDTO: {}", orderDTO);
                    kafkaTemplate.send("new_orders", orderDTO);
                    log.info("OrderDTO отправлен в Payment-service");


                    MessageKey messageKey = new MessageKey();
                    messageKey.setKey(uniqueKey);
                    messageKeyRepository.save(messageKey);

                    message.setStatus(Status.SENT);

                } catch (Exception e) {
                    log.error("Ошибка отправки OrderDTO: {}", e.getMessage());
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Duplicate key detected: " + uniqueKey);
            }
        }
    }
}
