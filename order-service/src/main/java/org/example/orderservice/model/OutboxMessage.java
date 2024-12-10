package org.example.orderservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.orderservice.enums.Status;
import org.hibernate.annotations.ColumnTransformer;


@Entity
@Getter
@Setter
@Table(name = "outbox_messages")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OutboxMessage extends BasicEntity {

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    String payload;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;
}
