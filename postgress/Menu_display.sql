CREATE TABLE IF NOT EXISTS Menu_display(
  ID serial primary key,
  Menu_name VARCHAR(450) NOT NULL,
  Menu_link VARCHAR(450) NOT NULL,
  fa_icon VARCHAR(450) NULL,
  Parent_menu_ID INT NOT NULL,
  Pages_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL);
  
  
  
  INSERT INTO Menu_display (ID, Menu_name, Menu_link, fa_icon, Parent_menu_ID, Pages_ID, date_modify, date_create) VALUES 
 (6, 'M31', '/page', 'fas fa-circle-notch', 3, 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(7, 'M32', '/page', 'fas fa-circle-notch', 3, 2, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(1, 'M21', '/page', 'fas fa-circle-notch', 2, 4, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(2, 'M22', '/page', 'fas fa-circle-notch', 2, 5, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(3, 'M23', '/page', 'fas fa-circle-notch', 2, 6, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(4, 'M24', '/page', 'fas fa-circle-notch', 2, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(5, 'M25', '/page', 'fas fa-circle-notch', 2, 8, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(8, 'M41', '/page', 'fas fa-circle-notch', 4, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(9, 'M42', '/page', 'fas fa-circle-notch', 4, 10, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(10, 'M210', '/page', 'fas fa-circle-notch', 2, 11, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(11, 'Transfer', '/page', 'fas fa-circle-notch', 3, 13, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(12, 'account', '/page', 'fas fa-circle-notch', 3, 14, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(13, 'Product', '/page', 'fas fa-circle-notch', 5, 15, '2020-01-04 14:17:05', '2020-01-04 14:17:05');
