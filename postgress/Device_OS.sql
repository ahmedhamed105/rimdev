CREATE TABLE IF NOT EXISTS Device_OS (
  ID serial primary key,
  Device_OS VARCHAR(45) NULL,
  Device_desc VARCHAR(45) NULL,
  Device_modify timestamp NOT NULL,
  Device_create timestamp NOT NULL);
  
  INSERT INTO Device_OS (ID, Device_OS, Device_desc, Device_modify, Device_create) VALUES
  (1, 'Windows', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (2, 'Mac', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (3, 'iOS', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (4, 'Android', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (5, 'Linux', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (6, 'Unix', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (7, 'Firefox-OS', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (8, 'Chrome-OS', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (9, 'Windows-Phone', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (10, 'Unknown', NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05');