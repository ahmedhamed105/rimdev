CREATE TABLE IF NOT EXISTS parent_component(
  ID serial primary key,
  pcode_tittle VARCHAR(450) NOT NULL,
  parent_postion INT NOT NULL,
  Pages_ID INT NOT NULL,
  parent_type VARCHAR(45) NULL,
  parent_name VARCHAR(45) NULL,
  First_method VARCHAR(450) NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL,
  pagination INT NOT NULL,
  Com_IP VARCHAR(450) NOT NULL,
  Com_port VARCHAR(45) NOT NULL,
  com_table INT NULL,
  com_formid INT NULL);
  
  
  INSERT INTO parent_component (ID, pcode_tittle, parent_postion, Pages_ID, parent_type, parent_name, First_method, date_modify, date_create, pagination, Com_IP, Com_port, com_table, com_formid) VALUES
  (1, 'inserting_form', 1, 6, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (2, 'T61', 1, 6, 'table', 'table1', '/Email/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 1, 1)
, (3, 'inserting_form', 1, 7, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (4, 'T74', 1, 7, 'table', 'table1', '/Tele/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 1, 3)
, (5, 'inserting_form', 1, 8, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (6, 'T86', 1, 8, 'table', 'table1', '/Page/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 1, 5)
, (7, 'inserting_form', 1, 5, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (8, 'T58', 1, 5, 'table', 'table1', '/User/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 1, 7)
, (9, 'T49', 1, 4, 'table', 'table1', '/Device/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 0, NULL)
, (10, 'T410', 1, 4, 'table', 'table2', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 0, NULL)
, (11, 'inserting_form', 1, 2, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8080', 0, NULL)
, (12, 'T212', 1, 2, 'table', 'table1', '/Flow/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8080', 1, 11)
, (13, 'inserting_form', 1, 3, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8080', 0, NULL)
, (14, 'T314', 1, 3, 'table', 'table1', '/Currency/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8080', 1, 13)
, (15, 'inserting_form', 1, 9, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (16, 'T916', 1, 9, 'table', 'table1', '/text/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 1, 15)
, (17, 'inserting_form', 1, 10, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (18, 'T1018', 1, 10, 'table', 'table1', '/langs/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 1, 17)
, (19, 'inserting_form', 1, 11, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (20, 'T1120', 1, 11, 'table', 'table1', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8081', 0, NULL)
, (21, 'inserting_form', 1, 12, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (22, 'inserting_form', 1, 13, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8080', 0, NULL)
, (23, 'T1121', 1, 14, 'table', 'table1', '/Account/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8080', 0, NULL)
, (24, 'inserting_form', 1, 15, 'form', 'insertform', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 'localhost', '8081', 0, NULL)
, (25, 'T61', 1, 15, 'table', 'table1', '/product/all', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 5, 'localhost', '8083', 1, 24);
