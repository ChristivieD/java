/*
	FILE:	DDL_Wildlife_Sanctuary.sql
    DATE:	2023-04-28
    AUTHOR:	Kate Rich
    DESCRIPTION:
		A script file containing the code that builds the Wildlife_Sanctuary database.
        This file will also contain code the creates four stored procedures (for CRUD functions)
        for the 'Department' and 'Species' tables, as well as code that creates three triggers
        (for INSERT, UPDATE, and DELETE) on the 'Department' table.
        Also, contained in this file will be INSERT statements that insert dummy data into all
        tables, as well as code that creates two separate VIEWs.
*/

/*
	Database Creation
*/
DROP DATABASE IF EXISTS Wildlife_Sanctuary;
CREATE DATABASE Wildlife_Sanctuary;
USE Wildlife_Sanctuary;

/*
	Table Creation
*/
DROP TABLE IF EXISTS Animal_Archive;
DROP TABLE IF EXISTS Veterinary;
DROP TABLE IF EXISTS Released;
DROP TABLE IF EXISTS Captured;
DROP TABLE IF EXISTS Animal;
DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS Species;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Department_Audit;
DROP TABLE IF EXISTS Department;

CREATE TABLE Department (
	dept_id	INT	NOT NULL AUTO_INCREMENT	COMMENT 'A unique identifier for each department.'
	, dept_description VARCHAR(255) UNIQUE COMMENT 'A description of each department.'
    , PRIMARY KEY(dept_id)
) COMMENT 'A table containing information regarding the sanctuary''s different departments.'
;

CREATE TABLE Department_Audit (
	dept_id INT NOT NULL COMMENT 'An identifier for the department.'
	, dept_description VARCHAR(255) NOT NULL COMMENT 'A description of the department.'
	, action_type VARCHAR(50) NOT NULL COMMENT 'The type of action that was performed on the table.'
	, action_date DATETIME NOT NULL COMMENT 'The date and time the action was performed on the table.'
	, action_user VARCHAR(255) NOT NULL COMMENT 'The user who performed the action on the table.'
) COMMENT 'A table that will be used for performing audits on the Department table.'
;

CREATE TABLE Employee (
	emp_id INT NOT NULL AUTO_INCREMENT COMMENT 'A unique identifier for each employee.'
	, dept_id INT NOT NULL COMMENT 'An identifier that indicates which department the employee is working in.
									This references the ''dept_id'' field in the Department table.'
	, emp_first_name VARCHAR(55) NOT NULL COMMENT 'The employee''s first name.'
	, emp_last_name VARCHAR(55) NOT NULL COMMENT 'The employee''s last name.'
	, emp_phone VARCHAR(50) NOT NULL COMMENT 'The employee''s contact phone number.'
	, emp_address VARCHAR(255) NOT NULL COMMENT 'The employee''s street address.'
	, emp_city VARCHAR(35) NOT NULL COMMENT 'The city where the employee lives.'
	, emp_state CHAR(2) NOT NULL COMMENT 'The abbreviated state the employee lives in.'
	, emp_zip_code VARCHAR(10) NOT NULL COMMENT 'The employee''s zip code.'
	, PRIMARY KEY(emp_id)
    , CONSTRAINT fk_Employee_deptid
		FOREIGN KEY(dept_id)
			REFERENCES Department(dept_id)
) COMMENT 'A table containing information regarding the sanctuary''s employees.'
;

CREATE TABLE Species (
	species_id INT NOT NULL AUTO_INCREMENT COMMENT 'A unique identifier for each species type.'
	, species_description VARCHAR(255) NOT NULL COMMENT 'A description of each species type.'
	, PRIMARY KEY(species_id)
) COMMENT 'A table containing information regarding different animal species.'
;

CREATE TABLE Location (
	loc_id INT NOT NULL AUTO_INCREMENT COMMENT 'A unique identifier for each location.'
	, loc_address VARCHAR(255) NOT NULL COMMENT 'The street address of the location.'
	, loc_city VARCHAR(35) NOT NULL COMMENT 'The city the location is in.'
	, loc_state CHAR(2) NOT NULL COMMENT 'The abbreviated state the location is in.'
	, loc_zip_code VARCHAR(10) NOT NULL COMMENT 'The zip code of the location.'
	, PRIMARY KEY(loc_id)
) COMMENT 'A table containing numerous locations where animals have been captured and/or released.'
;

CREATE TABLE Animal (
	animal_id INT NOT NULL AUTO_INCREMENT COMMENT 'A unique identifier for each animal.'
	, species_id INT NOT NULL COMMENT 'An identifier that indicates the species each animal is.'
	, animal_name VARCHAR(55) NOT NULL COMMENT 'The animal''s name.'
	, animal_gender ENUM('Male', 'Female') NOT NULL COMMENT 'The animal''s gender.'
	, PRIMARY KEY(animal_id)
    , CONSTRAINT fk_Animal_speciesid
		FOREIGN KEY(species_id)
			REFERENCES Species(species_id)
) COMMENT 'A table containing basic information about each animal.'
;

CREATE TABLE Captured (
	capture_id INT NOT NULL AUTO_INCREMENT COMMENT 'A unique identifier for each capture.'
	, animal_id INT NOT NULL COMMENT 'An identifier indicating which animal has been captured.'
	, capture_emp INT NOT NULL COMMENT 'An identifier indicating which employee facilitated the capture.'
	, capture_loc INT NOT NULL COMMENT 'An identifier indicating the location where the capture took place.'
	, capture_date DATETIME NOT NULL COMMENT 'The date and time the capture took place.'
	, capture_reason TEXT NOT NULL COMMENT 'A description of why the animal was captured.'
	, PRIMARY KEY(capture_id)
    , CONSTRAINT fk_Captured_animalid
		FOREIGN KEY(animal_id)
			REFERENCES Animal(animal_id)
	, CONSTRAINT fk_Captured_captureemp
		FOREIGN KEY(capture_emp)
			REFERENCES Employee(emp_id)
	, CONSTRAINT fk_Captured_captureloc
		FOREIGN KEY(capture_loc)
			REFERENCES Location(loc_id)
) COMMENT 'A table containing information about an animal''s capture.'
;

CREATE TABLE Released (
	release_id INT NOT NULL AUTO_INCREMENT COMMENT 'A unique identifier for each release.'
	, animal_id INT NOT NULL COMMENT 'An identifier indicating which animal has been released.'
	, release_emp INT NOT NULL COMMENT 'An identifier indicating which employee facilitated the release.'
	, release_loc INT NOT NULL COMMENT 'An identifier indicating the location where the release took place.'
	, release_date DATETIME NOT NULL COMMENT 'The date and time the release took place.'
	, PRIMARY KEY(release_id)
	, CONSTRAINT fk_Released_animalid
		FOREIGN KEY(animal_id)
			REFERENCES Animal(animal_id)
	, CONSTRAINT fk_Released_releaseemp
		FOREIGN KEY(release_emp)
			REFERENCES Employee(emp_id)
	, CONSTRAINT fk_Released_releaseloc
		FOREIGN KEY(release_loc)
			REFERENCES Location(loc_id)
) COMMENT 'A table containing information about an animal''s release.'
;

CREATE TABLE Veterinary (
	record_id INT NOT NULL AUTO_INCREMENT COMMENT 'A unique identifier for each veterinary record.'
	, vet_id INT NOT NULL COMMENT 'An identifier indicating which veterinarian is responsible for this individual record.'
	, animal_id INT NOT NULL COMMENT 'An identifier indicating which animal is receiving care.'
	, animal_weight DECIMAL(9,2) NULL COMMENT 'The weight of the animal receiving care. The veterinarian will update this field when they provide care.'
	, animal_age INT NULL COMMENT 'The age of the animal receiving care. The veterinarian will update this field when they provide care.'
	, care_type VARCHAR(255) NOT NULL DEFAULT 'Routine Exam' COMMENT 'A type of care that the animal needs to receive from the veterinarian.'
	, is_resolved TINYINT NOT NULL DEFAULT FALSE COMMENT 'A field that indicates whether or not the record has been resolved/completed.'
	, tag_id INT NULL DEFAULT NULL COMMENT 'A unique tag number for each animal. Each animal will be tagged by the veterinarian once they deem they okay for release.'
	, PRIMARY KEY(record_id)
	, CONSTRAINT fk_Veterinary_vetid
		FOREIGN KEY(vet_id)
			REFERENCES Employee(emp_id)
	, CONSTRAINT fk_Veterinary_animalid
		FOREIGN KEY(animal_id)
			REFERENCES Animal(animal_id)
) COMMENT 'A table containing information about an animal''s veterinary records.'
;

CREATE TABLE Animal_Archive (
archive_id INT NOT NULL AUTO_INCREMENT COMMENT 'A unique identifier for each archive record.'
, archive_date DATETIME NOT NULL DEFAULT NOW() COMMENT 'The date and time the record was placed in the archive.'
, animal_id INT NOT NULL COMMENT 'The identifier of the animal whose records are being archived.'
, species_id INT NOT NULL COMMENT 'The identifier indicating which species the animal whose records are being archived is.'
, animal_name VARCHAR(55) NOT NULL COMMENT 'The name of the animal whose records are being archived.'
, animal_gender ENUM('Male', 'Female') NOT NULL COMMENT 'The gender of the animal whose records are being archived.'
, animal_age INT NOT NULL COMMENT 'The age of the animal whose records are being archived.'
, animal_weight DECIMAL(9,2) NOT NULL COMMENT 'The most recent weight of the animal whose records are being archived.'
, tag_id INT NOT NULL COMMENT 'The tag number given to the animal whose records are being archived before they were released back into the wild.'
, capture_date DATETIME	NOT NULL COMMENT 'The most recent date and time the animal whose records are being archived was captured.'
, release_date DATETIME	NOT NULL COMMENT 'The date and time the animal whose records are being archived was released.'
, PRIMARY KEY(archive_id)
) COMMENT 'A table containing all pertinent information about an animal after it''s release.
		Duplicate animals are allowed, as they could be caught and released numerous times.'
;

/*
	Stored Procedures
*/
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_insert_into_Department$$
CREATE PROCEDURE sp_insert_into_Department(
	p_dept_description VARCHAR(255)
)
COMMENT 'Stored procedure for inserting a row into the Department
	table.'
BEGIN
	DECLARE var_error TINYINT DEFAULT FALSE;
    
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET var_error = TRUE;
	
    INSERT INTO Department (
		dept_description
    ) VALUES (
		p_dept_description
    )
    ;
    
    SELECT LAST_INSERT_ID() AS 'dept_id';
	
END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_update_Department$$
CREATE PROCEDURE sp_update_Department(
	p_dept_id INT
    , p_old_dept_description VARCHAR(255)
    , p_new_dept_description VARCHAR(255)
)
COMMENT 'Stored procedure for updating a row in the Department
	table.'
BEGIN
	DECLARE var_error TINYINT DEFAULT FALSE;
    DECLARE var_count INT;
    
    DECLARE CONTINUE HANDLER FOR 1406 -- ER_DATA_TOO_LONG (Too many characters in description)
		SET var_error = TRUE;
	
    SELECT COUNT(*)
    INTO var_count
    FROM Department
    WHERE dept_id = p_dept_id
		AND dept_description = p_old_dept_description
	;
    
    IF var_count <> 1 THEN
		SIGNAL SQLSTATE '23000'
		SET MESSAGE_TEXT = 'Record Not Found'
        ;
    END IF;
    
    START TRANSACTION;
    
    UPDATE Department
    SET dept_description = p_new_dept_description
    WHERE dept_id = p_dept_id
    ;
    
    IF var_error = FALSE THEN
		COMMIT;
        SELECT 'Update Successful' AS 'message';
	ELSE
		ROLLBACK;
        SELECT 'Update Failed' AS 'message';
	END IF;
	
END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_delete_from_Department$$
CREATE PROCEDURE sp_delete_from_Department(
	p_dept_id INT
    , p_dept_description VARCHAR(255)
)
COMMENT 'Stored procedure for deleting a row from the Department
	table.'
BEGIN
	
	DECLARE var_error TINYINT DEFAULT FALSE;
    DECLARE var_count INT;
    
    DECLARE CONTINUE HANDLER FOR 1451 -- Cannot delete or update a parent row.
		SET var_error = TRUE;
	
    SELECT COUNT(*)
    INTO var_count
    FROM Department
    WHERE dept_id = p_dept_id
		AND dept_description = p_dept_description
	;
    
    IF var_count <> 1 THEN
		SIGNAL SQLSTATE '23000'
		SET MESSAGE_TEXT = 'Record Not Found'
        ;
    END IF;
    
    START TRANSACTION;
    
    DELETE FROM Department
    WHERE dept_id = p_dept_id
		AND dept_description = p_dept_description
    ;
    
    IF var_error = FALSE THEN
		COMMIT;
        SELECT 'Delete Successful' AS 'message';
	ELSE
		ROLLBACK;
        SELECT 'Delete Failed' AS 'message';
	END IF;
	
END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_select_from_Department$$
CREATE PROCEDURE sp_select_from_Department(
	p_dept_id INT
)
COMMENT 'Stored procedure for selecting data from the
	Department table.'
BEGIN

	DECLARE var_error TINYINT DEFAULT FALSE;
    
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET var_error = TRUE;
	
    SELECT
		dept_id
		, dept_description
	FROM Department
    WHERE dept_id = p_dept_id
    ;
	
END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_insert_into_Species$$
CREATE PROCEDURE sp_insert_into_Species(
	p_species_description VARCHAR(255)
)
COMMENT 'Stored procedure for inserting a row into the Species
	table.'
BEGIN
	DECLARE var_error TINYINT DEFAULT FALSE;
    
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET var_error = TRUE;
	
    INSERT INTO Species (
		species_description
    ) VALUES (
		p_species_description
    )
    ;
    
    SELECT LAST_INSERT_ID() AS 'species_id';
	
END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_update_Species$$
CREATE PROCEDURE sp_update_Species(
	p_species_id INT
    , p_old_species_description VARCHAR(255)
    , p_new_species_description VARCHAR(255)
)
COMMENT 'Stored procedure for updating a row in the Species
	table.'
BEGIN
	DECLARE var_error TINYINT DEFAULT FALSE;
    DECLARE var_count INT;
    
    DECLARE CONTINUE HANDLER FOR 1406 -- ER_DATA_TOO_LONG (Too many characters in description)
		SET var_error = TRUE;
	
    SELECT COUNT(*)
    INTO var_count
    FROM Species
    WHERE species_id = p_species_id
		AND species_description = p_old_species_description
	;
    
    IF var_count <> 1 THEN
		SIGNAL SQLSTATE '23000'
		SET MESSAGE_TEXT = 'Record Not Found'
        ;
    END IF;
    
    START TRANSACTION;
    
    UPDATE Species
    SET species_description = p_new_species_description
    WHERE species_id = p_species_id
    ;
    
    IF var_error = FALSE THEN
		COMMIT;
        SELECT 'Update Successful' AS 'message';
	ELSE
		ROLLBACK;
        SELECT 'Update Failed' AS 'message';
	END IF;
	
END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_delete_from_Species$$
CREATE PROCEDURE sp_delete_from_Species(
	p_species_id INT
    , p_species_description VARCHAR(255)
)
COMMENT 'Stored procedure for deleting a row from the Species
	table.'
BEGIN
	
	DECLARE var_error TINYINT DEFAULT FALSE;
    DECLARE var_count INT;
    
    DECLARE CONTINUE HANDLER FOR 1451 -- Cannot delete or update a parent row.
		SET var_error = TRUE;
	
    SELECT COUNT(*)
    INTO var_count
    FROM Species
    WHERE species_id = p_species_id
		AND species_description = p_species_description
	;
    
    IF var_count <> 1 THEN
		SIGNAL SQLSTATE '23000'
		SET MESSAGE_TEXT = 'Record Not Found'
        ;
    END IF;
    
    START TRANSACTION;
    
    DELETE FROM Species
    WHERE species_id = p_species_id
		AND species_description = p_species_description
    ;
    
    IF var_error = FALSE THEN
		COMMIT;
        SELECT 'Delete Successful' AS 'message';
	ELSE
		ROLLBACK;
        SELECT 'Delete Failed' AS 'message';
	END IF;
	
END$$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS sp_select_from_Species$$
CREATE PROCEDURE sp_select_from_Species(
	p_species_id INT
)
COMMENT 'Stored procedure for selecting data from the
	Species table.'
BEGIN

	DECLARE var_error TINYINT DEFAULT FALSE;
    
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
		SET var_error = TRUE;
	
    SELECT
		species_id
		, species_description
	FROM Species
    WHERE species_id = p_species_id
    ;
	
END$$
DELIMITER ;

/*
	Triggers for Department table
*/
DELIMITER $$
DROP TRIGGER IF EXISTS tr_Department_after_insert$$
CREATE TRIGGER tr_Department_after_insert
	AFTER INSERT ON Department
    FOR EACH ROW
BEGIN
	INSERT INTO Department_Audit (
		dept_id
        , dept_description
        , action_type
        , action_date
        , action_user
    ) VALUES (
		NEW.dept_id
        , NEW.dept_description
        , 'INSERTED' -- action_type
        , NOW() -- action_date
        , CURRENT_USER() -- action_user
    )
    ;
END$$
DELIMITER ;

DELIMITER $$
DROP TRIGGER IF EXISTS tr_Department_after_update$$
CREATE TRIGGER tr_Department_after_update
	AFTER UPDATE ON Department
    FOR EACH ROW
BEGIN
	INSERT INTO Department_Audit (
		dept_id
        , dept_description
        , action_type
        , action_date
        , action_user
    ) VALUES (
		OLD.dept_id
        , OLD.dept_description
        , 'UPDATED' -- action_type
        , NOW() -- action_date
        , CURRENT_USER() -- action_user
    )
    ;
END$$
DELIMITER ;

DELIMITER $$
DROP TRIGGER IF EXISTS tr_Department_after_delete$$
CREATE TRIGGER tr_Department_after_delete
	AFTER DELETE ON Department
    FOR EACH ROW
BEGIN
	INSERT INTO Department_Audit (
		dept_id
        , dept_description
        , action_type
        , action_date
        , action_user
    ) VALUES (
		OLD.dept_id
        , OLD.dept_description
        , 'DELETED' -- action_type
        , NOW() -- action_date
        , CURRENT_USER() -- action_user
    )
    ;
END$$
DELIMITER ;

/*
	INSERT Statements
*/
CALL sp_insert_into_Department('Capture Specialist');
CALL sp_insert_into_Department('Release Specialist');
CALL sp_insert_into_Department('Veterinarian');
CALL sp_insert_into_Department('Groundskeeper');
-- UPDATE and DELETE to test other triggers.
CALL sp_update_Department(4, 'Groundskeeper', 'Ground Maintenance');
CALL sp_delete_from_Department(4, 'Ground Maintenance');

INSERT INTO Employee (
	dept_id
    , emp_first_name
    , emp_last_name
    , emp_phone
    , emp_address
    , emp_city
    , emp_state
    , emp_zip_code
) VALUES (
	2
    , 'Englebert'
    , 'Jeakins'
    , '205-287-6894'
    , '477 Green Ridge Terrace'
    , 'Albuquerque'
    , 'NM'
    , '87201'
)
, (
	1
    , 'Happy'
    , 'Briffett'
    , '205-711-8382'
    , '9960 Chive Place'
    , 'Honolulu'
    , 'HI'
    , '96815'
)
, (
	2
    , 'Darcie'
    , 'Cockburn'
    , '334-345-5321'
    , '84 Morningstar Plaza'
    , 'Englewood'
    , 'CO'
    , '80150'
)
, (
	2
    , 'Selena'
    , 'Francescuccio'
    , '256-260-4063', '0 Spaight Junction'
    , 'Gadsden'
    , 'AL'
    , '35905'
)
, (
	3
    , 'Modestine'
    , 'Orr'
    , '205-700-8658'
    , '55 Parkside Pass'
    , 'Lubbock'
    , 'TX'
    , '79410'
)
, (
	3
    , 'Jackson'
    , 'Tickle'
    , '251-131-8949'
    , '08970 5th Lane'
    , 'Nashville'
    , 'TN'
    , '37245'
)
, (
	2
    , 'Salmon'
    , 'Kingshott'
    , '205-656-3690'
    , '0785 Park Meadow Street'
    , 'Kansas City'
    , 'MO'
    , '64101'
)
, (
	1
    , 'Faustina'
    , 'Byrne'
    , '256-180-2687'
    , '19503 Vahlen Pass'
    , 'Seattle'
    , 'WA'
    , '98195'
)
, (
	1
    , 'Denver'
    , 'Watts'
    , '205-668-3787'
    , '23 Anniversary Avenue'
    , 'Cincinnati'
    , 'OH'
    , '45233'
)
, (
	1
    , 'Catlin'
    , 'Gurry'
    , '251-453-2871'
    , '7507 Porter Circle'
    , 'Des Moines'
    , 'IA'
    , '50312'
)
;

CALL sp_insert_into_Species('Cat');
CALL sp_insert_into_Species('Tiger');
CALL sp_insert_into_Species('Turte');
CALL sp_insert_into_Species('Panda');
CALL sp_insert_into_Species('North American River Otter');
CALL sp_insert_into_Species('Eastern Fox Squirrel');
CALL sp_insert_into_Species('Yellow Mongoose');
CALL sp_insert_into_Species('Coyote');
CALL sp_insert_into_Species('Turkey Vulture');
CALL sp_insert_into_Species('Cobra');

INSERT INTO Location (
	loc_address
    , loc_city
    , loc_state
    , loc_zip_code
) VALUES (
	'82344 Dexter Street'
    , 'Riverside'
    , 'CA'
    , '92505'
)
, (
	'1711 Hoffman Way'
    , 'Austin'
    , 'TX'
    , '78783'
)
, (
	'02275 Mccormick Parkway'
    , 'Bridgeport'
    , 'CT'
    , '06673'
)
, (
	'82495 Bartelt Way'
    , 'Alhambra'
    , 'CA'
    , '91841'
)
, (
	'6 Hallows Avenue'
    , 'San Francisco'
    , 'CA'
    , '94142'
)
, (
	'8 Memorial Trail'
    , 'Richmond'
    , 'VA'
    , '23260'
)
, (
	'312 Russell Street'
    , 'Canton'
    , 'OH'
    , '44760'
)
, (
	'015 Moose Trail'
    , 'Brea'
    , 'CA'
    , '92822'
)
, (
	'19133 Merrick Drive'
    , 'Greenville'
    , 'SC'
    , '29610'
)
, (
	'04 Columbus Avenue'
    , 'Springfield'
    , 'MO'
    , '65810'
)
;

INSERT INTO Animal (
	species_id
    , animal_name
    , animal_gender
) VALUES (
	7
    , 'Gregory'
    , 'Male'
)
, (
	4
    , 'Samantha'
    , 'Female'
)
, (
	3
    , 'Donatello'
    , 'Male'
)
, (
	6
    , 'Scabbers'
    , 'Male'
)
, (
	8
    , 'Ajax'
    , 'Female'
)
, (
	1
    , 'Scooter'
    , 'Male'
)
, (
	2
    , 'Banshee'
    , 'Female'
)
, (
	8
    , 'Crescent'
    , 'Female'
)
, (
	10
    , 'Slinky'
    , 'Male'
)
, (
	9
    , 'Victor'
    , 'Male'
)
;

INSERT INTO Captured (
	animal_id
    , capture_emp
    , capture_loc
    , capture_date
    , capture_reason
) VALUES (
	10
    , 8
    , 8
    , '2022-06-11 22:46:24'
    , 'Flew into a wind turbine'
)
, (
	9
    , 9
    , 9
    , '2022-11-25 23:09:59'
    , 'Was ran over by a lawn mower (partially)'
)
, (
	4
    , 2
    , 1
    , '2022-05-10 22:22:03'
    , 'Fell out of a tree'
)
, (
	2
    , 10 
    , 4
    , '2023-01-21 02:12:03'
    , 'Ate a poisonous snail'
)
, (
	5
    , 9
    , 10
    , '2023-04-09 16:39:44'
    , 'Was left abdandoned on the side of the road'
)
, (
	8
    , 9
    , 10
    , '2022-04-09 16:39:44'
    , 'Was left abdandoned on the side of the road'
)
, (
	1
    , 2
    , 5
    , '2022-05-30 11:08:42'
    , 'Broken leg'
)
, (
	6
    , 10
    , 7
    , '2023-03-23 07:57:22'
    , 'Abandoned for having the zoomies (note on his collar)'
)
, (
	3
    , 2
    , 2
    , '2022-12-08 11:43:30'
    , 'Hit by a car'
)
, (
	7
    , 8
    , 6
    , '2023-04-01 20:00:37'
    , 'Broke out of zoo enclosure and has a splinter in her paw'
)
;

INSERT INTO Released (
	animal_id
    , release_emp
    , release_loc
    , release_date
) VALUES (
	3
    , 1
    , 2
	, '2023-02-19 22:09:36'
)
, (
	7
    , 4
    , 6
	, '2023-04-09 16:39:44'
)
;

INSERT INTO Veterinary (
	vet_id
    , animal_id
    , animal_weight
    , animal_age
    , care_type
    , is_resolved
    , tag_id
) VALUES (
	5
    , 3
    , 7.00
    , 32
    , 'Surgery and Recovery'
    , TRUE
    , 1
)
, (
	6
    , 7
    , 363
    , 13
    , 'Splinter removal'
    , TRUE
    , 2
)
, (
	6
    , 10
    , 12.25
    , 7
    , 'Wing surgery'
    , FALSE
    , NULL
)
, (
	6
    , 9
    , 3.06
    , 5
    , 'Tail reattachment'
    , FALSE
    , NULL
)
, (
	6
    , 4
    , 6.30
    , 2
    , 'Routine Exam'
    , FALSE
    , NULL
)
 , (
	5 
    , 2
    , 203
    , 12
    , 'Observation for symptoms'
    , FALSE
    , NULL
 )
, (
	5
    , 5
    , 33.15
    , 6
    , 'Monitoring until her sister is recovered'
    , FALSE
    , NULL
)
, (
	5
    , 8
    , 32.95
    , 6
    , 'Mending broken paw (from being left on side of the road)'
    , FALSE
    , NULL
)
, (
	6
    , 1
    , 0.95
    , 8
    , 'Monitoring until can walk on own'
    , FALSE
    , NULL
)
, (
	5
    , 6
    , 12.56
    , 5
    , 'Sedation and monitoring until severe case of zoomies is resolved'
    , FALSE
    , NULL
)
;

INSERT INTO Animal_Archive (
	animal_id
    , species_id
    , animal_name
    , animal_gender
    , animal_age
    , animal_weight
    , tag_id
    , capture_date
    , release_date
) VALUES (
	3
    , 3
    , 'Donatello'
    , 'Male'
    , 32
    , 7.00
    , 1
    , '2022-12-08 11:43:30'
    , '2023-02-19 22:09:36'
)
, (
	7
    , 2
    , 'Banshee'
    , 'Female'
    , 13
    , 363
    , 2
    , '2023-04-01 20:00:37'
    , '2023-04-09 16:39:44'
)
;
/*
	Views
*/
CREATE VIEW vw_current_care_of_animals AS (
	SELECT
		Animal.animal_name
        , Species.species_description
        , CONCAT(Employee.emp_first_name, ' ', Employee.emp_last_name) AS 'overseeing_vet'
        , Captured.capture_reason
        , Veterinary.care_type
	FROM Veterinary
    INNER JOIN Animal
		ON Veterinary.animal_id = Animal.animal_id
    INNER JOIN Captured
		ON Animal.animal_id = Captured.animal_id
	INNER JOIN Species
		ON Animal.species_id = Species.species_id
	INNER JOIN Employee
		ON Veterinary.vet_id = Employee.emp_id
	ORDER BY Animal.animal_name
)
;

DROP VIEW IF EXISTS vw_total_location_captures;
CREATE VIEW vw_total_location_captures AS (
	SELECT
		Location.loc_id AS 'location_id'
        , CONCAT(Location.loc_city, ', ', Location.loc_state) AS 'location'
        , COUNT(*) AS 'total_captures'
	FROM Captured
    INNER JOIN Location
		ON Captured.capture_loc = Location.loc_id
	GROUP BY Location.loc_id
)
;