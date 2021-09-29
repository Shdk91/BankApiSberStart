CREATE TABLE clients
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    login        VARCHAR(255) NOT NULL UNIQUE,
    name         VARCHAR(255) NOT NULL,
    surname      VARCHAR(255) NOT NULL,
    patronymic   VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL unique,
    birthdate    date         NOT NULL,
    passport_id  BIGINT       NOT NULL UNIQUE,
    email        VARCHAR(255) NOT NULL UNIQUE,
    version      BIGINT       not null default 0,
    primary key (id)
);

CREATE TABLE accounts
(
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    account_number VARCHAR(20)  NOT NULL UNIQUE,
    currency       VARCHAR(255) NOT NULL,
    balance        DOUBLE       NOT NULL DEFAULT 0 CHECK (balance > 0 OR balance = 0),
    client_id      BIGINT       NOT NULL,
    isactive       BIT(1)       NOT NULL DEFAULT true,
    version        BIGINT       not null default 0,
    primary key (id),
    foreign key (client_id) REFERENCES clients (id)
);

CREATE TABLE cards
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    isactive    BIT(1)      NOT NULL DEFAULT true,
    card_number VARCHAR(16) NOT NULL UNIQUE,
    client_id   BIGINT      NOT NULL,
    account_id  BIGINT      NOT NULL,
    version     BIGINT      not null default 0,
    primary key (id),
    foreign key (client_id) references clients (id),
    foreign key (account_id) references accounts (id)
);

CREATE TABLE transactions
(
    id                   BIGINT       NOT NULL AUTO_INCREMENT,
    account_id           BIGINT       NOT NULL,
    counterparty_account VARCHAR(255)          default null,
    is_plus              BIT(1)       NOT NULL,
    time_of_transact     TIMESTAMP    NOT NULL,
    transaction_type     VARCHAR(255) NOT NULL,
    sum                  DOUBLE       NOT NULL CHECK (sum > 0 OR sum = 0),
    version              BIGINT       not null default 0,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) references accounts (id)
);

CREATE TABLE counterparties
(
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    name           VARCHAR(255) NOT NULL,
    account_number VARCHAR(20)  NOT NULL UNIQUE,
    tax_number     VARCHAR(12)  NOT NULL UNIQUE,
    version        BIGINT       NOT NULL DEFAULT 0,
    primary key (id)
);

CREATE TABLE clients_counterparties
(
    client_id         BIGINT NOT NULL,
    counterparties_id BIGINT NOT NULL,

    primary key (client_id, counterparties_id),
    foreign key (client_id) references clients (id),
    foreign key (counterparties_id) references counterparties (id)
);

CREATE INDEX client_login
    ON clients (login);

CREATE INDEX account_client_id
    ON accounts (client_id);

CREATE INDEX card_client_id
    ON cards (client_id);

INSERT INTO clients (login, name, surname, patronymic, phone_number, birthdate, passport_id, email)
values ('kozlovda', 'dima', 'kozlov', 'andreevich', '89993124527', '1991-02-25', 1111989989, 'somemail@mail.ru');

INSERT INTO accounts (account_number, currency, client_id)
values ('22222222222222222222', 'RUB', 1);

INSERT INTO cards (card_number, client_id, account_id)
values ('4222422242224222', 1, 1);

INSERT INTO counterparties (name, account_number, tax_number)
VALUES ('рога и копыта', '12345123451234512345', '0987654321'),
       ('березка', '11111222223333344444', '1234567890');

INSERT into clients_counterparties (client_id, counterparties_id)
values (1, 1),
       (1, 2);