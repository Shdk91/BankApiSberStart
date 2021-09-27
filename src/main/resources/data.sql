CREATE TABLE clients
(
    id              BIGINT NOT NULL UNIQUE,
    login           VARCHAR(255) NOT NULL UNIQUE,
    name            VARCHAR(255) NOT NULL,
    surname         VARCHAR(255) NOT NULL,
    patronymic      VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(255) NOT NULL unique,
    birthdate       date NOT NULL,
    passport_id     BIGINT NOT NULL UNIQUE,
    email           VARCHAR(255) NOT NULL UNIQUE,
    version         BIGINT not null default 0,
    primary key (id)
);

CREATE TABLE accounts
(
    id              BIGINT NOT NULL UNIQUE,
    account_number  VARCHAR (20) NOT NULL UNIQUE,
    currency        VARCHAR (255) NOT NULL,
    balance         BIGINT NOT NULL DEFAULT 0  CHECK (balance > 0 OR balance = 0),
    client_id       BIGINT NOT NULL,
    isactive        BIT(1) NOT NULL DEFAULT true,
    version         BIGINT not null default 0,
    primary key (id),
    foreign key (client_id) REFERENCES clients(id)
);

CREATE TABLE cards
(
    id              BIGINT NOT NULL AUTO_INCREMENT,
    isactive        BIT(1) NOT NULL DEFAULT true,
    card_number     VARCHAR (16) NOT NULL UNIQUE,
    client_id       BIGINT NOT NULL,
    account_id      BIGINT NOT NULL,
    version         BIGINT not null default 0,
    primary key (id),
    foreign key (client_id) references clients(id),
    foreign key (account_id) references accounts(id)
);

CREATE TABLE transactions
(
    id                      BIGINT NOT NULL AUTO_INCREMENT,
    account_id              BIGINT NOT NULL,
    counterparty_account    VARCHAR (255) default null,
    is_plus                 BIT(1) NOT NULL,
    time_of_transact        TIMESTAMP NOT NULL,
    transaction_type        VARCHAR (255) NOT NULL,
    sum                     BIGINT NOT NULL CHECK (sum > 0 OR sum = 0),
    version                 BIGINT not null default 0,
    PRIMARY KEY (id),
    FOREIGN  KEY (account_id) references accounts(id)
);

CREATE INDEX client_login
ON clients(login);

CREATE INDEX account_client_id
ON accounts(client_id);

CREATE INDEX card_client_id
ON cards(client_id);

INSERT INTO clients (id, login, name, surname, patronymic, phone_number, birthdate, passport_id, email)
values (1000000001, 'kozlovda', 'dima', 'kozlov' , 'andreevich', '89993124527', '1991-02-25', 1111989989, 'somemail@mail.ru');

INSERT INTO accounts (id, account_number, currency, client_id)
values (1000000001, '22222222222222222222', 'RUB', 1000000001);

INSERT INTO cards ( card_number, client_id, account_id)
values ('4222422242224222', 1000000001, 1000000001);