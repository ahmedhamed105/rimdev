CREATE TABLE IF NOT EXISTS cart_main(
  ID serial primary key,
  Product_cart INT NOT NULL,
  product_price DECIMAL(20,6) NOT NULL,
  Product_user INT NOT NULL,
  product_notes VARCHAR(500) NULL,
  bill_main_ID INT NOT NULL);