CREATE TABLE IF NOT EXISTS Log_type(
  ID serial primary key,
  Ltype VARCHAR(450) NOT NULL);
  
  
  INSERT INTO Log_type (ID, Ltype) VALUES
  (1, 'Duplicate')
, (2, 'sql error')
, (3, 'Device_not_auth')
, (4, 'info_DATA')
, (5, 'login_failed')
, (6, 'login_sucess')
, (7, 'report_IP')
, (8, 'page_priviledge')
, (9, 'web_service_priviledge')
, (10, 'blocked_ip')
, (11, 'login_blocked_three_times')
, (12, 'Device_auth')
, (13, 'Group_Closed')
, (14, 'NO_page_priviledge')
, (15, 'NO_web_service_priviledge')
, (16, 'email_login_failed')
, (17, 'blocked_email')
, (18, 'email_status')
, (19, 'user_blocked')
, (20, 'user_not_Active')
, (21, 'blocked_telephone')
, (22, 'telephone_status')
, (23, 'telephone_login_failed')
, (24, 'username_login_failed')
, (25, 'no_user_no_email_no_tele')
, (26, 'Device_blocked')
, (27, 'password_wrong')
, (28, 'password_correct')
, (29, 'User_auth')
, (30, 'User_not_auth')
, (31, 'User_not_login')
, (32, 'User_creation')
, (33, 'account_creation')
, (34, 'Currency')
, (35, 'flow type')
, (36, 'Account')
, (37, 'Transfer');
  