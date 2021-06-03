DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS contracts;
DROP TABLE IF EXISTS payments;

CREATE TABLE customers
(
    id      INT PRIMARY KEY,
    name    VARCHAR(20) NOT NULL,
    address VARCHAR(20) NOT NULL,
    tax_id  INT         NOT NULL
);

CREATE TABLE contracts
(
    id          INT PRIMARY KEY,
    name        VARCHAR(20) NOT NULL,
    subject     VARCHAR(20) NOT NULL,
    sum         DECIMAL     NOT NULL,
    comments    VARCHAR(20) NOT NULL,
    date        DATE        NOT NULL,
    customer_id INT ,

    CONSTRAINT customers_contracts_FK FOREIGN KEY (customer_id) REFERENCES customers (id)
);

CREATE TABLE payments
(
    id          SERIAL PRIMARY KEY,
    sum         DECIMAL   NOT NULL,
    time        TIMESTAMP NOT NULL,
    contract_id INT ,

    CONSTRAINT contracts_payments_FK FOREIGN KEY (contract_id) REFERENCES contracts (id)
);
