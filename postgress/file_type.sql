CREATE TABLE IF NOT EXISTS file_type (
  ID serial primary key,
  ftype VARCHAR(450) NOT NULL,
  fmime VARCHAR(450) NOT NULL);
  
  
  INSERT INTO file_type (ID, ftype, fmime) VALUES
  (1, 'png', 'image/png')
, (2, 'jpeg', 'image/jpeg')
, (3, 'pdf', 'application/pdf')
, (4, 'docx', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document')
, (5, 'doc', 'application/msword')
, (6, 'pptx', '	application/vnd.openxmlformats-officedocument.presentationml.presentation')
, (7, 'ppt', 'application/vnd.ms-powerpoint');