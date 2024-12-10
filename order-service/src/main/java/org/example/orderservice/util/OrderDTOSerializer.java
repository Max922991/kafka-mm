package org.example.orderservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.example.model.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOSerializer implements Serializer<OrderDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public byte[] serialize(String s, OrderDTO orderDTO) {
        try {
            if (orderDTO == null) {
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(orderDTO);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing OrderDTO to byte[]");
        }
    }
}
