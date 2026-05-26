-- =============================================
-- Table: api_keys
-- Purpose: API keys for third-party integration
-- =============================================

CREATE TABLE api_keys
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT REFERENCES users (id) ON DELETE CASCADE,
    key_value  VARCHAR(128) UNIQUE NOT NULL,
    name       VARCHAR(100),
    is_active  BOOLEAN   DEFAULT TRUE,
    last_used  TIMESTAMP,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_api_keys_key_value ON api_keys (key_value);
CREATE INDEX idx_api_keys_user_id ON api_keys (user_id);

COMMENT ON TABLE api_keys IS 'API keys for third-party app integration';
COMMENT ON COLUMN api_keys.key_value IS 'BCrypt hashed API key - raw key shown only once';
COMMENT ON COLUMN api_keys.name IS 'Label for the key e.g. My Website, Mobile App';
COMMENT ON COLUMN api_keys.last_used IS 'Last time this key was used';