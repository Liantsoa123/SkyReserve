CREATE TABLE _user_
(
    user_id  SERIAL,
    name     VARCHAR(250) NOT NULL,
    role     VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE city
(
    city_id   SERIAL,
    city_name VARCHAR(250) NOT NULL,
    country   VARCHAR(250) NOT NULL,
    PRIMARY KEY (city_id)
);

CREATE TABLE seat_type
(
    seat_type_id SERIAL,
    type_name    VARCHAR(250) NOT NULL,
    PRIMARY KEY (seat_type_id)
);

CREATE TABLE plane
(
    plane_id         SERIAL,
    model            VARCHAR(250) NOT NULL,
    manufacture_date DATE         NOT NULL,
    PRIMARY KEY (plane_id)
);

CREATE TABLE flight
(
    flight_id         SERIAL,
    departure_date    TIMESTAMP NOT NULL,
    arrival_date      TIMESTAMP NOT NULL,
    plane_id          INTEGER   NOT NULL,
    departure_city_id INTEGER   NOT NULL,
    arrival_city_id   INTEGER   NOT NULL,
    PRIMARY KEY (flight_id),
    FOREIGN KEY (plane_id) REFERENCES plane (plane_id),
    FOREIGN KEY (departure_city_id) REFERENCES city (city_id),
    FOREIGN KEY (arrival_city_id) REFERENCES city (city_id)
);

CREATE TABLE reservation_status
(
    resrvation_status_id SERIAL,
    reseravtion_name     VARCHAR(250) NOT NULL,
    PRIMARY KEY (resrvation_status_id)
);

CREATE TABLE number_promotion
(
    number_promotion_id SERIAL,
    number              INTEGER NOT NULL default 0,
    flight_id           INTEGER NOT NULL,
    PRIMARY KEY (number_promotion_id),
    FOREIGN KEY (flight_id) REFERENCES flight (flight_id)
);

CREATE TABLE reservation
(
    reservation_id       SERIAL,
    reservation_date     TIMESTAMP NOT NULL,
    seats_number         INTEGER   NOT NULL default 1,
    has_promotion        BOOLEAN   NOT NULL default false,
    resrvation_status_id INTEGER   NOT NULL,
    seat_type_id         INTEGER   NOT NULL,
    flight_id            INTEGER   NOT NULL,
    user_id              INTEGER   NOT NULL,
    PRIMARY KEY (reservation_id),
    FOREIGN KEY (resrvation_status_id) REFERENCES reservation_status (resrvation_status_id),
    FOREIGN KEY (seat_type_id) REFERENCES seat_type (seat_type_id),
    FOREIGN KEY (flight_id) REFERENCES flight (flight_id),
    FOREIGN KEY (user_id) REFERENCES _user_ (user_id)
);

CREATE TABLE plane_seats
(
    seat_type_id INTEGER,
    plane_id     INTEGER,
    number       INTEGER NOT NULL Default 0,
    PRIMARY KEY (seat_type_id, plane_id),
    FOREIGN KEY (seat_type_id) REFERENCES seat_type (seat_type_id),
    FOREIGN KEY (plane_id) REFERENCES plane (plane_id)
);

CREATE TABLE price_info
(
    seat_type_id        INTEGER,
    flight_id           INTEGER,
    unit_price          NUMERIC(15, 2) NOT NULL default 0,
    discount_percentage NUMERIC(15, 2) NOT NULL default 0,
    number              INTEGER        NOT NULL default 0,
    PRIMARY KEY (seat_type_id, flight_id),
    FOREIGN KEY (seat_type_id) REFERENCES seat_type (seat_type_id),
    FOREIGN KEY (flight_id) REFERENCES flight (flight_id)
);
