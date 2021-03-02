CREATE TABLE IF NOT EXISTS Component (
  ID serial primary key,
  seq_num INT NOT NULL,
  name VARCHAR(450) NOT NULL,
  parent_group VARCHAR(450) NULL,
  Group_name VARCHAR(45) NULL,
  Label_name VARCHAR(500) NULL,
  Form_name VARCHAR(45) NULL,
  C_code VARCHAR(450) NOT NULL,
  C_type VARCHAR(45) NULL,
  C_required INT NOT NULL,
  C_pattern INT NOT NULL,
  patterndesgin VARCHAR(500) NULL,
  parent_component_ID INT NOT NULL,
  date_modify timestamp NOT NULL,
  date_create timestamp NOT NULL,
  visible INT NOT NULL DEFAULT 0,
  disable INT NOT NULL,
  table_visible INT NOT NULL DEFAULT 0,
  table_disable INT NULL,
  label_icon VARCHAR(450) NULL,
  field_encry INT NOT NULL DEFAULT 0,
  required_error VARCHAR(450) NULL,
  patern_error VARCHAR(450) NULL,
  display_data_error INT NULL,
  Amethod VARCHAR(450) NULL);
  
  delete from Component ;
  
  INSERT INTO Component (ID, seq_num, name, parent_group, Group_name, Label_name, Form_name, C_code, C_type, C_required, C_pattern, patterndesgin, parent_component_ID, date_modify, date_create, visible, disable, table_visible, table_disable, label_icon, field_encry, required_error, patern_error, display_data_error, Amethod) VALUES(1, 1, 'username', NULL, NULL, 'username', 'form', 'username', 'input', 1, 0, NULL, 21, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (2, 2, 'password', NULL, NULL, 'password', 'form', 'password', 'input', 1, 0, NULL, 21, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 1, 'required', 'wrong_data', 1, NULL)
, (3, 3, 'button', NULL, NULL, 'submit', 'form', 'login', 'button', 1, 0, NULL, 21, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'onSubmit')
, (4, 1, 'id', 'user', NULL, 'id', 'form', 'id', 'label', 0, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (5, 2, 'profileimg', 'user', NULL, 'profileimg', 'form', 'profile_img', 'input', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (6, 3, 'firstName', 'user', NULL, 'firstName', 'form', 'firstname', 'input', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (7, 4, 'lastname', 'user', NULL, 'lastname', 'form', 'lastname', 'input', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (8, 5, 'birthdate', 'user', NULL, 'birthdate', 'form', 'birthday', 'input', 1, 1, '^(([1]{0,1}[9]{0,1}[0-9]{0,1}[0-9]{0,1})|([2]{0,1}[0]{0,1}[0-1]{0,1}[0-9]{0,1}))-([0-9]{0,2})-([0-9]{0,2})$', 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (9, 6, 'iDnumber', 'user', NULL, 'iDnumber', 'form', 'idnumber', 'input', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 1, 'required', 'wrong_data', 0, NULL)
, (10, 7, 'IDimgaes', 'user', NULL, 'IDimgaes', 'form', 'id_img', 'input', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (11, 8, 'id', 'login', NULL, 'id', 'form', 'id', 'label', 0, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (12, 9, 'username', 'login', NULL, 'username', 'form', 'username', 'input', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (13, 10, 'passwordEncy', 'login', NULL, 'passwordEncy', 'form', 'password', 'input', 1, 1, '^((?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\\d\\W])|(?=.*\\W)(?=.*\\d))|(?=.*\\W)(?=.*[A-Z])(?=.*\\d)).{8,})|(''Enter password'')$', 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 1, 'required', 'wrong_data', 1, NULL)
, (14, 11, 'notes', 'login', NULL, 'notes', 'form', 'notes', 'input', 0, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (15, 12, 'id', 'login', 'applicationID', 'applicationID', 'form', 'application_type', 'select', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (16, 13, 'id', 'login', 'usertypeID', 'usertypeID', 'form', 'type', 'select', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (17, 14, 'id', 'login', 'grouppriviledgeID', 'grouppriviledgeID', 'form', 'group_privil', 'select', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (18, 15, 'id', 'login', 'userstatusID', 'userstatusID', 'form', 'status', 'select', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (19, 16, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (20, 17, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (21, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 8, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (22, 2, 'button', NULL, NULL, 'Display', 'table1', 'display', 'button', 1, 0, NULL, 8, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'displayupdate')
, (23, 2, 'currencyname', NULL, NULL, 'currencyname', 'form', 'curreny', 'input', 1, 0, NULL, 13, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (24, 3, 'currencyISO', NULL, NULL, 'currencyISO', 'form', 'currenyiso', 'input', 1, 0, NULL, 13, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (25, 4, 'currencydescription', NULL, NULL, 'currencydescription', 'form', 'notes', 'input', 0, 0, NULL, 13, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (26, 5, 'id', NULL, 'allstatusID', 'allstatusID', 'form', 'status', 'select', 1, 0, NULL, 13, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (27, 6, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 13, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (28, 7, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 13, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (29, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 14, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (30, 2, 'flowtype', NULL, NULL, 'currencyname', 'form', 'flowtype', 'input', 1, 0, NULL, 11, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (31, 3, 'flowdescription', NULL, NULL, 'flowdescription', 'form', 'flowdescription', 'input', 1, 0, NULL, 11, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (32, 4, 'id', NULL, 'allstatusID', 'allstatusID', 'form', 'status', 'select', 1, 0, NULL, 11, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (33, 5, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 11, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (34, 6, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 11, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (35, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 12, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (36, 1, 'id', NULL, NULL, 'id', 'form', 'id', 'label', 0, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (37, 1, 'id', NULL, NULL, 'id', 'form', 'id', 'label', 0, 0, NULL, 13, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (38, 1, 'fromCustomercode', NULL, NULL, 'fromCustomercode', 'form', 'from_Customercode', 'input', 0, 0, NULL, 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (39, 2, 'id', NULL, 'fromaccounts', 'fromaccounts', 'form', 'from_account', 'select', 1, 0, NULL, 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-university', 0, 'required', 'wrong_data', 0, 'doaction')
, (40, 3, 'amount', NULL, NULL, 'amount', 'form', 'amount', 'input', 1, 1, '^((:?(?=[1])(10{0,8})|(?=[^0])(\\d{1,8})|0)\\.[0-9]{1,2})$|^((:?(?=[1])(10{0,8})|(?=[^0])(\\d{1,8})|0))$', 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-money-bill-wave', 0, 'required', 'wrong_data', 0, NULL)
, (41, 4, 'toCustomercode', NULL, NULL, 'toCustomercode', 'form', 'to_Customercode', 'input', 0, 0, NULL, 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (42, 5, 'id', NULL, 'toaccounts', 'toaccounts', 'form', 'to_account', 'select', 1, 0, NULL, 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-university', 0, 'required', 'wrong_data', 0, NULL)
, (43, 6, 'trxdesc', NULL, NULL, 'trxdesc', 'form', 'trxdesc', 'input', 1, 0, NULL, 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (44, 7, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (45, 8, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 22, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (46, 2, 'acctNumber', NULL, NULL, 'acctNumber', 'table', 'acctNumber', 'label', 1, 0, '', 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (47, 3, 'acctName', NULL, NULL, 'acctName', 'table', 'acctName', 'label', 1, 0, '', 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (48, 4, 'currbalance', NULL, NULL, 'currbalance', 'table', 'currbalance', 'label', 1, 0, '', 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (49, 5, 'avalbalance', NULL, NULL, 'avalbalance', 'table', 'avalbalance', 'label', 1, 0, '', 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (50, 6, 'customernumber', NULL, NULL, 'customernumber', 'table', 'customernumber', 'label', 1, 0, '', 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (51, 7, 'currencyISO', NULL, 'currencyID', 'currencyISO', 'table', 'currencyISO', 'label', 1, 0, '', 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (52, 18, 'button', NULL, NULL, 'update', 'form', 'update', 'button', 0, 0, NULL, 7, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (53, 8, 'id', NULL, 'allstatusID', 'allstatusID', 'table', 'status', 'select', 1, 0, NULL, 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (54, 9, 'button', NULL, NULL, 'submit', 'table', 'update', 'button', 1, 0, NULL, 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (55, 1, 'id', NULL, NULL, 'id', 'table', 'id', 'label', 1, 0, '', 23, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (56, 1, 'langcode', NULL, NULL, 'langcode', 'form', 'code', 'input', 1, 0, NULL, 15, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (57, 2, 'id', 'txtconv', 'languagesID', 'languagesID', 'form', 'language', 'select', 1, 0, NULL, 15, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 1, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (58, 3, 'returnLang', 'txtconv', NULL, 'returnLang', 'form', 'text', 'input', 1, 0, NULL, 15, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 1, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (59, 4, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 15, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (60, 5, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 15, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (61, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 16, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (62, 1, 'languagename', NULL, NULL, 'languagename', 'form', 'languagename', 'input', 1, 0, NULL, 17, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (63, 1, 'languagecode', NULL, NULL, 'languagecode', 'form', 'languagecode', 'input', 1, 0, NULL, 17, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (64, 4, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 17, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (65, 5, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 17, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (66, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 18, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (67, 1, 'id', NULL, NULL, 'id', 'form', 'id', 'label', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (68, 2, 'devicename', NULL, NULL, 'devicename', 'form', 'devicename', 'label', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (69, 3, 'deviceinfo', NULL, NULL, 'deviceinfo', 'form', 'deviceinfo', 'label', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (70, 4, 'deviceip', NULL, NULL, 'deviceip', 'form', 'deviceip', 'label', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (71, 5, 'devicemac', NULL, NULL, 'devicemac', 'form', 'languagename', 'label', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (72, 6, 'deviceosversion', NULL, NULL, 'deviceosversion', 'form', 'languagename', 'label', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (73, 7, 'deviceosunknow', NULL, NULL, 'deviceinfo', 'form', 'deviceinfo', 'label', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (74, 8, 'id', NULL, 'devicetypeID', 'devtype', 'table', 'status', 'select', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 1, 1, 1, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (75, 9, 'id', NULL, 'deviceOSID', 'deviceOS', 'table', 'status', 'select', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 1, 1, 1, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (76, 10, 'id', NULL, 'devicestatusID', 'dstatus', 'table', 'status', 'select', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (77, 11, 'button', NULL, NULL, 'submit', 'table', 'Display', 'button', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (78, 12, 'button', NULL, NULL, 'submit', 'table', 'update', 'button', 1, 0, NULL, 9, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (79, 1, 'page_name', NULL, NULL, 'page_name', 'form', 'page_name', 'label', 1, 0, NULL, 10, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (80, 2, 'page_Date', NULL, NULL, 'page_Date', 'form', 'page_Date', 'label', 1, 0, NULL, 10, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (81, 1, 'pagename', NULL, NULL, 'pagename', 'form', 'pagename', 'input', 1, 0, NULL, 5, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (82, 2, 'pagemenu', NULL, NULL, 'pagemenu', 'form', 'pagemenu', 'input', 1, 0, NULL, 5, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (83, 3, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 5, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (84, 4, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 5, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (85, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 6, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (86, 1, 'emailuser', 'email', NULL, 'emailuser', 'form', 'emailuser', 'input', 1, 1, '[a-zA-Z0-9.!#$%&ampâ€™*+/=?^_{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)+', 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (87, 2, 'userid', NULL, NULL, 'userid', 'form', 'userid', 'input', 1, 0, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (88, 3, 'id', 'email', 'datastatusID', 'dstatus', 'table', 'status', 'select', 1, 0, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (89, 4, 'emailPrimary', 'email', NULL, 'emailPrimary', 'table', 'emailPrimary', 'select', 1, 0, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (90, 5, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (91, 6, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 1, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (92, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 2, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (93, 1, 'button', NULL, NULL, 'submit', 'table1', 'delete', 'button', 1, 0, NULL, 2, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (94, 1, 'phoneNo', 'tele', NULL, 'phoneNo', 'form', 'phoneNo', 'input', 1, 1, '^[0-9]{10,12}$', 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (95, 2, 'userid', NULL, NULL, 'userid', 'form', 'userid', 'input', 1, 0, NULL, 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (96, 3, 'id', 'tele', 'datastatusID', 'dstatus', 'table', 'status', 'select', 1, 0, NULL, 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (97, 4, 'telePrimary', 'tele', NULL, 'telePrimary', 'table', 'telePrimary', 'select', 1, 0, NULL, 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (98, 5, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (99, 6, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 3, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (100, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 4, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (101, 1, 'button', NULL, NULL, 'submit', 'table1', 'delete', 'button', 1, 0, NULL, 4, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (102, 1, 'productName', NULL, NULL, 'productName', 'form', 'productName', 'input', 1, 0, NULL, 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (103, 2, 'productIcon', NULL, NULL, 'productIcon', 'form', 'productIcon', 'input', 1, 0, NULL, 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (104, 3, 'productParagraph', NULL, NULL, 'productParagraph', 'form', 'productParagraph', 'input', 1, 0, NULL, 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (105, 4, 'productPrice', NULL, NULL, 'productPrice', 'form', 'productPrice', 'input', 1, 1, '^((:?(?=[1])(10{0,8})|(?=[^0])(\\d{1,8})|0)\\.[0-9]{1,2})$|^((:?(?=[1])(10{0,8})|(?=[^0])(\\d{1,8})|0))$', 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-money-bill-wave', 0, 'required', 'wrong_data', 0, NULL)
, (106, 5, 'productSale', NULL, NULL, 'productSale', 'form', 'productSale', 'input', 1, 1, '^((:?(?=[1])(10{0,8})|(?=[^0])(\\d{1,8})|0)\\.[0-9]{1,2})$|^((:?(?=[1])(10{0,8})|(?=[^0])(\\d{1,2})|0))$', 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-money-bill-wave', 0, 'required', 'wrong_data', 0, NULL)
, (107, 6, 'id', 'subcatalogID', 'maincatalogID', 'maincatagory', 'form', 'maincatagory', 'select', 1, 0, NULL, 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 0, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (108, 7, 'id', NULL, 'subcatalogID', 'subcatalogName', 'form', 'subcatagory', 'select', 1, 0, NULL, 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 0, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, NULL)
, (109, 8, 'button', NULL, NULL, 'submit', 'form', 'submit', 'button', 1, 0, NULL, 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (110, 9, 'button', NULL, NULL, 'reset', 'form', 'reset', 'button', 1, 0, NULL, 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction')
, (111, 1, 'button', NULL, NULL, 'submit', 'table1', 'update', 'button', 1, 0, NULL, 25, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'tableaction')
, (112, 2, 'button', NULL, NULL, 'Display', 'table1', 'display', 'button', 1, 0, NULL, 25, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 1, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'displayupdate')
, (113, 10, 'button', NULL, NULL, 'update', 'form', 'update', 'button', 0, 0, NULL, 24, '2020-01-04 14:17:05', '2020-01-04 14:17:05', 0, 0, 1, 0, 'fas fa-user', 0, 'required', 'wrong_data', 0, 'doaction');

  
  