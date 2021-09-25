CREATE TABLE clients
(
    id              BIGINT NOT NULL UNIQUE,
    login           VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    surname         VARCHAR(255) NOT NULL,
    patronymic      VARCHAR(255) NOT NULL,
    phone_number    VARCHAR(255) NOT NULL unique,
    birthdate       date NOT NULL,
    passport_id     BIGINT NOT NULL UNIQUE,
    email           VARCHAR(255) NOT NULL UNIQUE,
    version         BIGINT,
    primary key (id)
);

CREATE TABLE accounts
(
    id              BIGINT NOT NULL UNIQUE,
    account_number  VARCHAR (255) NOT NULL UNIQUE,
    currency        VARCHAR (255) NOT NULL,
    balance         BIGINT NOT NULL DEFAULT 0  CHECK (balance > 0 OR balance = 0),
    client_id       BIGINT NOT NULL,
    isActive        BIT(1) NOT NULL DEFAULT true,
    version         BIGINT,
    primary key (id),
    foreign key (client_id) REFERENCES clients(id)
);

INSERT INTO clients (id, login, password, name, surname, patronymic, phone_number, birthdate, passport_id, email)
values (1000000001, 'kozlovda', '1111', 'dima', 'kozlov' , 'andreevich', '89993124527', '1991-02-25', 1111989989, 'somemail@mail.ru');

INSERT INTO accounts (id, account_number, currency, client_id)
values (1000000001, '222222222222222222222', 'RUB', 1000000001);