CREATE TABLE IF NOT EXISTS Component_input (
  ID serial primary key,
  input_Actions VARCHAR(45) NOT NULL,
  Component_ID INT NOT NULL,
  input_type_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL,
  insert_serv VARCHAR(450) NULL,
  delete_serv VARCHAR(450) NULL,
  insert_parameter VARCHAR(450) NULL,
  delete_parameter VARCHAR(450) NULL,
  file_count INT NULL DEFAULT 0,
  file_size INT NULL,
  file_counterr VARCHAR(450) NULL,
  file_sizeerr VARCHAR(450) NULL,
  file_typeerror VARCHAR(450) NULL,
  insert_ip VARCHAR(450) NULL,
  insert_port VARCHAR(45) NULL,
  delete_ip VARCHAR(450) NULL,
  delete_port VARCHAR(45) NULL);
  
  
  
  INSERT INTO Component_input (ID, input_Actions, Component_ID, input_type_ID, date_modify, date_create, insert_serv, delete_serv, insert_parameter, delete_parameter, file_count, file_size, file_counterr, file_sizeerr, file_typeerror, insert_ip, insert_port, delete_ip, delete_port) VALUES 
  (1, 'invalid', 1, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (2, 'invalid', 2, 5, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (3, 'invalid', 5, 4, '2020-01-04 14:17:05', '2020-01-04 14:17:05', '/User/profile', '/User/deletefile', 'id', 'id', 1, 20971520, 'file_count_err', 'file_size_err', 'file_type_err', 'localhost', '8081', 'localhost', '8081')
, (4, 'invalid', 6, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (5, 'invalid', 7, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (6, 'invalid', 8, 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (7, 'invalid', 9, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (8, 'invalid', 10, 4, '2020-01-04 14:17:05', '2020-01-04 14:17:05', '/User/file', '/User/deletefile', 'id', 'id', 2, 20971520, 'file_count_err', 'file_size_err', 'file_type_err', 'localhost', '8081', 'localhost', '8081')
, (9, 'invalid', 12, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (10, 'invalid', 13, 5, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (11, 'invalid', 14, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (12, 'invalid', 23, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (13, 'invalid', 24, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (14, 'invalid', 25, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (15, 'invalid', 30, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (16, 'invalid', 31, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (17, 'invalid', 38, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (18, 'invalid', 40, 6, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (19, 'invalid', 41, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (20, 'invalid', 43, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (21, 'invalid', 56, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (22, 'invalid', 58, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (23, 'invalid', 62, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (24, 'invalid', 63, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (25, 'invalid', 81, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (26, 'invalid', 82, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (27, 'invalid', 86, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (28, 'invalid', 87, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (29, 'invalid', 94, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (30, 'invalid', 95, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (31, 'invalid', 102, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (32, 'invalid', 103, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (33, 'invalid', 104, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (34, 'invalid', 105, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL)
, (35, 'invalid', 106, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
