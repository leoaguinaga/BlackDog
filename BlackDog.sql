/*
 DROP DATABASE IF EXISTS BlackDog;
 CREATE DATABASE BlackDog;

 USE BlackDog;
*/

-- DROP TABLE client;
CREATE TABLE client(
	client_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	phone_number VARCHAR(15),
	email VARCHAR(50) UNIQUE,
	pwd VARCHAR(255)
);

-- DROP TABLE administrator;
CREATE TABLE administrator(
	admin_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	full_name VARCHAR(80),
	email VARCHAR(50) UNIQUE,
	pwd VARCHAR(255)
);

-- DROP TABLE customer_order;
CREATE TABLE customer_order(
	customer_order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	client_id BIGINT,
	order_date DATETIME,
    address VARCHAR(40),
    amount NUMERIC(4,2),
    state VARCHAR(15),
    evidence_image VARCHAR(70),

	FOREIGN KEY (client_id) REFERENCES client(client_id)
);

-- DROP TABLE product;
CREATE TABLE product(
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    image VARCHAR(70),
    price NUMERIC(2,2),
    type VARCHAR(20)
);

-- DROP TABLE order_detail;
CREATE TABLE order_detail(
    customer_order BIGINT,
    product_id BIGINT,

    PRIMARY KEY (customer_order, product_id),
    FOREIGN KEY (customer_order) REFERENCES customer_order(customer_order_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- DROP TABLE ingredient;
CREATE TABLE ingredient(
    ingredient_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25)
);

-- DROP TABLE product_ingredient;
CREATE TABLE product_ingredient(
	product_id BIGINT,
	ingredient_id BIGINT,
    quantity INT,

    PRIMARY KEY (product_id, ingredient_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id),
	FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id)
);
