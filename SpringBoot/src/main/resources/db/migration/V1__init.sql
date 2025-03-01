CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$dcCL0ARQfBY.kqI1uaiepuFIoeHIQ9Pblj9LmWyxIg1ydj5hRCEPG', 'ROLE_ADMIN');

INSERT INTO users (username, password, role)
VALUES ('manager', '$2a$10$EAWja.I4SLQqXyPRMwIi2OV6/QeH7Mc.ETSG8uFBBp0vS5RgDeJ4.', 'ROLE_MANAGER');

INSERT INTO users (username, password, role)
VALUES ('user', '$2a$10$K/VuDn2rmMb0TgzeW7GQfu/Xr5hgEJnFUbY3xCrI3YfhULATbno86', 'ROLE_USER');