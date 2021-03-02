CREATE TABLE IF NOT EXISTS Notification (
  ID serial primary key,
  Notif_text VARCHAR(450) NOT NULL,
  Notif_Date timestamp NULL,
  Application_ID INT NOT NULL,
  User_login_ID INT NOT NULL,
  Group_priviledge_ID INT NOT NULL);