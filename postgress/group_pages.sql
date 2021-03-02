CREATE TABLE IF NOT EXISTS group_pages (
  ID serial primary key,
  Group_priviledge_ID INT NOT NULL,
  pages_priviledge_ID INT NOT NULL);
  
  INSERT INTO group_pages (ID, Group_priviledge_ID, pages_priviledge_ID) VALUES
  (1, 2, 1)
, (2, 2, 2)
, (3, 2, 3)
, (4, 2, 4)
, (5, 2, 5)
, (6, 2, 6)
, (7, 2, 7)
, (8, 2, 8)
, (9, 2, 9)
, (10, 2, 10)
, (11, 2, 11)
, (12, 2, 12)
, (13, 1, 12)
, (14, 2, 13)
, (15, 2, 14)
, (16, 2, 15);
