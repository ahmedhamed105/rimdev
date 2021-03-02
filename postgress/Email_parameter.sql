CREATE TABLE IF NOT EXISTS Email_parameter (
  ID serial primary key,
  parameter_key VARCHAR(45) NOT NULL,
  paramter_value VARCHAR(4500) NULL,
  parmter_location VARCHAR(4500) NULL,
  value_dymaic INT NOT NULL,
  Email_template_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL);
  
  
  INSERT INTO Email_parameter (ID, parameter_key, paramter_value, parmter_location, value_dymaic, Email_template_ID, date_modify, date_create) VALUES
  (1, '<<--tittle-->>', 'RIMDEV', NULL, 0, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (2, '<<--Adv-->>', '', 'D:\\\\RIM_DEV\\\\Core\\\\Adv.html', 2, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (3, '<<--ADVimage-->>', 'http://localhost:8081/public/image/EN', NULL, 0, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (4, '<<--ADVtittle-->>', 'service', NULL, 0, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (5, '<<--ADVpara-->>', 'sd', NULL, 0, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (6, '<<--ADVbuttonlink-->>', 'sdv', NULL, 0, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
, (7, '<<--ADVbutton-->>', 'http://localhost:4200', NULL, 0, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
