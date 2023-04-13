--liquibase formatted sql

--changeset sqlead:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset sqlead:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);