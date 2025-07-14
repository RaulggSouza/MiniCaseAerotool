-- Migration V1: Criação das tabelas do domínio Tool

CREATE TABLE tool(
    id BIGSERIAL PRIMARY KEY,
    integration_id VARCHAR(36),
    description TEXT,
    category VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMPTZ DEFAULT NULL
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
    CONSTRAINT tool_item_tool_id_tool_fkey FOREIGN KEY(tool_id) REFERENCES tool(id),
    CONSTRAINT tool_item_status_id_tool_item_status_fkey FOREIGN KEY(status_id) REFERENCES tool_item_status(id),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMPTZ DEFAULT NULL
);

CREATE OR REPLACE FUNCTION update_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_tool_updated_at
    BEFORE UPDATE ON tool
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

CREATE TRIGGER trg_tool_item_status_updated_at
    BEFORE UPDATE ON tool_item_status
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

CREATE TRIGGER trg_tool_item_updated_at
    BEFORE UPDATE ON tool_item
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at();