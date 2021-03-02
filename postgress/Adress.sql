CREATE TABLE IF NOT EXISTS Adress(
  ID serial primary key,
  Ad_name VARCHAR(45) NOT NULL,
  ad_street VARCHAR(45) NULL,
  Ad_building VARCHAR(45) NULL,
  Ad_Area VARCHAR(45) NULL,
  Ad_logtiude VARCHAR(45) NULL,
  Ad_latitude VARCHAR(45) NULL,
  Area_ID INT NOT NULL,
  add_modify timestamp NOT NULL,
  add_create timestamp NOT NULL,
  User_login_ID INT NOT NULL);