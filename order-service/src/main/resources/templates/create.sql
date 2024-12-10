CREATE TABLE orders
(
    id               UUID PRIMARY KEY,
    user_id          UUID         NOT NULL,
    quantity         INTEGER      NOT NULL,
    delivery_address VARCHAR(255) NOT NULL,
    created_at       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE outbox_messages
(
    id         UUID PRIMARY KEY,
    payload    JSONB       NOT NULL,
    status     VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE message_keys
(
    id         UUID PRIMARY KEY,
    key        VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
