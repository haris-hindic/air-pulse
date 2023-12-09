/*============================================================================*/
/* DBMS: PostgreSQL 15*/
/* Created on : 10/01/2023 10:14:15 PM                                           */
/*============================================================================*/


/*============================================================================*/
/*                                  TABLES                                    */
/*============================================================================*/

CREATE TABLE "as_user" ( 
  "id"                BIGINT NOT NULL,
  "first_name"        VARCHAR(100) NOT NULL,
  "last_name"         VARCHAR(100) NOT NULL,
  "username"     	  VARCHAR(100) NOT NULL,
  "password"     	  VARCHAR(100) NOT NULL,
  "phone_number"      VARCHAR(100) NOT NULL,
  "email"             VARCHAR(100) NOT NULL,
  "date_of_birth"     TIMESTAMP(2) NOT NULL,
  "created"           TIMESTAMP(2) NOT NULL,
  "created_by"        VARCHAR(100) NOT NULL,
  "modified"          TIMESTAMP(2),
  "modified_by"       VARCHAR(100),
  "status"            VARCHAR(100) NOT NULL,
  "image"            VARCHAR(100),
  "role"              VARCHAR(100) NOT NULL,
CONSTRAINT "as_user_pk" PRIMARY KEY ("id")
) 
;

/*============================================================================*/
/*                               SEQUENCES                                    */
/*============================================================================*/

CREATE SEQUENCE as_user_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;