----------------------------------------------------------------
-- USERS
----------------------------------------------------------------
CREATE TABLE users
(
    id       BIGSERIAL    NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

----------------------------------------------------------------
-- ROLES
----------------------------------------------------------------
CREATE TABLE roles
(
    id   BIGSERIAL    NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

----------------------------------------------------------------
-- PIVOT ROLE USER
----------------------------------------------------------------
CREATE TABLE role_user
(
    role_id BIGINT NOT NULL REFERENCES roles (id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE
);