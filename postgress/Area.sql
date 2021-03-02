CREATE TABLE IF NOT EXISTS Area (
  ID serial primary key,
  Area_name VARCHAR(45) NOT NULL,
  Area_long1 VARCHAR(45) NOT NULL,
  Area_long2 VARCHAR(45) NULL,
  Area_long3 VARCHAR(45) NULL,
  Area_lat1 VARCHAR(45) NULL,
  Area_lat2 VARCHAR(45) NULL,
  Area_lat3 VARCHAR(45) NULL,
  Area_lat4 VARCHAR(45) NULL,
  Area_modify timestamp NOT NULL,
  Area_create timestamp NOT NULL);