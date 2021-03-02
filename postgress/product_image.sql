CREATE TABLE IF NOT EXISTS product_image (
  ID serial primary key,
  image_id INT NOT NULL,
  Product_main_Product INT NOT NULL);
  
  INSERT INTO product_image (ID, image_id, Product_main_Product) VALUES 
 (1, 1, 1)
,(2, 2, 1)
, (3, 3, 1)
, (4, 4, 1);
