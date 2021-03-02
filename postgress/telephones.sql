CREATE TABLE IF NOT EXISTS Telephones(
  ID serial primary key,
  phone_no VARCHAR(45) NOT NULL,
  tele_primary INT NOT NULL,
  OTP_code VARCHAR(45) NULL,
  OTP_sent INT NULL,
  tele_modify timestamp NOT NULL,
  tele_create timestamp NOT NULL,
  Data_status_ID INT NOT NULL,
  User_login_ID INT NOT NULL);