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
  (5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'Boeing 777', '2022-05-05', 900.00, 'Boeing');

-- Inserting data into fc_aircraft_seats table
INSERT INTO fc_aircraft_seats ("id", "class", "quantity", "price_modifier", "aircraft_id", "created", "created_by", "modified", "modified_by", "status")
VALUES
  (1, 'Economy', 150, 1.0, 1, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active'),
  (2, 'Business', 30, 1.5, 2, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active'),
  (3, 'First Class', 10, 2.0, 3, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active'),
  (4, 'Economy', 120, 1.0, 4, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active'),
  (5, 'Business', 25, 1.5, 5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active');

-- Inserting data into fc_airport table
INSERT INTO fc_airport ("id", "created", "created_by", "modified", "modified_by", "status", "iata_code", "icao_code", "name", "city", "country")
VALUES
  (1, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'JFK', 'KJFK', 'John F. Kennedy International Airport', 'New York', 'United States'),
  (2, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'LHR', 'EGLL', 'Heathrow Airport', 'London', 'United Kingdom'),
  (3, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'CDG', 'LFPG', 'Charles de Gaulle Airport', 'Paris', 'France'),
  (4, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'HND', 'RJTT', 'Haneda Airport', 'Tokyo', 'Japan'),
  (5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 'SYD', 'YSSY', 'Sydney Airport', 'Sydney', 'Australia');

-- Inserting data into fc_staff table
INSERT INTO fc_staff ("id", "created", "created_by", "modified", "modified_by", "status", "employee_id", "valid_from", "valid_to", "aircraft_id")
VALUES
  (1, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1001, '2022-01-01', NULL, 1),
  (2, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1002, '2022-02-15', NULL, 2),
  (3, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1003, '2022-03-20', NULL, 3),
  (4, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1004, '2022-04-10', NULL, 4),
  (5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 1005, '2022-05-05', NULL, 5);

-- Inserting data into fc_route table
INSERT INTO fc_route ("id", "created", "created_by", "modified", "modified_by", "status", "distance", "duration", "note", "departure_airport_id", "arrival_airport_id", "aircraft_id")
VALUES
  (1, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 4000, 300, 'Direct flight', 1, 2, 1),
  (2, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 5500, 420, '1 layover at CDG', 3, 5, 2),
  (3, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 7000, 540, '1 layover at HND', 4, 1, 3),
  (4, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 3000, 240, 'Direct flight', 2, 4, 4),
  (5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', 9000, 720, '2 layovers at CDG and HND', 5, 3, 5);

-- Inserting data into fc_flight table
INSERT INTO fc_flight ("id", "created", "created_by", "modified", "modified_by", "status", "arrival", "departure", "base_price", "route_id")
VALUES
  (1, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', '2023-01-15 15:30:00', '2023-01-15 10:00:00', 300.00, 1),
  (2, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', '2023-02-20 20:45:00', '2023-02-20 14:30:00', 400.00, 2),
  (3, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', '2023-03-25 08:15:00', '2023-03-25 02:30:00', 500.00, 3),
  (4, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', '2023-04-10 12:45:00', '2023-04-10 08:00:00', 350.00, 4),
  (5, CURRENT_TIMESTAMP, 'Admin', NULL, NULL, 'Active', '2023-05-05 18:00:00', '2023-05-05 12:30:00', 600.00, 5);
