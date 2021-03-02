CREATE TABLE IF NOT EXISTS Relation_type (
  ID serial primary key,
  Rtype VARCHAR(450) NOT NULL);
  
  
  INSERT INTO Relation_type (ID, Rtype) VALUES
  (1, 'keyup')
, (2, 'change')
, (3, 'referesh')
, (4, 'reset')
, (5, 'Click');