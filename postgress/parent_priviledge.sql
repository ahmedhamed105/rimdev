CREATE TABLE IF NOT EXISTS parent_priviledge(
  ID serial primary key,
  parent_component_ID INT NOT NULL,
  public INT NOT NULL DEFAULT 0,
  Web_Device INT NOT NULL DEFAULT 1,
  Mobile_Device INT NOT NULL DEFAULT 1,
  Admin_Device INT NOT NULL DEFAULT 1,
  Isdesktop INT NOT NULL DEFAULT 1,
  Ismobile INT NOT NULL DEFAULT 1,
  Istablet INT NOT NULL DEFAULT 1);
  
  INSERT INTO parent_priviledge (ID, parent_component_ID, public, Web_Device, Mobile_Device, Admin_Device, Isdesktop, Ismobile, Istablet) VALUES  
  (1, 1, 1, 1, 1, 1, 1, 1, 1)
, (2, 2, 1, 1, 1, 1, 1, 1, 1)
, (3, 3, 1, 1, 1, 1, 1, 1, 1)
, (4, 4, 1, 1, 1, 1, 1, 1, 1)
, (5, 5, 1, 1, 1, 1, 1, 1, 1)
, (6, 6, 1, 1, 1, 1, 1, 1, 1)
, (7, 7, 1, 1, 1, 1, 1, 1, 1)
, (8, 8, 1, 1, 1, 1, 1, 1, 1)
, (9, 9, 1, 1, 1, 1, 1, 1, 1)
, (10, 10, 1, 1, 1, 1, 1, 1, 1)
, (11, 11, 1, 1, 1, 1, 1, 1, 1)
, (12, 12, 1, 1, 1, 1, 1, 1, 1)
, (13, 13, 1, 1, 1, 1, 1, 1, 1)
, (14, 14, 1, 1, 1, 1, 1, 1, 1)
, (15, 15, 1, 1, 1, 1, 1, 1, 1)
, (16, 16, 1, 1, 1, 1, 1, 1, 1)
, (17, 17, 1, 1, 1, 1, 1, 1, 1)
, (18, 18, 1, 1, 1, 1, 1, 1, 1)
, (19, 19, 1, 1, 1, 1, 1, 1, 1)
, (20, 20, 1, 1, 1, 1, 1, 1, 1)
, (21, 21, 1, 1, 1, 1, 1, 1, 1)
, (22, 22, 1, 1, 1, 1, 1, 1, 1)
, (23, 23, 1, 1, 1, 1, 1, 1, 1)
, (24, 24, 1, 1, 1, 1, 1, 1, 1)
, (25, 25, 1, 1, 1, 1, 1, 1, 1);