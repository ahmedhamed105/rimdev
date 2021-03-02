CREATE TABLE IF NOT EXISTS Application(
  ID serial primary key,
  App_name VARCHAR(450) NOT NULL,
  App_search INT NOT NULL,
  search_webserv VARCHAR(450) NULL,
  notification_falg INT NOT NULL,
  footer_html VARCHAR(10000) NOT NULL);
  
  INSERT INTO Application (ID, App_name, App_search, search_webserv, notification_falg, footer_html) VALUES
  (1, 'Admin', 1, '/search', 1, '<strong>Copyright &copy; 2020 <a href=\"/\">RIMDEV</a>.</strong> All rights reserved.  <div class=\"float-right d-none d-sm-inline-block\"> <b>Version</b> 3.0.4 </div>')
, (2, 'Web', 1, '/search', 1, '<strong>Copyright &copy; 2020 <a href=\"/\">RIMDEV</a>.</strong> All rights reserved.  <div class=\"float-right d-none d-sm-inline-block\"> <b>Version</b> 3.0.4 </div>')
, (3, 'IOS', 1, '/search', 1, '<strong>Copyright &copy; 2020 <a href=\"/\">RIMDEV</a>.</strong> All rights reserved.  <div class=\"float-right d-none d-sm-inline-block\"> <b>Version</b> 3.0.4 </div>')
, (4, 'andriod', 1, '/search', 1, '<strong>Copyright &copy; 2020 <a href=\"/\">RIMDEV</a>.</strong> All rights reserved.  <div class=\"float-right d-none d-sm-inline-block\"> <b>Version</b> 3.0.4 </div>');
