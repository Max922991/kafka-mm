package org.example.orderservice.util;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.repository.MessageKeyRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MessageKeyCleaner {

    private final MessageKeyRepository messageKeyRepository;

    public void cleanOldMessageKeys() {
        LocalDateTime threshold = LocalDateTime.now().minusHours(24); // мало
        messageKeyRepository.deleteByCreatedAtBefore(threshold);
    }
}