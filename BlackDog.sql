DROP DATABASE IF EXISTS BlackDog;
CREATE DATABASE BlackDog;

USE BlackDog;

-- DROP TABLE client;
CREATE TABLE client(
    client_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    pwd VARCHAR(255) NOT NULL
);

-- DROP TABLE administrator;
CREATE TABLE administrator(
    admin_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(80) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    pwd VARCHAR(255) NOT NULL
);

-- DROP TABLE customer_order;
CREATE TABLE customer_order(
    customer_order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    order_date DATETIME NOT NULL,
    address VARCHAR(40) NOT NULL,
    amount NUMERIC(6,2) NOT NULL,
    state VARCHAR(15) NOT NULL,
    evidence_image VARCHAR(70) NOT NULL,

    FOREIGN KEY (client_id) REFERENCES client(client_id)
);

-- DROP TABLE product;
CREATE TABLE product(
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    image VARCHAR(70) NOT NULL,
    price NUMERIC(4,2) NOT NULL,
    type VARCHAR(20) NOT NULL
);

-- DROP TABLE order_detail;
CREATE TABLE order_detail(
    customer_order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    PRIMARY KEY (customer_order_id, product_id),
    FOREIGN KEY (customer_order_id) REFERENCES customer_order(customer_order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- DROP TABLE ingredient;
CREATE TABLE ingredient(
    ingredient_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    price NUMERIC(4,2) NOT NULL
);

-- DROP TABLE product_ingredient;
CREATE TABLE product_ingredient(
    product_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    quantity INT NOT NULL,

    PRIMARY KEY (product_id, ingredient_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id)
);

SELECT * FROM client;
SELECT * FROM administrator;