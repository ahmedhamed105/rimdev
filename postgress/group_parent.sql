CREATE TABLE IF NOT EXISTS group_parent(
  ID serial primary key,
  parent_priviledge_ID INT NOT NULL,
  Group_priviledge_ID INT NOT NULL);
  
  INSERT INTO group_parent (ID, parent_priviledge_ID, Group_priviledge_ID) VALUES
  (1, 1, 2)
, (2, 2, 2)
, (3, 3, 2)
, (4, 4, 2)
, (5, 5, 2)
, (6, 6, 2)
, (7, 7, 2)
, (8, 8, 2)
, (9, 9, 2)
, (10, 10, 2)
, (11, 11, 2)
, (12, 12, 2)
, (13, 13, 2)
, (14, 14, 2)
, (15, 15, 2)
, (16, 16, 2)
, (17, 17, 2)
, (18, 18, 2)
, (19, 19, 2)
, (20, 20, 2)
, (21, 21, 2)
, (22, 21, 1)
, (23, 22, 2)
, (24, 23, 2)
, (25, 24, 2)
, (26, 25, 2);
