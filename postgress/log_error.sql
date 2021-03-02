CREATE TABLE IF NOT EXISTS log_error(
  ID serial primary key,
  user_id INT NOT NULL DEFAULT 0,
  log_text VARCHAR(9000) NOT NULL,
  log_exception VARCHAR(450) NULL,
  log_time timestamp NOT NULL,
  DEVICE_ID INT NOT NULL,
  Log_type_ID INT NOT NULL,
  web_service VARCHAR(450) NOT NULL,
  Error_code VARCHAR(450) NOT NULL,
  Ip_address VARCHAR(450) NOT NULL);