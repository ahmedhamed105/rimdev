CREATE TABLE IF NOT EXISTS User_file(
  ID serial primary key,
  files_upload_ID INT NOT NULL,
  Component_ID INT NOT NULL,
  User_login_ID INT NOT NULL);