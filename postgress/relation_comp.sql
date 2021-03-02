CREATE TABLE IF NOT EXISTS relation_comp (
  ID serial primary key,
  Component_ID INT NOT NULL,
  service VARCHAR(450) NULL,
  empty_serv VARCHAR(450) NULL,
  Com_IP VARCHAR(450) NULL,
  Com_port VARCHAR(45) NULL,
  related_component INT NULL,
  related_parent INT NULL,
  Relation_type_ID INT NOT NULL,
  relparent_servempty VARCHAR(450) NULL,
  Com_IPpar VARCHAR(450) NULL,
  Com_portpar VARCHAR(45) NULL,
  par_serv VARCHAR(450) NULL,
  Data_type VARCHAR(45) NULL,
  routing_ind INT NOT NULL,
  routing_loc VARCHAR(450) NULL,
  alert_after INT NULL,
  sucess_message VARCHAR(900) NULL,
  resetind INT NOT NULL,
  reset_parent INT NULL,
  enable_comp INT NULL,
  visible_comp INT NULL,
  disable_comp INT NULL,
  hide_comp INT NULL);
  
  
  
  
  
  INSERT INTO relation_comp (ID, Component_ID, service, empty_serv, Com_IP, Com_port, related_component, related_parent, Relation_type_ID, relparent_servempty, Com_IPpar, Com_portpar, par_serv, Data_type, routing_ind, routing_loc, alert_after, sucess_message, resetind, reset_parent, enable_comp, visible_comp, disable_comp, hide_comp) VALUES (1, 38, '/Account/key', '/Account/all', 'localhost', '8080', 39, NULL, 1, NULL, NULL, NULL, NULL, 'A', 0, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL)
,(2, 39, '/Account/currency', '/Account/currency', 'localhost', '8080', 40, NULL, 2, NULL, NULL, NULL, NULL, 'NP', 0, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL)
,(3, 41, '/Account/key', '/Account/all', 'localhost', '8080', 42, NULL, 1, NULL, NULL, NULL, NULL, 'A', 0, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL)
,(4, 3, NULL, NULL, NULL, NULL, NULL, 0, 5, NULL, 'localhost', '8081', '/login/saveorupdate', 'N', 1, '/dashboard', 1, 'welcome {{username}} {{logintime}}', 1, 21, NULL, NULL, NULL, NULL)
,(5, 44, NULL, NULL, NULL, NULL, NULL, 0, 5, NULL, 'localhost', '8080', '/Account/transfer', 'N', 0, NULL, 1, 'Transfer {{reference_no}}', 1, 22, NULL, NULL, NULL, NULL)
,(6, 45, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'N', 0, NULL, 1, 'form_reset', 1, 22, NULL, NULL, NULL, NULL)
,(7, 19, NULL, NULL, NULL, NULL, NULL, 8, 5, NULL, 'localhost', '8081', '/User/save', 'A', 0, NULL, 1, 'data_inserted', 1, 7, NULL, NULL, NULL, 52)
,(8, 20, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 7, NULL, 19, NULL, 52)
,(9, 21, NULL, NULL, NULL, NULL, NULL, 8, 5, NULL, 'localhost', '8081', '/User/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(10, 22, NULL, NULL, NULL, NULL, NULL, 7, 5, NULL, 'localhost', '8081', '/User/filebyuser', 'A', 0, NULL, 1, 'data_display', 0, NULL, NULL, 52, NULL, 19)
,(11, 52, NULL, NULL, NULL, NULL, NULL, 8, 5, NULL, 'localhost', '8081', '/User/update', 'A', 0, NULL, 1, 'data_updated', 1, 7, NULL, NULL, NULL, NULL)
,(12, 27, NULL, NULL, NULL, NULL, NULL, 14, 5, NULL, 'localhost', '8080', '/Currency/save', 'A', 0, NULL, 1, 'data_inserted', 1, 13, NULL, NULL, NULL, NULL)
,(13, 28, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 13, NULL, NULL, NULL, NULL)
,(14, 29, NULL, NULL, NULL, NULL, NULL, 14, 5, NULL, 'localhost', '8080', '/Currency/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(15, 33, NULL, NULL, NULL, NULL, NULL, 12, 5, NULL, 'localhost', '8080', '/Flow/save', 'A', 0, NULL, 1, 'data_inserted', 1, 11, NULL, NULL, NULL, NULL)
,(16, 34, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 11, NULL, NULL, NULL, NULL)
,(17, 35, NULL, NULL, NULL, NULL, NULL, 12, 5, NULL, 'localhost', '8080', '/Flow/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(18, 54, NULL, NULL, NULL, NULL, NULL, 23, 5, NULL, 'localhost', '8080', '/Account/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(19, 59, NULL, NULL, NULL, NULL, NULL, 16, 5, NULL, 'localhost', '8081', '/text/save', 'A', 0, NULL, 1, 'data_inserted', 1, 15, NULL, NULL, NULL, NULL)
,(20, 60, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 15, NULL, NULL, NULL, NULL)
,(21, 61, NULL, NULL, NULL, NULL, NULL, 16, 5, NULL, 'localhost', '8081', '/text/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(22, 64, NULL, NULL, NULL, NULL, NULL, 18, 5, NULL, 'localhost', '8081', '/langs/save', 'A', 0, NULL, 1, 'data_inserted', 1, 17, NULL, NULL, NULL, NULL)
,(23, 65, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 17, NULL, NULL, NULL, NULL)
,(24, 66, NULL, NULL, NULL, NULL, NULL, 18, 5, NULL, 'localhost', '8081', '/langs/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(25, 77, NULL, NULL, NULL, NULL, NULL, 10, 5, NULL, 'localhost', '8081', '/Device/page', 'A', 0, NULL, 1, 'data_display', 0, NULL, NULL, NULL, NULL, NULL)
,(26, 78, NULL, NULL, NULL, NULL, NULL, 9, 5, NULL, 'localhost', '8081', '/Device/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(27, 83, NULL, NULL, NULL, NULL, NULL, 6, 5, NULL, 'localhost', '8081', '/Page/save', 'A', 0, NULL, 1, 'data_inserted', 1, 5, NULL, NULL, NULL, NULL)
,(28, 84, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 5, NULL, NULL, NULL, NULL)
,(29, 85, NULL, NULL, NULL, NULL, NULL, 6, 5, NULL, 'localhost', '8081', '/Page/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(30, 90, NULL, NULL, NULL, NULL, NULL, 2, 5, NULL, 'localhost', '8081', '/Email/save', 'A', 0, NULL, 1, 'data_inserted', 1, 1, NULL, NULL, NULL, NULL)
,(31, 91, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 1, NULL, NULL, NULL, NULL)
,(32, 92, NULL, NULL, NULL, NULL, NULL, 2, 5, NULL, 'localhost', '8081', '/Email/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(33, 93, NULL, NULL, NULL, NULL, NULL, 2, 5, NULL, 'localhost', '8081', '/Email/delete', 'A', 0, NULL, 1, 'data_deleted', 0, NULL, NULL, NULL, NULL, NULL)
,(34, 98, NULL, NULL, NULL, NULL, NULL, 4, 5, NULL, 'localhost', '8081', '/Tele/save', 'A', 0, NULL, 1, 'data_inserted', 1, 3, NULL, NULL, NULL, NULL)
,(35, 99, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 3, NULL, NULL, NULL, NULL)
,(36, 100, NULL, NULL, NULL, NULL, NULL, 4, 5, NULL, 'localhost', '8081', '/Tele/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(37, 101, NULL, NULL, NULL, NULL, NULL, 4, 5, NULL, 'localhost', '8081', '/Tele/delete', 'A', 0, NULL, 1, 'data_deleted', 0, NULL, NULL, NULL, NULL, NULL)
,(38, 107, '/subcatagory/getcat', '/subcatagory/getcat', 'localhost', '8083', 108, NULL, 2, NULL, NULL, NULL, NULL, 'A', 0, NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL)
,(39, 109, NULL, NULL, NULL, NULL, NULL, 25, 5, NULL, 'localhost', '8083', '/product/save', 'A', 0, NULL, 1, 'data_inserted', 1, 24, NULL, NULL, NULL, 113)
,(40, 110, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, 'A', 0, NULL, 1, 'form_reset', 1, 24, NULL, 109, NULL, 113)
,(41, 111, NULL, NULL, NULL, NULL, NULL, 25, 5, NULL, 'localhost', '8083', '/product/update', 'A', 0, NULL, 1, 'data_updated', 0, NULL, NULL, NULL, NULL, NULL)
,(42, 112, NULL, NULL, NULL, NULL, NULL, 24, 5, NULL, 'localhost', '8083', '/product/findbyproduct', 'A', 0, NULL, 1, 'data_display', 0, NULL, NULL, 113, NULL, 109)
,(43, 113, NULL, NULL, NULL, NULL, NULL, 25, 5, NULL, 'localhost', '8083', '/product/update', 'A', 0, NULL, 1, 'data_updated', 1, 24, NULL, NULL, NULL, NULL);