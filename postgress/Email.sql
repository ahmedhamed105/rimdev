CREATE TABLE IF NOT EXISTS Email(
  ID serial primary key,
  Email_user VARCHAR(45) NOT NULL,
  email_primary INT NOT NULL,
  OTP_code VARCHAR(45) NULL,
  OTP_sent INT NULL,
  email_modify timestamp NOT NULL,
  email_create timestamp NOT NULL,
  Data_status_ID INT NOT NULL,
  User_login_ID INT NOT NULL);