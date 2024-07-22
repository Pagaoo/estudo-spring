DO $$
BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM information_schema.columns
            WHERE table_name='products'
            AND column_name='product_image'
        ) THEN
ALTER TABLE products ADD COLUMN product_image VARCHAR(255);
end if;
END $$;
