CREATE TABLE IF NOT EXISTS sub_catalog (
  ID serial primary key,
  subcatalog_name VARCHAR(45) NOT NULL,
  subcatalog_info VARCHAR(4500) NULL,
  Main_catalog_ID INT NOT NULL);
  
  
INSERT INTO sub_catalog (ID, subcatalog_name, subcatalog_info, Main_catalog_ID) VALUES 
  (1, 'No Category', 'No Category', 1)
, (2, 'No Category', 'No Category', 2)
, (3, 'WATERS DROP', 'WATERS DROP', 1)
, (4, 'ERP', 'ERP', 2);

