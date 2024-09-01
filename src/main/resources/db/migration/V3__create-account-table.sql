CREATE TABLE account
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    email        VARCHAR(100) NOT NULL UNIQUE,
    balance      FLOAT        NOT NULL,
    client_id     BIGINT,
    enterprise_id BIGINT,

    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (enterprise_id) REFERENCES enterprises (id)
);