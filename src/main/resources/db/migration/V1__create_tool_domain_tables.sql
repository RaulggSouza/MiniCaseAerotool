-- Migration V1: Criação das tabelas do domínio Tool

CREATE TABLE tool_category(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT
);

CREATE TABLE tool(
    id BIGSERIAL PRIMARY KEY,
    integration_id VARCHAR(36),
    description TEXT,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMPTZ DEFAULT NULL,
    CONSTRAINT tool_category_id_tool_category_fkey FOREIGN KEY(category_id) REFERENCES tool_category(id)
);

CREATE TABLE tool_item_status(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMPTZ DEFAULT NULL
);

CREATE TABLE tool_item(
    id BIGSERIAL PRIMARY KEY,
    integration_id VARCHAR(36),
    color VARCHAR(50),
    tool_id BIGINT NOT NULL,
    status_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMPTZ DEFAULT NULL,
    CONSTRAINT tool_item_tool_id_tool_fkey FOREIGN KEY(tool_id) REFERENCES tool(id),
    CONSTRAINT tool_item_status_id_tool_item_status_fkey FOREIGN KEY(status_id) REFERENCES tool_item_status(id)
);

CREATE OR REPLACE FUNCTION update_updated_at()
RETURNS TRIGGER AS $$
    BEGIN
        IF TG_OP = 'Update' THEN
            NEW.updated_at = CURRENT_TIMESTAMP;
        END IF;
        RETURN NEW;
    END;
$$ LANGUAGE plpgsql;
