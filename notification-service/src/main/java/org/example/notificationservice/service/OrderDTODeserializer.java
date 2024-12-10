package org.example.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.model.OrderDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderDTODeserializer implements Deserializer<OrderDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderDTO deserialize(String topic, byte[] payload) {
        try {
            return objectMapper.readValue(payload, OrderDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка десериализации объекта ", e);
        }
    }
}
