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

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description    VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    quantity     INT          NOT NULL,
    retail_price DOUBLE NOT NULL,
    wholesale_price DOUBLE NOT NULL
);