-- Making short_code  nullable
-- Because first save URL and then short code can add
ALTER TABLE urls ALTER COLUMN short_code DROP NOT NULL;