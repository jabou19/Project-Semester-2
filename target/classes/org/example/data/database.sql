DROP VIEW IF EXISTS full_credits;
DROP VIEW IF EXISTS producers;
DROP TABLE IF EXISTS credit;
DROP TABLE IF EXISTS production;
DROP TABLE IF EXISTS person;


CREATE TABLE person
(
    id           SERIAL PRIMARY KEY UNIQUE,
    name         VARCHAR NOT NULL,
    email        VARCHAR UNIQUE,
    phone_number VARCHAR(8) UNIQUE
);

CREATE TABLE production
(
    id    SERIAL PRIMARY KEY UNIQUE,
    title VARCHAR UNIQUE NOT NULL
);

CREATE TABLE credit
(
    person_id     INT REFERENCES person (id) ON DELETE CASCADE,
    production_id INT REFERENCES production (id) ON DELETE CASCADE,
    role          VARCHAR NOT NULL,
    PRIMARY KEY (person_id, production_id)
);

INSERT INTO person
VALUES (DEFAULT, 'Lise Lisensen', 'lise@mail.com', '11223344');
INSERT INTO person
VALUES (DEFAULT, 'Mogens Mogensen', 'Mogens@mail.com', '44332211');
INSERT INTO person
VALUES (DEFAULT, 'Brita Britansen', 'Brita@mail.com', '33445566');
INSERT INTO person
VALUES (DEFAULT, 'Lasse Lassensen', 'Lasse@mail.com', '22334455');
INSERT INTO person
VALUES (DEFAULT, 'Gertrud Gertrudsen', 'Gertrud@mail.com', '44556677');
INSERT INTO person
VALUES (DEFAULT, 'Gert Gertsen', 'gert@mail.com', '55667788');
INSERT INTO person
VALUES (DEFAULT, 'Peter', 'peter@leasy.com', '88888888');

INSERT INTO production
VALUES (DEFAULT, 'Lise''s baggård');
INSERT INTO production
VALUES (DEFAULT, 'Mogens'' baggård');

INSERT INTO credit
VALUES (1, 1, 'Producer');
INSERT INTO credit
VALUES (6, 1, 'Camera Mand');
INSERT INTO credit
VALUES (3, 1, 'Baggrunds Artist');
INSERT INTO credit
VALUES (4, 1, 'Instruktør');
INSERT INTO credit
VALUES (5, 1, 'Lyd Designer');

INSERT INTO credit
VALUES (2, 2, 'Producer');
INSERT INTO credit
VALUES (7, 2, 'Leasings Inspektør');
INSERT INTO credit
VALUES (3, 2, 'Make-up Artist');
INSERT INTO credit
VALUES (4, 2, 'Sprængnings Ekspert');
INSERT INTO credit
VALUES (5, 2, 'The dude playing a dude playing another dude');
INSERT INTO credit
VALUES (6, 2, 'VFX Artist');

DELETE
FROM person
WHERE id = 3;