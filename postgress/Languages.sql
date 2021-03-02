CREATE TABLE IF NOT EXISTS Languages (
  ID serial primary key,
  Language_name VARCHAR(45) NULL,
  Language_code VARCHAR(45) NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL);
  
  INSERT INTO Languages (ID, Language_name, Language_code, date_modify, date_create) VALUES 
  (1, 'ENGLISH', 'EN', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (2, 'ARABIC', 'AR', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (3, 'French', 'FR', '2020-01-04 14:17:05', '2020-01-04 14:17:05');
