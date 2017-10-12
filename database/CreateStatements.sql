CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    surname VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50),
    user_type INT NOT NULL,
    PRIMARY KEY (id)
);
	

CREATE TABLE coordinates (
	id INT NOT NULL AUTO_INCREMENT,
    latitude INT,
    longitude INT,
    country VARCHAR(50),
    city VARCHAR(50),
    ZIP_code VARCHAR(50),
    street VARCHAR(50),
    number VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE shop (
	id	INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50),
    description VARCHAR(32000),
    picture_path	VARCHAR(50),
    website_link	VARCHAR(50),
    opening_hours	VARCHAR(100),
    owner_id INT,
    location_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY (owner_id) REFERENCES user(id),
    FOREIGN KEY (location_id) REFERENCES coordinates(id)
);

CREATE TABLE product (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50),
    category VARCHAR(50),
    description VARCHAR(32000),
    picture_path VARCHAR(50),
    price FLOAT,
    shop_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (shop_id) REFERENCES shop(id)
);

CREATE TABLE purchase (
	id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    date TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);
    

CREATE TABLE review (
	id INT NOT NULL AUTO_INCREMENT,
	overall_value INT,
    quality INT,
    shop_service INT,
    value_for_money INT,
    title VARCHAR(50),
    review_description VARCHAR(32000),
    purchase_id INT NOT NULL,
    date TIMESTAMP,
    replied BOOL,
    reply_text VARCHAR(32000),
    reply_date TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (purchase_id) REFERENCES purchase(id)
);

CREATE TABLE issue_report (
	id INT NOT NULL,
    purchase_id INT NOT NULL,
    description VARCHAR(32000),
    issue_date TIMESTAMP,
    validator INT,
    validation_date TIMESTAMP,
    result BOOL,
    PRIMARY KEY(id),
    FOREIGN KEY(purchase_id) REFERENCES purchase(id)
);

