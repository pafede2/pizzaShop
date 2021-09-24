INSERT INTO customer (uuid, first_name, last_name, card_number) VALUES
  ('19e7a509-38a5-401a-8467-6b22ecbe519f', 'Alina', 'Lopez', '1234'),
  ('6d506b61-631d-49db-84a1-da1ef497fd1f', 'Marcia', 'Tenemo', '5678'),
  ('1b0aafc3-c89a-4ea3-b79c-f4da18b905e2', 'Albert', 'Rikol', '9876');

INSERT INTO pay_option (name) VALUES
  ('ONLINE'),
  ('OFFLINE');

INSERT INTO pizza_type (name, price) VALUES
  ('NORMAL', 5.00),
  ('VEGGIE', 5.60);

INSERT INTO topping (name, price) VALUES
  ('cheese', 1.00),
  ('egg', 1.50),
  ('olives', 2.00);

INSERT INTO order_status (name) VALUES
  ('WAITING'),
  ('COOKING'),
  ('READY'),
  ('IN_DELIVERY'),
  ('DELIVERED'),
  ('CANCELLED');

INSERT INTO pizza (pizza_type_id) VALUES
  (1),
  (2),
  (1),
  (1),
  (2);

INSERT INTO pizza_to_topping (pizza_id, topping_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 1),
  (2, 3),
  (3, 1);

INSERT INTO pizza_order (uuid, customer_id, pay_option_id, order_status_id, payment_status, delivery_address) VALUES
  ('123e4567-e89b-42d3-a456-556642440000', 1, 1, 1, FALSE, 'Evergreen Terrace 123'),
  ('eb5dcd25-794d-41bc-8c80-915b339aef08', 2, 1, 1, FALSE, 'Av. Siempreviva 345'),
  ('0c810aed-0b11-4fee-86c5-c60f0909e9f3', 3, 2, 3, TRUE, 'Evergreen Av 987');

INSERT INTO order_to_pizza (order_id, pizza_id) VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (3, 5);
