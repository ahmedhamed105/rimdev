CREATE TABLE IF NOT EXISTS User_status(
  ID serial primary key,
  User_status VARCHAR(450) NOT NULL);
  
  INSERT INTO User_status (ID, User_status) VALUES 
  (1, 'Active')
, (2, 'undercheck')
, (3, 'Closed')
, (4, 'Blocked');
