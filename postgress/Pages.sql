CREATE TABLE IF NOT EXISTS Pages(
 ID serial primary key,
 Page_name VARCHAR(45) NULL,
 Page_menu VARCHAR(45) NULL,
 Link_router VARCHAR(450) NULL,
 date_modify timestamp NOT NULL,
 date_create timestamp NOT NULL,
 image_flag INT NOT NULL,
 files_upload_ID INT NULL);
 
 
 INSERT INTO Pages (ID, Page_name, Page_menu, Link_router, date_modify, date_create, image_flag, files_upload_ID) VALUES
  (1, 'dasboard', 'dasboard', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (2, 'Flow type', 'Flow type', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (3, 'Currency', 'Currency', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (4, 'Devices', 'Devices', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (5, 'Users', 'Users', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (6, 'User_email', 'User_email', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (7, 'User_telephone', 'User_telephone', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (8, 'pages', 'pages', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (9, 'Language', 'Language', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (10, 'languagecode', 'languagecode', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (11, 'logininfo', 'logininfo', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (12, 'loginpage', 'loginpage', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 1)
, (13, 'Account_transfer', 'Account_transfer', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (14, 'Account', 'Account', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL)
, (15, 'product', 'product', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, NULL);
