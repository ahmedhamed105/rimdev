CREATE TABLE IF NOT EXISTS Product_main(
  ID serial primary key,
  product_name VARCHAR(450) NOT NULL,
  product_Icon VARCHAR(45) NULL,
  product_paragraph VARCHAR(5000) NULL,
  product_price DECIMAL(20,6) NOT NULL,
  product_sale INT NOT NULL,
  User_created INT NOT NULL,
  created_date timestamp NOT NULL,
  modified_date timestamp NOT NULL,
  sub_catalog_ID INT NOT NULL);
  
  INSERT INTO Product_main (ID, product_name, product_Icon, product_paragraph, product_price, product_sale, User_created, created_date, modified_date, sub_catalog_ID) VALUES 
 (1, 'WATER', 'fa fa-water', 'water needed', 10.20, 10, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
,(2, 'Electict', 'fa fa-water', 'Electict needed', 12.20, 20, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 2);
