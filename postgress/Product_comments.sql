CREATE TABLE IF NOT EXISTS Product_comments(
  ID serial primary key,
  Pcomments VARCHAR(4500) NOT NULL,
  rating INT NOT NULL,
  Product_main_ID INT NOT NULL,
  User_id INT NULL);
  
 INSERT INTO Product_comments (ID, Pcomments, rating, Product_main_ID, User_id) VALUES (1, 'good product', 5, 1, 1);
