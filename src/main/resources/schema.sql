DROP TABLE IF EXISTS TBL_PRODUCT;
 
CREATE TABLE TBL_PRODUCT (
  product_id INT AUTO_INCREMENT  PRIMARY KEY,
  product_name VARCHAR(250) NOT NULL,
  product_price NUMBER NOT NULL,
  avail_quantity VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS TBL_CUSTOMER;
 
CREATE TABLE TBL_CUSTOMER (
  customer_id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  contact_number NUMBER NOT NULL
);

DROP TABLE IF EXISTS TBL_PRODUCT_BOOKING;

CREATE TABLE TBL_PRODUCT_BOOKING (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  customer_id NUMBER NOT NULL,
  product_id NUMBER NOT NULL,
  price NUMBER NOT NULL
);