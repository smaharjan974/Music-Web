-- V1__initial_schema.sql

CREATE TABLE permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE role_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

-- Insert some permissions
INSERT INTO permissions (name) VALUES ('READ_PRIVILEGES');
INSERT INTO permissions (name) VALUES ('WRITE_PRIVILEGES');
INSERT INTO permissions (name) VALUES ('DELETE_PRIVILEGES');

-- Insert some roles
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');

-- Assign permissions to roles (role_permissions table)
INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 1); -- ADMIN - READ_PRIVILEGES
INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 2); -- ADMIN - WRITE_PRIVILEGES
INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 3); -- ADMIN - DELETE_PRIVILEGES
INSERT INTO role_permissions (role_id, permission_id) VALUES (2, 1); -- USER - READ_PRIVILEGES