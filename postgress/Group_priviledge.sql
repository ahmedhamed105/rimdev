CREATE TABLE IF NOT EXISTS Group_priviledge(
  ID serial primary key,
  Group_name VARCHAR(450) NOT NULL,
  Group_status_ID INT NOT NULL);
  
  INSERT INTO Group_priviledge (ID, Group_name, Group_status_ID) VALUES 
 (1, 'public', 1)
,(2, 'Admin_user', 1);
