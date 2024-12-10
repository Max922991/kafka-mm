package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDTO {


    @JsonProperty("userId")
    private UUID userId;

    @JsonProperty("productsId")
    private List<Long> productsId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("deliveryAddress")
    private String deliveryAddress;

    public OrderDTO() {
    }
}
