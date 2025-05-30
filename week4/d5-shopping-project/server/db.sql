-- DROP TABLE IF EXISTS user;
--
-- CREATE TABLE user
-- (
--     id       INT AUTO_INCREMENT PRIMARY KEY,
--     email    VARCHAR(255) NOT NULL,
--     password VARCHAR(255) NOT NULL,
--     role     INT          NOT NULL,
--     username VARCHAR(255) NOT NULL
-- )

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
    VALUE (
    'product desc', 'prod name', 1, 10, 5
);

SELECT * FROM product;

-- ---------------------------------
DROP TABLE IF EXISTS product;
CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         description    VARCHAR(255) NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         quantity     INT          NOT NULL,
                         retail_price DOUBLE NOT NULL,
                         wholesale_price DOUBLE NOT NULL
);

-- ---------------------------------

DROP TABLE IF EXISTS 'order';
CREATE TABLE 'order'
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY
)