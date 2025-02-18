INSERT INTO seat_type (type_name)
VALUES ('Economy'),
       ('Premium Economy'),
       ('Business'),
       ('First Class');

INSERT INTO city (city_name, country)
VALUES ('New York', 'United States'),
       ('Paris', 'France'),
       ('Tokyo', 'Japan'),
       ('London', 'United Kingdom'),
       ('Dubai', 'United Arab Emirates'),
       ('Sydney', 'Australia'),
       ('Berlin', 'Germany'),
       ('Toronto', 'Canada'),
       ('São Paulo', 'Brazil'),
       ('Johannesburg', 'South Africa');

INSERT INTO plane (model, manufacture_date)
VALUES ('Boeing 737', '2018-05-20'),
       ('Airbus A320', '2019-07-15'),
       ('Boeing 787 Dreamliner', '2020-03-10'),
       ('Airbus A380', '2017-11-05'),
       ('Embraer E190', '2021-06-25'),
       ('Boeing 777', '2016-09-30'),
       ('Airbus A350', '2022-02-18'),
       ('Bombardier CRJ900', '2015-04-12'),
       ('Cessna Citation X', '2014-12-08'),
       ('Gulfstream G650', '2023-01-29');

INSERT INTO plane_seats (seat_type_id, plane_id, number)
VALUES (1, 1, 150),
       (2, 1, 30),
       (3, 1, 20),
       (1, 2, 160),
       (3, 2, 25),
       (4, 3, 10),
       (1, 3, 200),
       (2, 3, 40),
       (3, 3, 30),
       (1, 4, 400),
       (2, 4, 80),
       (3, 4, 60),
       (4, 4, 20),
       (1, 5, 90),
       (3, 5, 15),
       (1, 6, 220),
       (2, 6, 50),
       (3, 6, 40),
       (4, 6, 10);

