CREATE TABLE IF NOT EXISTS User_type(
  ID serial primary key,
  User_type VARCHAR(45) NOT NULL,
  Publish_not INT NOT NULL,
  type_modify timestamp NOT NULL,
  type_create timestamp NOT NULL);
  
  INSERT INTO User_type (User_type, Publish_not, type_modify, type_create) 
 VALUES ( 'admin', 0, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,( 'client', 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,( 'Worker', 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05');
