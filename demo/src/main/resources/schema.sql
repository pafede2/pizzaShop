DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  uuid VARCHAR(255) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  card_number VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS pay_option;
CREATE TABLE pay_option (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS pizza_type;
CREATE TABLE pizza_type (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  price DECIMAL(6,2) NOT NULL
);

DROP TABLE IF EXISTS toppings;
CREATE TABLE topping (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  price DECIMAL(6,2) NOT NULL
);

DROP TABLE IF EXISTS order_status;
CREATE TABLE order_status (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS pizza;
CREATE TABLE pizza (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  pizza_type_id INT NOT NULL
);

DROP TABLE IF EXISTS pizza_to_topping;
CREATE TABLE pizza_to_topping (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  pizza_id INT NOT NULL,
  topping_id INT NOT NULL
);

DROP TABLE IF EXISTS pizza_order;
CREATE TABLE pizza_order (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  uuid VARCHAR(255) NOT NULL,
  customer_id INT NOT NULL,
  pay_option_id INT NOT NULL,
  order_status_id INT NOT NULL,
  payment_status BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS order_to_pizza;
CREATE TABLE order_to_pizza (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  order_id INT NOT NULL,
  pizza_id INT NOT NULL
);