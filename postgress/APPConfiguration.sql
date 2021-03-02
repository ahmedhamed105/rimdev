CREATE TABLE IF NOT EXISTS APPConfiguration (
  ID serial primary key,
  Config_key VARCHAR(450) NOT NULL,
  Config_value VARCHAR(450) NULL,
  Config_num INT NULL,
  Config_boolean INT NULL,
  Config_Date timestamp NULL,
  Create_Date timestamp NOT NULL,
  modify_Date timestamp NOT NULL,
  User_login_ID INT NOT NULL);
  
  INSERT INTO APPConfiguration (ID, Config_key, Config_value, Config_num, Config_boolean, Config_Date, Create_Date, modify_Date, User_login_ID) VALUES
  (1, 'Login_email', NULL, NULL, 0, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (2, 'login_telephone', NULL, NULL, 1, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (3, 'login_username', NULL, NULL, 1, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (4, 'Tokean_Expiration_hours', NULL, 10, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (5, 'Tokean_Expiration_minutes', NULL, 1, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (6, 'Tokean_Expiration_flag', NULL, 1, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (7, 'Log_info', NULL, NULL, 1, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (8, 'Log_error', NULL, NULL, 1, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (9, 'Log_fatal_error', NULL, NULL, 1, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (10, 'Log_warning', NULL, NULL, 1, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (11, 'Log_other', NULL, NULL, 1, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (12, 'notification_count', NULL, 4, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (13, 'Login_failed_blocked', NULL, 3, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (14, 'default_currency', 'EGP', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (15, 'account_tokean', 'P@ssw0rd', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (16, 'input_comp_not_include_table', NULL, 4, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (17, 'Email_sender', 'ahmed2000105@gmail.com', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (18, 'Sender_name', 'RIMDEV', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (19, 'SMTP_OTP_EMAIL', 'smtp.googlemail.com', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (20, 'SMTP_OTP_PORT', '465', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (21, 'Username', 'ahmed2000105@gmail.com', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (22, 'password_enc', 'D:\\\\RIM_DEV\\\\Core\\\\rimdev\\\\password_files\\\\otp.t', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (23, 'password_key', 'ssshhhhhhhhhhh!!!!', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1)
, (24, 'template_id', '1', NULL, NULL, NULL, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1);
