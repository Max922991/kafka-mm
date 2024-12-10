package org.example.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.util.MessageKeyCleaner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageKeyCleanupScheduler {

    private final MessageKeyCleaner messageKeyCleaner;

    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void cleanUpOldMessageKeys() {
        messageKeyCleaner.cleanOldMessageKeys();
    }
}