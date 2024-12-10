package org.example.orderservice.mapper;

import org.example.model.OrderDTO;
import org.example.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);

    @Mapping(target = "productsId", ignore = true)
    OrderDTO toDTO(Order order);
}
