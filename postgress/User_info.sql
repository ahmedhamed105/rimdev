CREATE TABLE IF NOT EXISTS User_info (
  ID serial PRIMARY KEY,
  first_name VARCHAR(45) NULL,
  middle_name VARCHAR(45) NULL,
  Last_name VARCHAR(45) NULL,
  birthdate TIMESTAMP NULL,
  User_id_number VARCHAR(45) UNIQUE NULL,
  Passport_number VARCHAR(45) NULL,
  ID_number VARCHAR(45) NULL,
  User_modify TIMESTAMP NOT NULL,
  User_create TIMESTAMP NOT NULL,
  files_upload_ID INT NOT NULL);