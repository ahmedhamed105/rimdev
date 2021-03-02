CREATE TABLE IF NOT EXISTS component_file (
  ID serial primary key,
  file_type_ID INT NOT NULL,
  Component_input_ID INT NOT NULL);
  
  
  INSERT INTO component_file (ID, file_type_ID, Component_input_ID) VALUES
  (1, 1, 3)
, (2, 2, 3)
, (3, 1, 8)
, (4, 2, 8);
