package org.example.orderservice.repository;

import org.example.orderservice.model.MessageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface MessageKeyRepository extends JpaRepository<MessageKey, UUID> {
    boolean existsByKey(String key);

    void deleteByCreatedAtBefore(LocalDateTime timestamp);
}
