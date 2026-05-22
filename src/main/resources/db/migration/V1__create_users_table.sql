-- =============================================
-- Table: users
-- Purpose: Stores all registered user accounts
-- =============================================

CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    name       VARCHAR(100)        NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- Index for fast email lookup during login
CREATE INDEX idx_users_email ON users (email);

-- Documentation
COMMENT ON TABLE users IS 'Stores registered Trimzo user accounts';
COMMENT ON COLUMN users.password IS 'BCrypt hashed password - never plain text';
COMMENT ON COLUMN users.email IS 'Unique email used for login';