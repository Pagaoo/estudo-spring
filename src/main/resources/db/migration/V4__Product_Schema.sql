CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    product_type VARCHAR(255) NOT NULL,
    price NUMERIC(19,2),
    quantity BIGINT
);