CREATE TABLE IF NOT EXISTS Device_page(
  ID serial primary key,
  DEVICE_ID INT NOT NULL,
  Pages_ID INT NOT NULL,
  Visit_time timestamp NOT NULL,
  User_login_ID INT NOT NULL,
  page_tokean VARCHAR(450) NOT NULL);