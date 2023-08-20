INSERT INTO cart (id) VALUES (1);
INSERT INTO cart (id) VALUES (2);
INSERT INTO cart (id) VALUES (3);
INSERT INTO cart (id) VALUES (4);
INSERT INTO cart (id) VALUES (5);

INSERT INTO card (id, name, number, cvc, expiry_date) VALUES (1, 'blanca', '4916844037092893', 111, '012025');
INSERT INTO card (id, name, number, cvc, expiry_date) VALUES (2, 'armando', '4916844037092893', 222, '022026');
INSERT INTO card (id, name, number, cvc, expiry_date) VALUES (3, 'manuel', '4916844037092893', 333, '032027');
INSERT INTO card (id, name, number, cvc, expiry_date) VALUES (4, 'teresa', '4916844037092893', 444, '042028');
INSERT INTO card (id, name, number, cvc, expiry_date) VALUES (5, 'nana', '4916844037092893', 555, '052029');

INSERT INTO wishlist (id) VALUES (1);
INSERT INTO wishlist (id) VALUES (2);
INSERT INTO wishlist (id) VALUES (3);
INSERT INTO wishlist (id) VALUES (4);
INSERT INTO wishlist (id) VALUES (5);

INSERT INTO user (id, name, email, cart_id, card_id, wishlist_id) VALUES (1, 'blanca', 'blanca@gmail.com', 1, 1, 1);
INSERT INTO user (id, name, email, cart_id, card_id, wishlist_id) VALUES (2, 'armando', 'armando@gmail.com', 2, 2, 2);
INSERT INTO user (id, name, email, cart_id, card_id, wishlist_id) VALUES (3, 'manuel', 'manuel@gmail.com', 3, 3, 3);
INSERT INTO user (id, name, email, cart_id, card_id, wishlist_id) VALUES (4, 'teresa', 'teresa@gmail.com', 4, 4, 4);
INSERT INTO user (id, name, email, cart_id, card_id, wishlist_id) VALUES (5, 'nana', 'nana@gmail.com', 5, 5, 5);
INSERT INTO user (id, name, email, cart_id, card_id, wishlist_id) VALUES (6, 'elena', 'elena@gmail.com', null, null, null);

INSERT INTO product (id, name, price) VALUES (1, 'fish', 3.4);
INSERT INTO product (id, name, price) VALUES (2, 'halloumi', 3.7);
INSERT INTO product (id, name, price) VALUES (3, 'deodorant', 2.5);
INSERT INTO product (id, name, price) VALUES (4, 'detergent', 8.0);
INSERT INTO product (id, name, price) VALUES (5, 'towel', 10.0);

INSERT INTO cart_product (cart_id, product_id) VALUES (1, 1);
INSERT INTO cart_product (cart_id, product_id) VALUES (1, 2);
INSERT INTO cart_product (cart_id, product_id) VALUES (1, 3);
INSERT INTO cart_product (cart_id, product_id) VALUES (1, 4);
INSERT INTO cart_product (cart_id, product_id) VALUES (2, 1);
INSERT INTO cart_product (cart_id, product_id) VALUES (2, 2);
INSERT INTO cart_product (cart_id, product_id) VALUES (2, 3);
INSERT INTO cart_product (cart_id, product_id) VALUES (2, 4);
INSERT INTO cart_product (cart_id, product_id) VALUES (3, 1);
INSERT INTO cart_product (cart_id, product_id) VALUES (3, 2);
INSERT INTO cart_product (cart_id, product_id) VALUES (3, 3);
INSERT INTO cart_product (cart_id, product_id) VALUES (3, 4);
INSERT INTO cart_product (cart_id, product_id) VALUES (4, 1);
INSERT INTO cart_product (cart_id, product_id) VALUES (4, 2);
INSERT INTO cart_product (cart_id, product_id) VALUES (4, 3);
INSERT INTO cart_product (cart_id, product_id) VALUES (4, 4);
INSERT INTO cart_product (cart_id, product_id) VALUES (5, 1);
INSERT INTO cart_product (cart_id, product_id) VALUES (5, 2);
INSERT INTO cart_product (cart_id, product_id) VALUES (5, 3);
INSERT INTO cart_product (cart_id, product_id) VALUES (5, 4);

INSERT INTO purchase (id, total_value, user_id) VALUES (1, 39.5, 1);
INSERT INTO purchase (id, total_value, user_id) VALUES (2, 67.8, 2);
INSERT INTO purchase (id, total_value, user_id) VALUES (3, 25.5, 3);
INSERT INTO purchase (id, total_value, user_id) VALUES (4, 90.2, 4);
INSERT INTO purchase (id, total_value, user_id) VALUES (5, 70.0, 5);

INSERT INTO purchase_product (purchase_id, product_id) VALUES (1, 1);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (1, 3);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (1, 5);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (2, 1);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (2, 2);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (2, 4);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (3, 2);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (3, 4);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (4, 3);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (4, 5);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (5, 1);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (5, 2);
INSERT INTO purchase_product (purchase_id, product_id) VALUES (5, 4);
--
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (1, 1);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (1, 2);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (1, 3);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (1, 4);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (2, 1);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (2, 2);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (2, 3);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (2, 4);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (3, 1);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (3, 2);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (3, 3);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (3, 4);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (4, 1);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (4, 2);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (4, 3);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (4, 4);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (5, 1);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (5, 2);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (5, 3);
INSERT INTO wishlist_product (wishlist_id, product_id) VALUES (5, 4);