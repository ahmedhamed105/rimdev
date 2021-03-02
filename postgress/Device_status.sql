CREATE TABLE IF NOT EXISTS Device_status (
  ID serial primary key,
  D_status VARCHAR(45) NULL);
  
  INSERT INTO Device_status (ID, D_status) VALUES 
  (1, 'Active')
, (2, 'blocked');
