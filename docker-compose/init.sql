CREATE SCHEMA IF NOT EXISTS component;

CREATE TYPE component.USER_TYPE AS ENUM ('user','admin');
CREATE TYPE component.UNIT AS ENUM ('mOhms','Ohms','kOhms','MOhms','pF','nF','uF','F');

CREATE TABLE IF NOT EXISTS component.operating_temperatures
(
    id        BIGSERIAL PRIMARY KEY,
    low_temp  VARCHAR(5) NOT NULL,
    high_temp VARCHAR(5) NOT NULL,
    unit_temp VARCHAR(3) DEFAULT '°C'
);

CREATE TABLE IF NOT EXISTS component.cases
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(10)   NOT NULL,
    length    NUMERIC(3, 1) NOT NULL,
    width     NUMERIC(3, 1) NOT NULL,
    unit_case VARCHAR(3) DEFAULT 'mm'
);


CREATE TABLE IF NOT EXISTS component.component
(
    id             BIGSERIAL PRIMARY KEY,
    manufacturer   VARCHAR(20)    NOT NULL,
    value          INTEGER        NOT NULL,
    unit           component.UNIT NOT NULL,
    power_value    VARCHAR(8),
    voltage_value  VARCHAR(8),
    case_type_id   BIGINT REFERENCES component.cases (id),
    temperature_id BIGINT REFERENCES component.operating_temperatures (id),
    price          DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS component.users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(20)         NOT NULL,
    last_name  VARCHAR(20)         NOT NULL,
    email      VARCHAR(40)         NOT NULL,
    password   VARCHAR(40)         NOT NULL,
    type       component.USER_TYPE NOT NULL
);

CREATE TABLE IF NOT EXISTS component.orders
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES component.users
);


CREATE TABLE IF NOT EXISTS component.order_links
(
    order_id     BIGINT REFERENCES component.orders,
    component_id BIGINT REFERENCES component.component,
    count        INTEGER NOT NULL
);

INSERT INTO component.operating_temperatures(low_temp, high_temp)
VALUES ('-55', '+125'),
       ('-55', '+150'),
       ('-55', '+160'),
       ('-65', '+150');


INSERT INTO component.cases(name, length, width)
VALUES ('0402', 0.4, 0.2),
       ('0603', 0.6, 0.3),
       ('0805', 0.8, 0.5),
       ('1210', 1.2, 1.0);
