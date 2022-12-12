----------------------------------------------------------------
-- ROLES
----------------------------------------------------------------
INSERT INTO roles VALUES (DEFAULT, 'ROLE_USER');
INSERT INTO roles VALUES (DEFAULT, 'ROLE_ADMIN');

----------------------------------------------------------------
-- USERS
----------------------------------------------------------------
INSERT INTO users VALUES (DEFAULT, 'user', 'user@mail.com', '$2a$10$EBjddqv2suhjy3fj7MiOUuawnLbyb0ICGmVJCM.IFUQKDMWB/dZ/2');
INSERT INTO users VALUES (DEFAULT, 'admin', 'admin@mail.com', '$2a$10$.EBJDceNVI9ox05wIVHZzOqVjReRKWPFRiR9ssrYB5DdTMp7Hfm/2');
INSERT INTO roles VALUES (DEFAULT, 'ROLE_ADMIN');

----------------------------------------------------------------
-- ROLE USER
----------------------------------------------------------------
INSERT INTO role_user VALUES (1, 1);
INSERT INTO role_user VALUES (2, 2);

