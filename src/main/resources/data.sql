
CREATE TABLE user_details(
    user_id INT(11) unsigned NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL DEFAULT '',
    last_name VARCHAR(255) NOT NULL DEFAULT '',
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255) NOT NULL DEFAULT '',
    city VARCHAR(255) NOT NULL DEFAULT '',
    country VARCHAR(255) NOT NULL DEFAULT '',
    address VARCHAR(255) NOT NULL DEFAULT '',
    username VARCHAR(255) NOT NULL DEFAULT '' UNIQUE,
    password VARCHAR(255) NOT NULL DEFAULT '' UNIQUE,
    active tinyint(1) NOT NULL DEFAULT '1',
    PRIMARY KEY(user_id)
);

CREATE TABLE item_details(
    item_id INT(11) unsigned  NOT NULL AUTO_INCREMENT,
    item_title VARCHAR(255) NOT NULL DEFAULT '',
    photo_url VARCHAR(255) NOT NULL DEFAULT '',
    item_price DOUBLE NOT NULL,
    stock_quantity INT(11) NOT NULL,
    PRIMARY KEY(item_id)
);

CREATE TABLE order_details(
    order_id INT(11) unsigned NOT NULL AUTO_INCREMENT,
    order_user_id INT(11) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    shipping_city VARCHAR(255) NOT NULL DEFAULT '',
    shipping_country VARCHAR(255) NOT NULL DEFAULT '',
    shipping_address VARCHAR(255) NOT NULL DEFAULT '',
    total_price DOUBLE NOT NULL,
    order_status VARCHAR(255) DEFAULT 'TEMP',
    PRIMARY KEY(order_id),
    FOREIGN KEY(order_user_id) REFERENCES user_details(user_id),
    FOREIGN KEY(shipping_address) REFERENCES user_details(address)
);

CREATE TABLE order_item(
    order_item_id INT(11) unsigned NOT NULL AUTO_INCREMENT,
    order_id INT(11) NOT NULL,
    item_id INT(11) NOT NULL,
    quantity INT(11) NOT NULL,
    PRIMARY KEY(order_item_id),
    FOREIGN KEY(order_id) REFERENCES order_details(order_id) ON DELETE CASCADE,
    FOREIGN KEY(item_id) REFERENCES item_details(item_id)
);

CREATE TABLE favorite_item(
    id INT(11) unsigned NOT NULL AUTO_INCREMENT,
    user_id INT(11) NOT NULL,
    item_id INT(11) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user_details(user_id),
    FOREIGN KEY(item_id) REFERENCES item_details(item_id)
);

INSERT INTO user_details(first_name, last_name, email, phone_number, city, country, address, username, password) VALUES
('reef', 'ben shimol', 'reef@gmail.com', 0535550331, 'eilat', 'israel', 'habracha 12/5' , 'reef', 'Reef2018!');

INSERT INTO item_details(item_title, photo_url, item_price, stock_quantity) VALUES
('Inter Miami Leonel Messi Shirt', 'https://bit.ly/3SD332W', 80.00, 25),
('Al Naser Cristiano Ronaldo Shirt', 'https://footdealer.co/wp-content/uploads/2023/08/MAILLOT-AL-NASSR-RONALDO-DOMICILE-2023-2024-1-768x713.jpg', 80.00, 25),
('Al Hilal Neymar JR Shirt', 'https://bit.ly/3Swij1z', 70.00, 25),
('PSG Kylian Mbappe Shirt', 'https://bit.ly/3SunFdK', 70.00, 25),
('Leonel Messi Shoes', 'https://tinyurl.com/5562m44v', 130.00, 15),
('Cristiano Ronaldo shoes', 'https://bit.ly/47eSpE0', 130.00, 15),
('Neymar JR Shoes', 'https://bit.ly/3u2JUxk', 110.00, 15),
('Kylian Mbappe shoes', 'https://bit.ly/3MxISQ9', 110.00, 15),
('Brazil Selection Scarf', 'https://bit.ly/4693J3k', 30.00, 50),
('Spain Selection Scarf', 'https://bit.ly/3QNK3xx', 30.00, 50),
('England Selection Scarf', 'https://m.media-amazon.com/images/I/91RuQLJib6L.jpg', 30.00, 50),
('Argentina Selection Scarf', 'https://ae01.alicdn.com/kf/S84ee575f748b4ef0810d1e90ff45a4682.png_640x640.png_.webp', 30.00, 50),
('La Liga Official Ball', 'https://bit.ly/3S4D8zi', 50.00, 100),
('Premier League Official Ball', 'https://bit.ly/3SsfwGl', 50.00, 100),
('Bundesliga Official Ball', 'https://bit.ly/3UgozeO', 50.00, 100),
('Champions League official Ball', 'https://bit.ly/42bfSV4', 50.00, 100);
