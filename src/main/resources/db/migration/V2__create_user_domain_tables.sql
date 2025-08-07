-- Migration V2: Criação das tabelas do domínio User

CREATE TABLE users(
    prontuario VARCHAR(10) NOT NULL,
    password TEXT NOT NULL,
    name TEXT NOT NULL,
    email TEXT,
    document VARCHAR(15) NOT NULL,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamptz DEFAULT NULL,
    deleted_at timestamptz DEFAULT NULL,
    CONSTRAINT users_pk PRIMARY KEY (prontuario),
    CONSTRAINT users_password_uk UNIQUE (password),
    CONSTRAINT users_document_uk UNIQUE (document)
);

CREATE TABLE users_role(
    id SERIAL NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamptz DEFAULT NULL,
    deleted_at timestamptz DEFAULT NULL,
    CONSTRAINT users_role_pk PRIMARY KEY (id)
)