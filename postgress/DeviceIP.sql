CREATE TABLE IF NOT EXISTS DeviceIP (
  ID serial primary key,
  ip_address VARCHAR(450) NOT NULL,
  country VARCHAR(450) NULL,
  city VARCHAR(450) NULL,
  state VARCHAR(450) NULL,
  latitude VARCHAR(450) NULL,
  longitude VARCHAR(450) NULL,
  subneting VARCHAR(450) NULL,
  timezone VARCHAR(450) NULL,
  DEVICE_ID INT NOT NULL,
  Deviceip_modify timestamp NOT NULL,
  Deviceip_create timestamp NOT NULL);