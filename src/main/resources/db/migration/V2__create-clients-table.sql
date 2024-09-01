CREATE TABLE clients
(
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL,
    cpf    VARCHAR(20)  NOT NULL UNIQUE,

    PRIMARY KEY (id)
)