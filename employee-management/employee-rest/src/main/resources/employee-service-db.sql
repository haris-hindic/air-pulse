/*============================================================================*/
/* DBMS: PostgreSQL 15*/
/* Created on : 10/01/2023 10:14:15 PM                                           */
/*============================================================================*/


/*============================================================================*/
/*                                  TABLES                                    */
/*============================================================================*/
CREATE TABLE "ems_employee" ( 
  "id"                BIGINT NOT NULL,
  "first_name"        VARCHAR(100) NOT NULL,
  "last_name"         VARCHAR(100) NOT NULL,
  "date_of_birth"     TIMESTAMP(2) NOT NULL,
  "phone_number"      VARCHAR(100) NOT NULL,
  "email"             VARCHAR(100) NOT NULL,
  "gender"            VARCHAR(50),
  "created"           TIMESTAMP(2) NOT NULL,
  "created_by"        VARCHAR(100) NOT NULL,
  "modified"          TIMESTAMP(2),
  "modified_by"       VARCHAR(100),
  "status"            VARCHAR(100) NOT NULL,
CONSTRAINT "ems_employee_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "ems_qualification" ( 
  "id"                  BIGINT NOT NULL,
  "education_level"     VARCHAR(100) NOT NULL,
  "languages"           VARCHAR(4000),
  "skills"              VARCHAR(4000),
  "licences"            VARCHAR(4000),
  "created"             TIMESTAMP(2) NOT NULL,
  "created_by"          VARCHAR(100) NOT NULL,
  "modified"            TIMESTAMP(2),
  "modified_by"         VARCHAR(100),
  "status"              VARCHAR(100) NOT NULL,
  "employee_id"         BIGINT NOT NULL,
CONSTRAINT "ems_qualification_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "ems_job_type" ( 
  "id"                   BIGINT NOT NULL,
  "title"                VARCHAR(100) NOT NULL,
  "responsibilities"     VARCHAR(2000) NOT NULL,
  "salary_min"           NUMERIC(19,2) NOT NULL,
  "salary_max"           NUMERIC(19,2) NOT NULL,
  "created"              TIMESTAMP(2) NOT NULL,
  "created_by"           VARCHAR(100) NOT NULL,
  "modified"             TIMESTAMP(2),
  "modified_by"          VARCHAR(100),
  "status"               VARCHAR(100) NOT NULL,
CONSTRAINT "ems_job_type_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "ems_position" ( 
  "id"              BIGINT NOT NULL,
  "start_date"      TIMESTAMP(2) NOT NULL,
  "end_date"        TIMESTAMP(2) NULL,
  "salary"          NUMERIC(19,2) NOT NULL,
  "created"         TIMESTAMP(2) NOT NULL,
  "created_by"      VARCHAR(100) NOT NULL,
  "modified"        TIMESTAMP(2),
  "modified_by"     VARCHAR(100),
  "status"          VARCHAR(100) NOT NULL,
  "employee_id"     BIGINT NOT NULL,
  "job_type_id"     BIGINT NOT NULL,
CONSTRAINT "ems_position_pk" PRIMARY KEY ("id")
) 
;

CREATE TABLE "ems_absence" ( 
  "id"              BIGINT NOT NULL,
  "type"            VARCHAR(200) NOT NULL,
  "start_date"      TIMESTAMP(2) NOT NULL,
  "end_date"        TIMESTAMP(2) NOT NULL,
  "reason"          VARCHAR(200),
  "comments"        VARCHAR(500),
  "status"          VARCHAR(100) NOT NULL,
  "created"         TIMESTAMP(2) NOT NULL,
  "created_by"      VARCHAR(100) NOT NULL,
  "modified"        TIMESTAMP(2),
  "modified_by"     VARCHAR(100),
  "employee_id"     BIGINT NOT NULL,
CONSTRAINT "ems_absence_pk" PRIMARY KEY ("id")
) 
;

/*============================================================================*/
/*                               FOREIGN KEYS                                 */
/*============================================================================*/
ALTER TABLE "ems_qualification"
    ADD CONSTRAINT "ems_qualification_ems_employee_fk"
        FOREIGN KEY ("employee_id")
            REFERENCES "ems_employee" ("id")
 ;
 
ALTER TABLE "ems_position"
    ADD CONSTRAINT "ems_position_ems_job_type_fk"
        FOREIGN KEY ("job_type_id")
            REFERENCES "ems_job_type" ("id")
 ;
 
ALTER TABLE "ems_position"
    ADD CONSTRAINT "ems_position_ems_employee_fk"
        FOREIGN KEY ("employee_id")
            REFERENCES "ems_employee" ("id")
 ;
 
ALTER TABLE "ems_absence"
    ADD CONSTRAINT "ems_absence_ems_employee_fk"
        FOREIGN KEY ("employee_id")
            REFERENCES "ems_employee" ("id")
 ;
 
/*============================================================================*/
/*                               SEQUENCES                                    */
/*============================================================================*/

CREATE SEQUENCE ems_absence_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;

CREATE SEQUENCE ems_position_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;
	
CREATE SEQUENCE ems_job_type_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;
	
CREATE SEQUENCE ems_qualification_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;
	
CREATE SEQUENCE ems_employee_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 11
	CACHE 1
	NO CYCLE;
	
/*============================================================================*/
/*                               TEST DATA                                    */
/*============================================================================*/

INSERT INTO testing.ems_employee (id, first_name, last_name, date_of_birth, phone_number, email, gender, created, created_by, status)
VALUES 
(nextval('ems_employee_seq'), 'John', 'Doe', '1990-05-15 00:00:00', '123-456-7890', 'john.doe@example.com', 'Male', CURRENT_TIMESTAMP, 'System', 'Active'),
(nextval('ems_employee_seq'), 'Jane', 'Smith', '1988-08-20 00:00:00', '987-654-3210', 'jane.smith@example.com', 'Female', CURRENT_TIMESTAMP, 'System', 'Active'),
(nextval('ems_employee_seq'), 'Michael', 'Johnson', '1995-03-10 00:00:00', '555-555-5555', 'michael.johnson@example.com', 'Male', CURRENT_TIMESTAMP, 'System', 'Active'),
(nextval('ems_employee_seq'), 'Sarah', 'Williams', '1992-12-25 00:00:00', '111-222-3333', 'sarah.williams@example.com', 'Female', CURRENT_TIMESTAMP, 'System', 'Active'),
(nextval('ems_employee_seq'), 'Kevin', 'Brown', '1985-07-18 00:00:00', '444-444-4444', 'kevin.brown@example.com', 'Male', CURRENT_TIMESTAMP, 'System', 'Active');


INSERT INTO testing.ems_absence (id, "type", start_date, end_date, reason, comments, status, created, created_by, employee_id)
VALUES 
(nextval('ems_absence_seq'), 'Vacation', '2023-10-01 00:00:00', '2023-10-05 00:00:00', 'Annual leave', 'Vacation trip', 'Approved', CURRENT_TIMESTAMP, 'System', 1),
(nextval('ems_absence_seq'), 'Sick Leave', '2023-09-15 00:00:00', '2023-09-16 00:00:00', 'Flu', 'Doctors recommendation', 'Approved', CURRENT_TIMESTAMP, 'System', 2),
(nextval('ems_absence_seq'), 'Vacation', '2023-11-20 00:00:00', '2023-11-25 00:00:00', 'Holiday break', 'Family trip', 'Pending', CURRENT_TIMESTAMP, 'System', 3),
(nextval('ems_absence_seq'), 'Personal Leave', '2023-10-12 00:00:00', '2023-10-12 00:00:00', 'Family event', 'Important family gathering', 'Approved', CURRENT_TIMESTAMP, 'System', 4),
(nextval('ems_absence_seq'), 'Vacation', '2023-12-01 00:00:00', '2023-12-05 00:00:00', 'Annual leave', 'Holiday vacation', 'Approved', CURRENT_TIMESTAMP, 'System', 5);

INSERT INTO testing.ems_job_type (id, title, responsibilities, salary_min, salary_max, created, created_by, status)
VALUES 
(nextval('ems_job_type_seq'), 'Pilot', 'Operating and flying the aircraft', 60000.00, 90000.00, CURRENT_TIMESTAMP, 'System', 'Active'),
(nextval('ems_job_type_seq'), 'Flight Attendant', 'Ensure the safety, comfort, and well-being of passengers during flights', 70000.00, 110000.00, CURRENT_TIMESTAMP, 'System', 'Active'),
(nextval('ems_job_type_seq'), 'Mechanic/Technician', 'Responsible for inspecting, repairing, and maintaining aircraft to ensure they meet safety and regulatory standards', 50000.00, 80000.00, CURRENT_TIMESTAMP, 'System', 'Active'),
(nextval('ems_job_type_seq'), 'Customer Service Representative', 'Assist passengers with various aspects of their travel', 55000.00, 85000.00, CURRENT_TIMESTAMP, 'System', 'Active'),
(nextval('ems_job_type_seq'), 'Air Traffic Controller', 'Coordinating the movement of aircraft in airspace and at airports', 58000.00, 95000.00, CURRENT_TIMESTAMP, 'System', 'Active');

INSERT INTO testing.ems_position (id, start_date, end_date, salary, created, created_by, status, employee_id, job_type_id)
VALUES 
(nextval('ems_position_seq'), '2023-10-01 00:00:00', NULL, 75000.00, CURRENT_TIMESTAMP, 'System', 'Active', 1, 1),
(nextval('ems_position_seq'), '2023-09-01 00:00:00', NULL, 80000.00, CURRENT_TIMESTAMP, 'System', 'Active', 2, 2),
(nextval('ems_position_seq'), '2023-08-01 00:00:00', NULL, 60000.00, CURRENT_TIMESTAMP, 'System', 'Active', 3, 3),
(nextval('ems_position_seq'), '2023-07-01 00:00:00', NULL, 65000.00, CURRENT_TIMESTAMP, 'System', 'Active', 4, 4),
(nextval('ems_position_seq'), '2023-06-01 00:00:00', NULL, 70000.00, CURRENT_TIMESTAMP, 'System', 'Active', 5, 5);


INSERT INTO testing.ems_qualification (id, education_level, languages, skills, licences, created, created_by, status, employee_id)
VALUES 
(nextval('ems_qualification_seq'), 'Bachelor Degree', 'English, Spanish', 'Java, Python, SQL', 'Microsoft Certified Professional', CURRENT_TIMESTAMP, 'System', 'Active', 1),
(nextval('ems_qualification_seq'), 'Master Degree', 'English, French', 'C++, R, Data Analysis', 'Project Management Professional', CURRENT_TIMESTAMP, 'System', 'Active', 2),
(nextval('ems_qualification_seq'), 'Associate Degree', 'English', 'HTML, CSS, JavaScript', 'None', CURRENT_TIMESTAMP, 'System', 'Active', 3),
(nextval('ems_qualification_seq'), 'High School Diploma', 'English, Spanish', 'Customer Service, Sales', 'None', CURRENT_TIMESTAMP, 'System', 'Active', 4),
(nextval('ems_qualification_seq'), 'Doctoral Degree', 'English, German', 'Research, Writing, Analysis', 'Doctor of Medicine', CURRENT_TIMESTAMP, 'System', 'Active', 5);