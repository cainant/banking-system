CREATE TABLE enterprises
(
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    name    VARCHAR(100) NOT NULL,
    cnpj    VARCHAR(20)  NOT NULL UNIQUE,
    balance FLOAT        NOT NULL,
    fee     FLOAT        NOT NULL,

    PRIMARY KEY (id)
)