INSERT INTO type_products(type_id, name) VALUES('SER', 'Servicio');
INSERT INTO type_products(type_id, name) VALUES('PRO', 'Producto');

INSERT INTO brands(brand_id, name, state) VALUES(0, 'Valentina Fashion', 1);
INSERT INTO brands(brand_id, name, state) VALUES(1, 'ZARA', 1);

INSERT INTO products (product_id, name, type_id, state) VALUES(10000, 'Pantalon', 'PRO', 1);
INSERT INTO products (product_id, name, type_id, state) VALUES(10001, 'Ajuste Pantalon', 'SER', 1);
INSERT INTO products (product_id, name, type_id, state) VALUES(35445, 'Camisa', 'PRO', 1);
INSERT INTO products (product_id, name, type_id, state) VALUES(35500, 'Ajuste Camisa', 'SER', 1);

INSERT INTO prices_list (price_list_id, state) VALUES(1, 1);
INSERT INTO prices_list (price_list_id, state) VALUES(2, 1);
INSERT INTO prices_list (price_list_id, state) VALUES(3, 1);
INSERT INTO prices_list (price_list_id, state) VALUES(4, 1);

INSERT INTO prices (prices_id, brand_id, start_date, end_date, price_list_id, product_id, priority, price, curr) VALUES(1, 1, '2020-06-14 00.00.00', '2020-12-31 23.59.59', 1, 35445, 0, 35.50, 'EUR');
INSERT INTO prices (prices_id, brand_id, start_date, end_date, price_list_id, product_id, priority, price, curr) VALUES(2, 1, '2020-06-14 15.00.00', '2020-06-14 18.30.00', 2, 35445, 1, 25.45, 'EUR');
INSERT INTO prices (prices_id, brand_id, start_date, end_date, price_list_id, product_id, priority, price, curr) VALUES(3, 1, '2020-06-15 00.00.00', '2020-06-15 11.00.00', 3, 35445, 1, 35.50, 'EUR');
INSERT INTO prices (prices_id, brand_id, start_date, end_date, price_list_id, product_id, priority, price, curr) VALUES(4, 1, '2020-06-15 16.00.00', '2020-12-31 23.59.59', 4, 35445, 1, 35.50, 'EUR');