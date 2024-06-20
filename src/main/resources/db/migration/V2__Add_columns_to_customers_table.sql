DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM information_schema.columns
            WHERE table_name='customers'
            AND column_name='user_type'
        ) THEN
            ALTER TABLE customers ADD COLUMN user_type VARCHAR(255);
        end if;
END $$;

DO $$
    BEGIN
        IF NOT EXISTS(
            SELECT 1
            FROM information_schema.columns
            WHERE table_name='customers'
            AND column_name='balance'
        ) THEN
            ALTER TABLE customers ADD COLUMN balance NUMERIC(19,2);
        end if;
END $$