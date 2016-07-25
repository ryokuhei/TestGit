/* DBが存在したら削除 */
DROP DATABASE IF EXISTS employee_management_db;

/* DB作成 */
CREATE DATABASE employee_management_db;

/* ユーザー作成 */
CREATE USER user;

/* 権限付与 */
GRANT ALL PRIVILEGES ON employee_management_db.* TO user IDENTIFIED BY 'password';

USE employee_management_db;

/* TABLEが存在したら削除 */
DROP TABLE IF EXISTS employee_management_db.employee;
DROP TABLE IF EXISTS employee_management_db.section;

/* TABLE作成 */

CREATE TABLE employee_management_db.section (
	SECTION_CODE INT(2) NOT NULL,
	SECTION_NAME VARCHAR(24) NOT NULL,
	DATE_MODEFIED TIMESTAMP,
PRIMARY KEY(SECTION_CODE)

)ENGINE=INNODB;


CREATE TABLE employee_management_db.employee (
	EMPLOYEE_NUMBER VARCHAR(4) NOT NULL,
	FIRST_NAME VARCHAR(16) NOT NULL,
	LAST_NAME VARCHAR(16) NOT NULL,
	PHONETIC_FIRST_NAME VARCHAR(16),
	PHONETIC_LAST_NAME VARCHAR(16),
	SEX TINYINT,
	BIRTH_DAY DATE,
	SECTION_CODE INT(2),
	HIRE_DATE DATE,
	DATE_MODEFIED TIMESTAMP,
PRIMARY KEY(EMPLOYEE_NUMBER),
FOREIGN KEY(SECTION_CODE) REFERENCES employee_management_db.section(SECTION_CODE)
)ENGINE=INNODB;


/* テストデータ挿入 */
INSERT INTO employee_management_db.employee
 (EMPLOYEE_NUMBER, FIRST_NAME, LAST_NAME) VALUES
 (1, 'SATO', 'RYOKUHEI');
 
 INSERT INTO employee_management_db.section
 (SECTION_CODE, SECTION_NAME)
	VALUES (1, '営業部');
	
INSERT INTO employee_management_db.employee
 (SECTION_CODE, SECTION_NAME)
	VALUES (2, '開発部');