CREATE TABLE IF NOT EXISTS bill_main(
  ID serial primary key,
  order_no VARCHAR(450) NOT NULL,
  bill_number VARCHAR(450) NOT NULL,
  order_address INT NOT NULL,
  order_user INT NULL,
  payment_method_ID INT NOT NULL,
  bill_status_ID INT NOT NULL,
  bill_notes VARCHAR(5000) NULL,
  order_rating INT NULL,
  create_date timestamp NOT NULL,
  modify_date timestamp NOT NULL);