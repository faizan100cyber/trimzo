-- =============================================
-- Table: URLs
-- Purpose: Stores all shortened URLs
-- =============================================

CREATE TABLE urls
(
    id           BIGSERIAL PRIMARY KEY,
    original_url TEXT                NOT NULL,
    short_code   VARCHAR(10) UNIQUE  NOT NULL,
    user_id      BIGINT REFERENCES users (id) ON DELETE CASCADE,
    title        VARCHAR(255),
    is_active    BOOLEAN   DEFAULT TRUE,
    expires_at   TIMESTAMP,
    created_at   TIMESTAMP DEFAULT NOW(),
    updated_at   TIMESTAMP DEFAULT NOW()
);

-- Fast lookup by short_code (most frequent query)
CREATE INDEX idx_urls_short_code ON urls (short_code);
CREATE INDEX idx_urls_user_id ON urls (user_id);

COMMENT ON TABLE urls IS 'Stores all shortened URLs created by users';
COMMENT ON COLUMN urls.short_code IS 'Base62 encoded unique identifier e.g. aB3xYz';
COMMENT ON COLUMN urls.is_active IS 'False = URL disabled, wont redirect';
COMMENT ON COLUMN urls.expires_at IS 'Optional expiry date, NULL = never expires';