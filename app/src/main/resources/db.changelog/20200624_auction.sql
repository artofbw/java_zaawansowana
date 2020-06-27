CREATE TABLE department
(
    id BIGINT PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE category
(
    id BIGINT PRIMARY KEY,
    name VARCHAR NOT NULL,
    department_id  BIGINT NOT NULL REFERENCES department(id),
    unique (department_id, name)
);

CREATE TABLE auction
(
    id BIGINT PRIMARY KEY,
    title    VARCHAR    NOT NULL,
    description TEXT    NOT NULL,
    price DECIMAL NOT NULL CONSTRAINT price_positive CHECK ( price > 0 ),
    version INTEGER NOT NULL DEFAULT 1,
    created_by      BIGINT NOT NULL REFERENCES users(id),
    category_id     BIGINT NOT NULL REFERENCES category(id)
);

CREATE TABLE parameter
(
    id BIGINT PRIMARY KEY,
    key VARCHAR NOT NULL,
    unique (key)
);

CREATE TABLE auction_parameter
(
    auction_id  BIGINT  NOT NULL REFERENCES auction(id),
    parameter_id  BIGINT  NOT NULL REFERENCES parameter(id),
    value VARCHAR NOT NULL,
    PRIMARY KEY (auction_id, parameter_id)
);

CREATE TABLE auction_photo
(
    id BIGINT PRIMARY KEY,
    auction_id BIGINT NOT NULL REFERENCES auction(id),
    url VARCHAR NOT NULL,
    unique (auction_id, url)
);