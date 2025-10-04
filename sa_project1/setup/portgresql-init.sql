-- Tạo schema
CREATE SCHEMA IF NOT EXISTS "order";

-- Enum order_status (Postgres 14 không hỗ trợ IF NOT EXISTS)
DO $$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'order_status') THEN
       CREATE TYPE order_status AS ENUM ('APPROVED','CANCELLING','CANCELLED');
   END IF;
END$$;

-- Orders table
CREATE TABLE IF NOT EXISTS "order".orders (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    restaurant_id UUID NOT NULL,
    tracking_id UUID NOT NULL UNIQUE,
    price NUMERIC(10,2) NOT NULL,
    order_status order_status NOT NULL,
    failure_messages VARCHAR,
    created_at TIMESTAMPTZ NOT NULL
);

-- Order items table
CREATE TABLE IF NOT EXISTS "order".order_items (
    id BIGINT NOT NULL,
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    sub_total NUMERIC(10,2) NOT NULL,
    CONSTRAINT order_items_pkey PRIMARY KEY (id, order_id),
    CONSTRAINT fk_order_id FOREIGN KEY (order_id)
        REFERENCES "order".orders(id)
        ON DELETE CASCADE
);

-- Indexes
CREATE INDEX IF NOT EXISTS idx_orders_tracking_id
    ON "order".orders(tracking_id);

CREATE INDEX IF NOT EXISTS idx_orders_customer_id
    ON "order".orders(customer_id);
