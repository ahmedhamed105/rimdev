CREATE TABLE IF NOT EXISTS Group_status(
  ID serial primary key,
  Gstatus VARCHAR(45) NOT NULL);
  
  INSERT INTO Group_status (ID, Gstatus) VALUES 
  (1, 'Active')
, (2, 'Closed');
