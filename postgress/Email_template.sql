CREATE TABLE IF NOT EXISTS Email_template (
  ID serial primary key,
  Temp_Location VARCHAR(450) NOT NULL,
  Sql_query VARCHAR(500) NULL,
  User_login_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL,
  send_query INT NOT NULL,
  update_query VARCHAR(4500) NULL);
  
  INSERT INTO Email_template (ID, Temp_Location, Sql_query, User_login_ID, date_modify, date_create, send_query, update_query) VALUES (1, 'D:\\\\RIM_DEV\\\\Core\\\\as.html', 'SELECT Email_user as email,CONCAT(\''<<--OTP-->>,\'', OTP_code) as param FROM rim_user.email where Data_status_ID = 2 and OTP_sent = 0', 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'update rim_user.email set OTP_sent = 1 where Email_user=');