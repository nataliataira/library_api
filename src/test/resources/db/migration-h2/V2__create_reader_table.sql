CREATE TABLE IF NOT EXISTS reader (
    id      BIGINT          NOT NULL    AUTO_INCREMENT,
    name    VARCHAR(150)    NOT NULL,
    email   VARCHAR(150)    NOT NULL    UNIQUE,
    PRIMARY KEY (id)
);