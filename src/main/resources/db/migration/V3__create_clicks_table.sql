CREATE TABLE clicks (

                        id BIGSERIAL PRIMARY KEY,

                        url_id BIGINT REFERENCES urls(id)
                            ON DELETE CASCADE,

                        clicked_at TIMESTAMP DEFAULT NOW(),

                        ip_address VARCHAR(45),

                        country VARCHAR(100),

                        city VARCHAR(100),

                        user_agent TEXT,

                        device_type VARCHAR(20),

                        os VARCHAR(50),

                        browser VARCHAR(50),

                        referrer TEXT,

                        referrer_source VARCHAR(50)
);

CREATE INDEX idx_clicks_url_id
    ON clicks(url_id);

CREATE INDEX idx_clicks_clicked_at
    ON clicks(clicked_at);

CREATE INDEX idx_clicks_country
    ON clicks(country);

CREATE INDEX idx_clicks_device
    ON clicks(device_type);

COMMENT ON TABLE clicks IS
    'Analytics data for every URL click event';

COMMENT ON COLUMN clicks.referrer_source IS
    'Parsed source: Twitter, Facebook, Direct, etc.';