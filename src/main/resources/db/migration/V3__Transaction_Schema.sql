CREATE TABLE IF NOT EXISTS customers (
    id BIGSERIAL PRIMARY KEY,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    value NUMERIC(19,2) NOT NULL,
    transaction_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_sender FOREIGN KEY (sender_id) REFERENCES customers(id),
    CONSTRAINT fk_receiver FOREIGN KEY (receiver_id) REFERENCES customers(id)
);