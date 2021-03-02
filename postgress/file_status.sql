CREATE TABLE IF NOT EXISTS file_status(
  ID serial primary key,
  file_st VARCHAR(450) NOT NULL,
  filest_modify timestamp NOT NULL,
  filest_create timestamp NOT NULL);
  
  INSERT INTO file_status (file_st,filest_modify,filest_create) VALUES ('Active', '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,('Deleted', '2020-01-04 14:17:05', '2020-01-04 14:17:05');

  
  