package org.example.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.example.model.OrderDTO;
import org.example.orderservice.mapper.OrderMapper;
import org.example.orderservice.model.Order;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OutboxService outboxService;

    @Transactional
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public OrderDTO createOrder(OrderDTO orderDTO) {

        Order order = orderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);

        outboxService.createOutboxMessage(savedOrder);

        return orderMapper.toDTO(savedOrder);
    }
}
