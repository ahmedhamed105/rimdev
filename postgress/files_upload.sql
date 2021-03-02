CREATE TABLE IF NOT EXISTS files_upload(
  ID serial primary key,
  files_url VARCHAR(500) NOT NULL,
  files_size DECIMAL(20,6) NOT NULL,
  files_name VARCHAR(450) NOT NULL,
  filecomruntime DECIMAL(20,6) NULL,
  file_status_ID INT NOT NULL,
  file_path VARCHAR(450) NULL,
  file_type_ID INT NOT NULL,
  file_public INT NOT NULL);
  
  
  INSERT INTO files_upload (files_url, files_size, files_name, filecomruntime, file_status_ID, file_path, file_type_ID, file_public) VALUES 
  ( 'http://localhost:8081/file/downloadFile/back_login.jpg', 4.000000, 'back_login.jpg', 0.000000, 1, 'D:\\RIM_DEV\\Core\\rimdev\\Files\\general', 1, 1)
, ( 'http://localhost:8081/file/downloadFile/avatar.png', 4.000000, 'avatar.png', 0.000000, 1, 'D:\\RIM_DEV\\Core\\rimdev\\Files\\general', 1, 1)
, ( 'http://localhost:8081/file/downloadFile/avatar2.png', 4.000000, 'avatar2.png', 0.000000, 1, 'D:\\RIM_DEV\\Core\\rimdev\\Files\\general', 1, 1)
, ( 'http://localhost:8081/file/downloadFile/avatar3.png', 4.000000, 'avatar3.png', 0.000000, 1, 'D:\\RIM_DEV\\Core\\rimdev\\Files\\general', 1, 1)
, ( 'http://localhost:8081/file/downloadFile/avatar4.png', 4.000000, 'avatar4.png', 0.000000, 1, 'D:\\RIM_DEV\\Core\\rimdev\\Files\\general', 1, 1)
, ( 'http://localhost:8081/file/downloadFile/avatar5.png', 4.000000, 'avatar5.png', 0.000000, 1, 'D:\\RIM_DEV\\Core\\rimdev\\Files\\general', 1, 1);
