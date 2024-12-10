package org.example.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.enums.Status;
import org.example.orderservice.model.OutboxMessage;
import org.example.orderservice.repository.OutboxMessageRepository;
import org.example.orderservice.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutboxMessageRepository outboxMessageRepository;

    public void createOutboxMessage(Object payload) {

        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.setStatus(Status.NEW);
        outboxMessage.setPayload(JsonUtil.toJson(payload));
        outboxMessage.setCreatedAt(LocalDateTime.now());

        outboxMessageRepository.save(outboxMessage);
    }
}
