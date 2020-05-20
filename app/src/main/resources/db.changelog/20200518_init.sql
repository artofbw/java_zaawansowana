CREATE SEQUENCE hibernate_sequence;

CREATE TABLE users
(
    id  BIGINT  NOT NULL,
    username    VARCHAR    NOT NULL,
    PRIMARY KEY (id)
);
