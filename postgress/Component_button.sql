CREATE TABLE IF NOT EXISTS Component_button (
  ID serial primary key,
  button_class VARCHAR(450) NOT NULL,
  button_type VARCHAR(450) NULL,
  valid INT NULL,
  Component_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL,
  alert_after INT NULL,
  empty_message VARCHAR(900) NULL);
  
  
  
  INSERT INTO Component_button (ID, button_class, button_type, valid, Component_ID, date_modify, date_create, alert_after, empty_message) VALUES 
(1, 'btn-primary', 'submit', 1, 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(2, 'btn-primary', 'submit', 1, 19, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(3, 'btn-secondary', 'submit', 0, 20, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(4, 'btn-primary', 'submit', 1, 21, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(5, 'btn-secondary', 'submit', 0, 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(6, 'btn-primary', 'submit', 1, 27, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(7, 'btn-secondary', 'submit', 0, 28, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(8, 'btn-primary', 'submit', 1, 29, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(9, 'btn-primary', 'submit', 1, 33, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(10, 'btn-secondary', 'submit', 0, 34, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(11, 'btn-primary', 'submit', 1, 35, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(12, 'btn-primary', 'submit', 1, 44, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(13, 'btn-secondary', 'submit', 0, 45, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(14, 'btn-primary', 'submit', 0, 52, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(15, 'btn-primary', 'submit', 0, 54, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(16, 'btn-primary', 'submit', 1, 59, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(17, 'btn-secondary', 'submit', 0, 60, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(18, 'btn-primary', 'submit', 1, 61, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(19, 'btn-primary', 'submit', 1, 64, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(20, 'btn-secondary', 'submit', 0, 65, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(21, 'btn-primary', 'submit', 1, 66, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(22, 'btn-primary', 'submit', 0, 77, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(23, 'btn-secondary', 'submit', 0, 78, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(24, 'btn-primary', 'submit', 1, 83, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(25, 'btn-secondary', 'submit', 0, 84, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(26, 'btn-primary', 'submit', 1, 85, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(27, 'btn-primary', 'submit', 1, 90, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(28, 'btn-secondary', 'submit', 0, 91, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(29, 'btn-primary', 'submit', 1, 92, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(30, 'btn-secondary', 'submit', 0, 93, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(31, 'btn-primary', 'submit', 1, 98, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(32, 'btn-secondary', 'submit', 0, 99, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(33, 'btn-primary', 'submit', 1, 100, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(34, 'btn-secondary', 'submit', 0, 101, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(35, 'btn-primary', 'submit', 1, 109, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(36, 'btn-secondary', 'submit', 0, 110, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(37, 'btn-primary', 'submit', 1, 111, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(38, 'btn-secondary', 'submit', 0, 112, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data')
,(39, 'btn-primary', 'submit', 0, 113, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 'No_data');