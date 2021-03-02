CREATE TABLE IF NOT EXISTS bill_status(
  ID serial primary key,
  bstatus VARCHAR(45) NULL);
  
  INSERT INTO bill_status (ID, bstatus) VALUES
 (1, 'order under condtruction')
, (2, 'order start')
, (3, 'order catched')
, (4, 'man on way')
, (5, 'man started job')
, (6, 'order finished')
, (7, 'order paied')
, (8, 'order finised');
