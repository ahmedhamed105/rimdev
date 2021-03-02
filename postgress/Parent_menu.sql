CREATE TABLE IF NOT EXISTS Parent_menu(
  ID serial primary key,
  P_menu VARCHAR(450) NOT NULL,
  fa_icon VARCHAR(450) NOT NULL,
  has_child INT NOT NULL,
  Parent_Link VARCHAR(450) NULL,
  Pages_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL);
  
  
  INSERT INTO Parent_menu (ID, P_menu, fa_icon, has_child, Parent_Link, Pages_ID, date_modify, date_create) VALUES (1, 'M1', 'fas fa-tachometer-alt', 0, '/dashboard', 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(2, 'M2', 'fas fa-users-cog', 1, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(3, 'M3', 'fas fa-search-dollar', 1, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(4, 'M4', 'fas fa-language', 1, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05')
,(5, 'Produt', 'fas fa-users-cog', 1, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05');
