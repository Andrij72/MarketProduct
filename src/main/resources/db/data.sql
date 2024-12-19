
truncate table shop.buyer cascade;
ALTER SEQUENCE shop.buyers_id_seq RESTART WITH 1;
INSERT INTO shop.buyer (first_name, last_name)
VALUES ('John', 'Doe'),
       ('Jane', 'Smith'),
       ('Alice', 'Smith'),
       ('Bob', 'Griffith');

truncate table shop.product cascade;
ALTER SEQUENCE shop.products_id_seq RESTART WITH 1;

INSERT INTO shop.product (name, cost)
VALUES ('Bread', 24),
       ('Mineral water', 12),
       ('Sour cream', 30),
       ('Cheese', 100),
       ('Milk', 40),
       ('Meat', 110);


truncate table shop.purchase cascade;
ALTER SEQUENCE shop.purchases_id_seq RESTART WITH 1;

INSERT INTO shop.purchase (product_id, date_purchase, buyer_id)
VALUES (1, '2024-11-06 10:00:00+00', 1),
       (2, '2024-11-16 11:00:00+00', 1),
       (3, '2024-11-12 10:00:00+00', 2),
       (4, '2024-11-12 11:00:00+00', 2),
       (6, '2024-11-12 10:00:00+00', 2),
       (5, '2024-11-14 11:00:00+00', 3),
       (1, '2024-11-14 10:00:00+00', 3),
       (2, '2024-11-14 11:00:00+00', 3),
       (1, '2024-11-14 10:00:00+00', 4),
       (3, '2024-11-14 11:00:00+00', 4),
       (5, '2024-11-14 10:00:00+00', 4),
       (6, '2024-11-14 11:00:00+00', 4),
       (6, '2024-11-15 10:00:00+00', 4),
       (1, '2024-11-15 11:00:00+00', 4);
