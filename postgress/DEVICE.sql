CREATE TABLE IF NOT EXISTS DEVICE (
  ID serial primary key,
  Device_name VARCHAR(45) NULL,
  Device_info VARCHAR(45) NULL,
  Device_ip VARCHAR(45) NULL,
  Device_code VARCHAR(450) NOT NULL,
  Device_mac VARCHAR(45) NULL,
  Device_OS_ID INT NOT NULL,
  Device_os_version VARCHAR(45) NULL,
  Device_os_unknow VARCHAR(1000) NULL,
  Device_modify timestamp NOT NULL,
  Device_create timestamp NOT NULL,
  Device_type_ID INT NOT NULL,
  Device_long DECIMAL(10,7) NULL,
  Device_latitude DECIMAL(10,7) NULL,
  Device_browser VARCHAR(45) NULL,
  Device_BVersion VARCHAR(45) NULL,
  Mobile BOOLEAN NULL,
  Desktop_Device BOOLEAN NULL,
  Tablet BOOLEAN NULL,
  Device_status_ID INT NOT NULL,
  Page INT NULL,
  login_type_ID INT NOT NULL,
  Application_ID INT NOT NULL);
  
  
  
  INSERT INTO DEVICE (ID, Device_name, Device_info, Device_ip, Device_code, Device_mac, Device_OS_ID, Device_os_version, Device_os_unknow, Device_modify, Device_create, Device_type_ID, Device_long, Device_latitude, Device_browser, Device_BVersion, Mobile, Desktop_Device, Tablet, Device_status_ID, Page, login_type_ID, Application_ID) VALUES (1, 'ahmed.elemam', 'dssd', '10.515.5512.5', 'ghkzm818cy54kqmdz99dxo', 'dsds', 1, '1', 'dsds', '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 31.3438691, 30.0843381, '21', '12', false, false, false, 1, 2, 1, 1);