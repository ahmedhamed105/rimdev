CREATE TABLE IF NOT EXISTS password_history (
  ID serial primary key,
  password_hist VARCHAR(45) NOT NULL,
  pass_modify timestamp NOT NULL,
  pass_create timestamp NOT NULL,
  User_login_ID INT NOT NULL);