CREATE TABLE IF NOT EXISTS input_type (
  ID serial primary key,
  itype VARCHAR(45) NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL);
  
  INSERT INTO input_type (ID, itype, date_modify, date_create) VALUES
  (1, 'text', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (2, 'email', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (3, 'date', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (4, 'file', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (5, 'password', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (6, 'money', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
