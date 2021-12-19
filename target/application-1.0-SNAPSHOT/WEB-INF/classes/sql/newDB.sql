CREATE SCHEMA IF NOT EXISTS component;

CREATE TABLE IF NOT EXISTS component.operating_temperatures
(
    id        SERIAL PRIMARY KEY,
    low_temp  VARCHAR(5) NOT NULL,
    high_temp VARCHAR(5) NOT NULL,
    unit_temp VARCHAR(3) DEFAULT '°C'
);

CREATE TABLE IF NOT EXISTS component.cases
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(10)   NOT NULL,
    length    NUMERIC(3, 1) NOT NULL,
    width     NUMERIC(3, 1) NOT NULL,
    unit_case VARCHAR(3) DEFAULT 'mm'
);

CREATE TABLE IF NOT EXISTS component.properties
(
    id             SERIAL PRIMARY KEY,
    case_type_id   INTEGER REFERENCES component.cases (id),
    temperature_id INTEGER REFERENCES component.operating_temperatures (id),
    price          NUMERIC(4, 2) NOT NULL,
    unit_price     VARCHAR(3) DEFAULT 'USD'
);

CREATE TABLE IF NOT EXISTS component.resistors
(
    id             SERIAL PRIMARY KEY,
    property_id    INTEGER REFERENCES component.properties (id),
    resistor_value INTEGER       NOT NULL,
    unit_value     component.UNIT_RESISTOR NOT NULL,
    power_value    VARCHAR(8)    NOT NULL
);

CREATE TABLE IF NOT EXISTS component.capacitor
(
    id              SERIAL PRIMARY KEY,
    property_id     INTEGER REFERENCES component.properties (id),
    capacitor_value INTEGER        NOT NULL,
    unit_value      component.UNIT_CAPACITOR NOT NULL,
    voltage_value   VARCHAR(8)     NOT NULL
);



CREATE TABLE IF NOT EXISTS component.users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name  VARCHAR(20) NOT NULL,
    login      VARCHAR(20) NOT NULL,
    password   VARCHAR(20) NOT NULL,
    type       component.USER_TYPE   NOT NULL
);

CREATE TABLE IF NOT EXISTS component.orders
(
    user_id        INTEGER PRIMARY KEY REFERENCES component.users (id),
    resistor_id    INTEGER REFERENCES component.resistors (id),
    count_resistor INTEGER,
    capacitor_id    INTEGER REFERENCES component.capacitor (id),
    count_capacitor INTEGER
);


CREATE TYPE component.USER_TYPE AS ENUM ('user','admin');
CREATE TYPE component.UNIT_RESISTOR AS ENUM ('mOhms','Ohms','kOhms','MOhms');
CREATE TYPE component.UNIT_CAPACITOR AS ENUM ('pF','nF','uF','F');

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

INSERT INTO component.resistors
VALUES (49, 'Ohms', '0.1W', 1, 1, 0.10),
       (120, 'Ohms', '0.25W', 3, 2, 0.11),
       (300, 'Ohms', '0.1W', 2, 4, 0.20),
       (1, 'kOhms', '0.1W', 2, 1, 0.14),
       (10, 'kOhms', '0.1W', 2, 1, 0.09);


DROP TABLE component.orders;
DROP TABLE component.users;
DROP TABLE component.capacitor;
DROP TABLE component.resistors;
DROP TABLE component.properties;
DROP TABLE component.cases;
DROP TABLE component.operating_temperatures;