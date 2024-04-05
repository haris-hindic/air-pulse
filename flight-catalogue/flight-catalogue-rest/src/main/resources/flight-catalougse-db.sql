/*============================================================================*/
/* DBMS: PostgreSQL 15*/
/* Created on : 11/26/2023 11:56:39                                           */
/*============================================================================*/


/*============================================================================*/
/*                                  TABLES                                    */
/*============================================================================*/
CREATE TABLE fc_aircraft ( 
  "id"                INT8 NOT NULL,
  "created"           TIMESTAMP(2) NOT NULL,
  "created_by"        VARCHAR(60) NOT NULL,
  "modified"          TIMESTAMP(2),
  "modified_by"       VARCHAR(60),
  "status"            VARCHAR(60) NOT NULL,
  "model"             VARCHAR(100) NOT NULL,
  "build_date"        TIMESTAMP(2) NOT NULL,
  "average_speed"     numeric(10,2) NOT NULL,
  "manufacturer"      VARCHAR(60) NOT NULL,
  "image"      		  VARCHAR(100),
CONSTRAINT fc_aircraft_pk PRIMARY KEY ("id")
) 
;


CREATE TABLE "fc_aircraft_seats" ( 
  "id"                 INT8 NOT NULL,
  "class"              VARCHAR(60) NOT NULL,
  "quantity"           int8 NOT NULL,
  "price_modifier"     NUMERIC(10,2) NOT NULL,
  "aircraft_id"        INT8 NOT NULL,
  "created"            TIMESTAMP(2) NOT NULL,
  "created_by"         VARCHAR(40) NOT NULL,
  "modified"           TIMESTAMP(2),
  "modified_by"        VARCHAR(40),
  "status"             VARCHAR(40) NOT NULL,
CONSTRAINT "fc_aircraft_seats_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "fc_airport" ( 
  "id"              INT8 NOT NULL,
  "created"         TIMESTAMP(2) NOT NULL,
  "created_by"      VARCHAR(40) NOT NULL,
  "modified"        TIMESTAMP(2),
  "modified_by"     VARCHAR(40),
  "status"          VARCHAR(40) NOT NULL,
  "iata_code"       VARCHAR(3) NOT NULL,
  "icao_code"       VARCHAR(4) NOT NULL,
  "name"            VARCHAR(100) NOT NULL,
  "city"            VARCHAR(100) NOT NULL,
  "country"         VARCHAR(100) NOT NULL,
CONSTRAINT "fc_airport_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "fc_staff" ( 
  "id"              INT8 NOT NULL,
  "created"         TIMESTAMP(2) NOT NULL,
  "created_by"      VARCHAR(40) NOT NULL,
  "modified"        TIMESTAMP(2),
  "modified_by"     VARCHAR(40),
  "status"          VARCHAR(40) NOT NULL,
  "employee_id"     INT8 NOT NULL,
  "valid_from"      TIMESTAMP(2) NOT NULL,
  "valid_to"        TIMESTAMP(2),
  "aircraft_id"     INT8 NOT NULL,
  "summary" 		varchar(100) null,
CONSTRAINT "fc_staff_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "fc_route" ( 
  "id"                       INT8 NOT NULL,
  "created"                  TIMESTAMP(2) NOT NULL,
  "created_by"               VARCHAR(40) NOT NULL,
  "modified"                 TIMESTAMP(2),
  "modified_by"              VARCHAR(40),
  "status"                   VARCHAR(40) NOT NULL,
  "distance"                 int8 NOT NULL,
  "duration"                 int8 NOT NULL,
  "note"                     VARCHAR(4000),
  "departure_airport_id"     INT8 NOT NULL,
  "arrival_airport_id"       INT8 NOT NULL,
  "aircraft_id"              INT8 NOT NULL,
CONSTRAINT "fc_route_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "fc_flight" ( 
  "id"              INT8 NOT NULL,
  "created"         TIMESTAMP(2) NOT NULL,
  "created_by"      VARCHAR(40) NOT NULL,
  "modified"        TIMESTAMP(2),
  "modified_by"     VARCHAR(40),
  "status"          VARCHAR(40) NOT NULL,
  "arrival"         TIMESTAMP(2) NOT NULL,
  "departure"       TIMESTAMP(2) NOT NULL,
  "base_price"      NUMERIC(10,2) NOT NULL,
  "route_id"        INT8 NOT NULL,
CONSTRAINT "fc_flight_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "fc_flight_booking" ( 
  "id"              INT8 NOT NULL,
  "created"         TIMESTAMP(2) NOT NULL,
  "created_by"      VARCHAR(40) NOT NULL,
  "modified"        TIMESTAMP(2),
  "modified_by"     VARCHAR(40),
  "status"          VARCHAR(40) NOT NULL,
  "luggage"          VARCHAR(100) NOT NULL,
  "seatClass"          VARCHAR(100) NOT NULL,
  "user_id"       TIMESTAMP(2) NOT NULL,
  "total_price"      NUMERIC(10,2) NOT NULL,
  "flight_id"        INT8 NOT NULL,
  "return_flight_id"        INT8 NULL,
CONSTRAINT "fc_flight_booking_pk" PRIMARY KEY ("id")
) 
;


/*============================================================================*/
/*                               FOREIGN KEYS                                 */
/*============================================================================*/
ALTER TABLE "fc_aircraft_seats"
    ADD CONSTRAINT "ft_aircraft_seats_aircraft"
        FOREIGN KEY ("aircraft_id")
            REFERENCES "fc_aircraft" ("id")
 ;
 
ALTER TABLE "fc_staff"
    ADD CONSTRAINT "fk_staff_aircraft"
        FOREIGN KEY ("aircraft_id")
            REFERENCES "fc_aircraft" ("id")
 ;
 
ALTER TABLE "fc_route"
    ADD CONSTRAINT "fk_route_airport_departure"
        FOREIGN KEY ("departure_airport_id")
            REFERENCES "fc_airport" ("id")
 ;
 
ALTER TABLE "fc_route"
    ADD CONSTRAINT "fk_route_airport_arrival"
        FOREIGN KEY ("arrival_airport_id")
            REFERENCES "fc_airport" ("id")
 ;
 
ALTER TABLE "fc_route"
    ADD CONSTRAINT "fk_route_aircraft"
        FOREIGN KEY ("aircraft_id")
            REFERENCES "fc_aircraft" ("id")
 ;
                          
ALTER TABLE "fc_flight"
    ADD CONSTRAINT "fk_flight_route"
        FOREIGN KEY ("route_id")
            REFERENCES "fc_route" ("id")
 ;

ALTER TABLE "fc_flight_booking"
    ADD CONSTRAINT "fc_flight_booking_depart_flight"
        FOREIGN KEY ("flight_id")
            REFERENCES "fc_flight" ("id")
 ;
ALTER TABLE "fc_flight_booking"
    ADD CONSTRAINT "fc_flight_booking_return_flight"
        FOREIGN KEY ("return_flight_id")
            REFERENCES "fc_flight" ("id")
;
/*============================================================================*/
/*                               SEQUENCES                                    */
/*============================================================================*/

CREATE SEQUENCE fc_route_seq
	INCREMENT BY 1
	MINVALUE 11
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE fc_staff_seq
	INCREMENT BY 1
	MINVALUE 11
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE fc_flight_seq
	INCREMENT BY 1
	MINVALUE 11
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE fc_aircraft_seq
	INCREMENT BY 1
	MINVALUE 11
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE fc_aircraft_seats_seq
	INCREMENT BY 1
	MINVALUE 11
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE fc_airport_seq
	INCREMENT BY 1
	MINVALUE 11
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;
 
CREATE SEQUENCE fc_flight_booking_seq
	INCREMENT BY 1
	MINVALUE 11
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;
 /*============================================================================*/
/*                               SAMPLE DATA                                 */
/*============================================================================*/

-- Inserting data into fc_aircraft table
INSERT INTO fc_aircraft ("id", "created", "created_by", "modified", "modified_by", "status", "model", "build_date", "average_speed", "manufacturer")
VALUES
  (1, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'Boeing 737', '2022-01-01', 800.00, 'Boeing'),
  (2, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'Airbus A320', '2022-02-15', 850.00, 'Airbus'),
  (3, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'Embraer E190', '2022-03-20', 750.00, 'Embraer'),
  (4, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'Bombardier CRJ-900', '2022-04-10', 700.00, 'Bombardier'),
  (5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'Boeing 777', '2022-05-05', 900.00, 'Boeing')
  (6, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Boeing 737-800', '2022-05-05', 514.44, 'Boeing', 'https://example.com/boeing_737.jpg'),
  (7, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Airbus A330', '2022-01-01', 515.00, 'Airbus', 'https://example.com/airbus_a320.jpg'),
  (8, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Boeing 787 Dreamliner', '2022-04-10', 954.80, 'Boeing', 'https://example.com/boeing_787.jpg'),
  (9, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Airbus A380', '2022-01-01', 903.00, 'Airbus', 'https://example.com/airbus_a380.jpg'),
  (10, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Boeing 747', '2022-04-10', 905.00, 'Boeing', 'https://example.com/boeing_777.jpg'),
  (11, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Airbus A350', '2022-05-05', 930.00, 'Airbus', 'https://example.com/airbus_a350.jpg'),
  (12, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Boeing 737 MAX 8', '2022-04-10', 518.00, 'Boeing', 'https://example.com/boeing_737_max.jpg'),
  (13, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Airbus A330', '2022-03-20', 871.00, 'Airbus', 'https://example.com/airbus_a330.jpg'),
  (14, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'Embraer E195', '2022-05-05', 828.00, 'Embraer', 'https://example.com/embraer_e195.jpg');

-- Inserting data into fc_aircraft_seats table
INSERT INTO fc_aircraft_seats ("id", "class", "quantity", "price_modifier", "aircraft_id", "created", "created_by", "modified", "modified_by", "status")
VALUES
  (1, 'Economy', 150, 1.0, 1, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active'),
  (2, 'Business', 30, 1.5, 2, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active'),
  (3, 'First Class', 10, 2.0, 3, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active'),
  (4, 'Economy', 120, 1.0, 4, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active'),
  (5, 'Business', 25, 1.5, 5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active');


-- Inserting data into fc_staff table
INSERT INTO fc_staff ("id", "created", "created_by", "modified", "modified_by", "status", "employee_id", "valid_from", "valid_to", "aircraft_id")
VALUES
  (1, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1001, '2022-01-01', NULL, 1),
  (2, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1002, '2022-02-15', NULL, 2),
  (3, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1003, '2022-03-20', NULL, 3),
  (4, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1004, '2022-04-10', NULL, 4),
  (5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1005, '2022-05-05', NULL, 5);

INSERT INTO testing.fc_airport (id, created, created_by, modified, modified_by, status, iata_code, icao_code, "name", city, country) 
VALUES 
(1, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'LHR', 'EGLL', 'London Heathrow Airport', 'London', 'United Kingdom'),
(2, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'CDG', 'LFPG', 'Charles de Gaulle Airport', 'Paris', 'France'),
(3, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'FRA', 'EDDF', 'Frankfurt Airport', 'Frankfurt', 'Germany'),
(4, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'AMS', 'EHAM', 'Amsterdam Airport Schiphol', 'Amsterdam', 'Netherlands'),
(5, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'BCN', 'LEBL', 'Barcelona-El Prat Airport', 'Barcelona', 'Spain'),
(6, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'FCO', 'LIRF', 'Leonardo da Vinci–Fiumicino Airport', 'Rome', 'Italy'),
(7, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'MUC', 'EDDM', 'Munich Airport', 'Munich', 'Germany'),
(8, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'ZRH', 'LSZH', 'Zurich Airport', 'Zurich', 'Switzerland'),
(9, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'CPH', 'EKCH', 'Copenhagen Airport', 'Copenhagen', 'Denmark'),
(10, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 'ATH', 'LGAV', 'Athens International Airport', 'Athens', 'Greece');


INSERT INTO testing.fc_route (id, created, created_by, modified, modified_by, status, distance, duration, note, departure_airport_id, arrival_airport_id, aircraft_id)
VALUES
(1, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 350, 90, 'Short-haul flight', 1, 2, 1),
(2, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 650, 150, 'Medium-haul flight', 2, 1, 2),
(3, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 1200, 180, 'Long-haul flight', 2, 3, 3),
(4, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 450, 120, 'Short-haul flight', 3, 2, 4),
(5, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 900, 200, 'Medium-haul flight', 4, 5, 5),
(6, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 1700, 240, 'Long-haul flight', 5, 4, 6),
(7, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 400, 100, 'Short-haul flight', 5, 6, 7),
(8, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 800, 180, 'Medium-haul flight', 6, 5, 8),
(9, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 1500, 220, 'Long-haul flight', 7, 8, 9),
(10, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 300, 90, 'Short-haul flight', 8, 7, 10),
(11, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 700, 160, 'Medium-haul flight', 8, 9, 11),
(12, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 1400, 210, 'Long-haul flight', 9, 8, 12),
(13, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 500, 120, 'Short-haul flight', 9, 10, 13),
(14, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 1000, 180, 'Medium-haul flight', 10, 9, 14),
(15, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 1800, 240, 'Long-haul flight', 1, 5, 5),
(16, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 350, 100, 'Short-haul flight', 5, 1, 6),
(17, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 750, 170, 'Medium-haul flight', 4, 7, 7),
(18, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 1600, 220, 'Long-haul flight', 7, 4, 8),
(19, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 400, 110, 'Short-haul flight', 2, 9, 9),
(20, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', 850, 190, 'Medium-haul flight', 9, 3, 10);

-- Route 1
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(1, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-15 08:00:00', '2024-06-15 06:00:00', 150.00, 1),
(2, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-16 10:00:00', '2024-06-16 08:00:00', 180.00, 1);

-- Route 2
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(3, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-17 12:00:00', '2024-06-17 10:00:00', 200.00, 2),
(4, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-18 14:00:00', '2024-06-18 12:00:00', 220.00, 2),
(5, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-19 16:00:00', '2024-06-19 14:00:00', 250.00, 2);

-- Route 3
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(6, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-20 18:00:00', '2024-06-20 16:00:00', 180.00, 3),
(7, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-21 20:00:00', '2024-06-21 18:00:00', 220.00, 3);

-- Route 4
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(8, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-22 08:00:00', '2024-06-22 06:00:00', 200.00, 4),
(9, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-23 10:00:00', '2024-06-23 08:00:00', 220.00, 4),
(10, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-24 12:00:00', '2024-06-24 10:00:00', 240.00, 4);

-- Route 5
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(11, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-25 14:00:00', '2024-06-25 12:00:00', 180.00, 5),
(12, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-26 16:00:00', '2024-06-26 14:00:00', 220.00, 5);

-- Route 6
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(13, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-27 18:00:00', '2024-06-27 16:00:00', 240.00, 6),
(14, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-28 20:00:00', '2024-06-28 18:00:00', 260.00, 6);

-- Route 7
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(15, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-29 22:00:00', '2024-06-29 20:00:00', 200.00, 7),
(16, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-06-30 12:00:00', '2024-06-30 10:00:00', 240.00, 7);

-- Route 8
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(17, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-01 14:00:00', '2024-07-01 12:00:00', 220.00, 8),
(18, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-02 16:00:00', '2024-07-02 14:00:00', 260.00, 8);

-- Route 9
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(19, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-03 18:00:00', '2024-07-03 16:00:00', 240.00, 9),
(20, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-04 20:00:00', '2024-07-04 18:00:00', 280.00, 9);

-- Route 10
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(21, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-05 22:00:00', '2024-07-05 20:00:00', 260.00, 10),
(22, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-06 12:00:00', '2024-07-06 10:00:00', 300.00, 10);

-- Route 11
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(23, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-07 14:00:00', '2024-07-07 12:00:00', 240.00, 11),
(24, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-08 16:00:00', '2024-07-08 14:00:00', 280.00, 11);

-- Route 12
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(25, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-09 18:00:00', '2024-07-09 16:00:00', 260.00, 12),
(26, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-10 20:00:00', '2024-07-10 18:00:00', 300.00, 12);

-- Route 13
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(27, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-11 22:00:00', '2024-07-11 20:00:00', 280.00, 13),
(28, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-12 12:00:00', '2024-07-12 10:00:00', 320.00, 13);

-- Route 14
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(29, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-13 14:00:00', '2024-07-13 12:00:00', 260.00, 14),
(30, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-14 16:00:00', '2024-07-14 14:00:00', 300.00, 14);

-- Route 15
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(31, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-15 18:00:00', '2024-07-15 16:00:00', 280.00, 15),
(32, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-16 20:00:00', '2024-07-16 18:00:00', 320.00, 15);

-- Route 16
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(33, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-17 22:00:00', '2024-07-17 20:00:00', 300.00, 16),
(34, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-18 12:00:00', '2024-07-18 10:00:00', 340.00, 16);

-- Route 17
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(35, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-19 14:00:00', '2024-07-19 12:00:00', 280.00, 17),
(36, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-20 16:00:00', '2024-07-20 14:00:00', 320.00, 17);

-- Route 18
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(37, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-21 18:00:00', '2024-07-21 16:00:00', 300.00, 18),
(38, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-22 20:00:00', '2024-07-22 18:00:00', 340.00, 18);

-- Route 19
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(39, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-23 22:00:00', '2024-07-23 20:00:00', 320.00, 19),
(40, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-24 12:00:00', '2024-07-24 10:00:00', 360.00, 19);

-- Route 20
INSERT INTO testing.fc_flight (id, created, created_by, modified, modified_by, status, arrival, departure, base_price, route_id)
VALUES
(41, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-25 14:00:00', '2024-07-25 12:00:00', 300.00, 20),
(42, CURRENT_TIMESTAMP, 'admin', NULL, NULL, 'Active', '2024-07-26 16:00:00', '2024-07-26 14:00:00', 340.00, 20);

