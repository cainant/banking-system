CREATE TABLE empresas
(
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    nome  VARCHAR(100) NOT NULL,
    cnpj  VARCHAR(20)  NOT NULL,
    saldo BIGINT       NOT NULL,

    PRIMARY KEY (id)
)