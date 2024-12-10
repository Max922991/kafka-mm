package org.example.orderservice.repository;

import jakarta.persistence.LockModeType;
import org.example.orderservice.model.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxMessageRepository extends JpaRepository<OutboxMessage, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT om FROM OutboxMessage om WHERE om.status = 'NEW'")
    List<OutboxMessage> findNewMessages();
}
