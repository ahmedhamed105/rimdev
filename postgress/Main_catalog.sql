CREATE TABLE IF NOT EXISTS Main_catalog (
  ID serial primary key,
  Catalog_name VARCHAR(45) NOT NULL,
  Catalog_info VARCHAR(4500) NULL);
  
  INSERT INTO Main_catalog (ID, Catalog_name, Catalog_info) VALUES 
  (1, 'WATERS', 'WATERS')
, (2, 'ELECT', 'WATERS');
