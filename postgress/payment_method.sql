CREATE TABLE IF NOT EXISTS payment_method(
  ID serial primary key,
  pay_method VARCHAR(450) NOT NULL,
  pay_icon VARCHAR(45) NULL);