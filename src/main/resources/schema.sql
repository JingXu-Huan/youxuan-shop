-- 创建用户表
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT FALSE
);

-- 创建商品表
CREATE TABLE IF NOT EXISTS goods (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    picture_url VARCHAR(500) NOT NULL,
    release_time DATETIME NOT NULL
);

-- 创建订单表
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_time DATETIME NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,
    address VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 创建订单商品表
CREATE TABLE IF NOT EXISTS orders_goods (
    order_id INT NOT NULL,
    good_id INT NOT NULL,
    purchase_quantity INT NOT NULL,
    PRIMARY KEY (order_id, good_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (good_id) REFERENCES goods(id)
);

