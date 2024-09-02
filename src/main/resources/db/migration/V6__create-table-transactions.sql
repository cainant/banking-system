CREATE TABLE Transaction
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT,
    type       VARCHAR(255),
    value      FLOAT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account (id)
);
