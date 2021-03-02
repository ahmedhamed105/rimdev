CREATE TABLE IF NOT EXISTS Component_select (
  ID serial primary key,
  array_name VARCHAR(450) NOT NULL,
  array_object INT NOT NULL,
  select_value VARCHAR(450) NULL,
  select_display VARCHAR(450) NULL,
  web_service VARCHAR(450) NULL,
  Component_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL,
  Com_IP VARCHAR(450) NULL,
  Com_port VARCHAR(45) NULL);
  
  
  INSERT INTO Component_select (ID, array_name, array_object, select_value, select_display, web_service, Component_ID, date_modify, date_create, Com_IP, Com_port) VALUES
  (1, 'applicationID', 1, 'id', 'appname', '/App/all', 15, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (2, 'usertypeID', 1, 'id', 'usertype', '/Usertype/all', 16, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (3, 'grouppriviledgeID', 1, 'id', 'groupname', '/grouppriv/all', 17, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (4, 'userstatusID', 1, 'id', 'userstatus', '/userstatus/all', 18, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (5, 'allstatusID', 1, 'id', 'sdata', '/Status/all', 26, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8080')
, (6, 'allstatusID', 1, 'id', 'sdata', '/Status/all', 32, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8080')
, (7, 'fromaccounts', 1, 'id', 'acctNumber', '/Account/all', 39, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8080')
, (8, 'toaccounts', 1, 'id', 'acctNumber', '/Account/all', 42, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8080')
, (9, 'allstatusID', 1, 'id', 'sdata', '/Status/all', 53, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8080')
, (10, 'languagesID', 1, 'id', 'languagename', '/langs/all', 57, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (11, 'devtype', 1, 'id', 'devtype', '/DeviceType/all', 74, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (12, 'deviceOS', 1, 'id', 'deviceOS', '/DeviceOS/all', 75, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (13, 'dstatus', 1, 'id', 'dstatus', '/Devicestatus/all', 76, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (14, 'dstatus', 1, 'id', 'dstatus', '/datastatus/all', 88, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (15, 'primary', 1, 'value', 'key', '/Email/primary', 89, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (16, 'dstatus', 1, 'id', 'dstatus', '/datastatus/all', 96, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (17, 'primary', 1, 'value', 'key', '/Tele/primary', 97, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8081')
, (18, 'maincatagory', 1, 'id', 'catalogname', '/maincatagory/all', 107, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8083')
, (19, 'subcatalog', 1, 'id', 'subcatalogName', '/subcatagory/all', 108, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 'localhost', '8083')
