-- =============================================
-- Table: clicks
-- Purpose: Analytics data for every URL click
-- =============================================

CREATE TABLE clicks
(
    id             BIGSERIAL PRIMARY KEY,
    url_id         BIGINT REFERENCES urls (id) ON DELETE CASCADE,
    clicked_at     TIMESTAMP DEFAULT NOW(),

    -- Geo Analytics
    ip_address     VARCHAR(45),
    country        VARCHAR(100),
    city           VARCHAR(100),

    -- Device Analytics
    user_agent     TEXT,
    device_type    VARCHAR(20),
    os             VARCHAR(50),
    browser        VARCHAR(50),

    -- Referrer Analytics
    referrer       TEXT,
    referrer_source VARCHAR(50)
);

-- Indexes for fast analytics queries
CREATE INDEX idx_clicks_url_id     ON clicks (url_id);
CREATE INDEX idx_clicks_clicked_at ON clicks (clicked_at);
CREATE INDEX idx_clicks_country    ON clicks (country);
CREATE INDEX idx_clicks_device     ON clicks (device_type);

COMMENT ON TABLE clicks IS 'Stores analytics data for every URL click event';
COMMENT ON COLUMN clicks.ip_address IS 'Supports IPv4 and IPv6 (max 45 chars)';
COMMENT ON COLUMN clicks.device_type IS 'mobile, desktop, or tablet';
COMMENT ON COLUMN clicks.referrer_source IS 'Twitter, Facebook, WhatsApp, Direct, etc.';