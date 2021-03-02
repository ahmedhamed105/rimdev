CREATE TABLE IF NOT EXISTS Data_status(
  ID serial primary key,
  Dstatus VARCHAR(45) NOT NULL,
  DDesc VARCHAR(500) NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL);
  
INSERT INTO Data_status(Dstatus, DDesc, date_modify, date_create) VALUES 
('Active', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,('not confirm', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,('Blocked', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05');
