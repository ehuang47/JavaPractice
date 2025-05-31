SHOW DATABASES;
CREATE DATABASE shopping;
USE shopping;
SHOW TABLES;
DESCRIBE product;
-- -----------
DROP TABLE IF EXISTS product;
CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         description    VARCHAR(255) NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         quantity     INT          NOT NULL,
                         retail_price DOUBLE NOT NULL,
                         wholesale_price DOUBLE NOT NULL
);

INSERT INTO product (description, name, quantity, retail_price, wholesale_price)
VALUES ('description 1', 'prod 1', 5, 10, 5),
       ('description 2', 'prod 2', 5, 10, 5),
       ('description 3', 'prod 3', 5, 10, 5);

SELECT * FROM product;

-- ---------------------------------

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role INT,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255)
);

INSERT INTO user (role, username, password, email) VALUES
    (0, 'test', 'test', 'test@gmail.com'),
    (1, 'admin', 'admin', 'admin@gmail.com');

SELECT * from user;
-- ---------------------------------

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status ENUM('PROCESSING', 'COMPLETED', 'CANCELED') NOT NULL DEFAULT 'PROCESSING',
    date_placed DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO `order` (user_id)
VALUES (1),
       (1),
       (1);

SELECT * from `order`;
-- ---------------------------------

DROP TABLE IF EXISTS order_item;
CREATE TABLE order_item
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchased_price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    wholesale_price DOUBLE NOT NULL,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `order`(id),
    FOREIGN KEY (product_id) REFERENCES `product`(id)
);

INSERT INTO order_item (purchased_price, quantity, wholesale_price, order_id, product_id)
VALUES (10, 1, 5, 1, 1),
       (10, 1, 5, 1, 2),
       (10, 1, 5, 1, 3),
       (10, 1, 5, 2, 1),
       (10, 1, 5, 2, 2),
       (10, 1, 5, 2, 3),
       (10, 1, 5, 3, 1),
       (10, 1, 5, 3, 2),
       (10, 1, 5, 3, 3);

SELECT * from product;
SELECT * from order_item;

-- ---------------------------------

DROP TABLE IF EXISTS watchlist;
CREATE TABLE watchlist
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) references user(id),
    FOREIGN KEY (product_id) references product(id)
);

INSERT INTO watchlist (user_id, product_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);

SELECT * FROM watchlist;
