-- 清空表数据（可选，如果需要重新插入数据）
-- TRUNCATE TABLE orders_goods;
-- TRUNCATE TABLE orders;
-- TRUNCATE TABLE goods;
-- TRUNCATE TABLE users;

-- 插入用户数据
INSERT INTO users (name, password, address, is_admin) VALUES
('admin', 'admin123', '北京市朝阳区', TRUE),
('zhangsan', '123456', '上海市浦东新区', FALSE),
('lisi', '123456', '广州市天河区', FALSE),
('wangwu', '123456', '深圳市南山区', FALSE),
('zhaoliu', '123456', '杭州市西湖区', FALSE),
('test01', '123456', '成都市锦江区', FALSE),
('test02', '123456', '武汉市洪山区', FALSE);

-- 插入商品数据
INSERT INTO goods (name, price, picture_url, release_time) VALUES
('iPhone 15 Pro', 7999.00, 'https://example.com/images/iphone15pro.jpg', '2024-01-15 10:00:00'),
('MacBook Pro 14寸', 14999.00, 'https://example.com/images/macbookpro14.jpg', '2024-01-16 10:00:00'),
('AirPods Pro 2', 1899.00, 'https://example.com/images/airpodspro2.jpg', '2024-01-17 10:00:00'),
('iPad Air', 4399.00, 'https://example.com/images/ipadair.jpg', '2024-01-18 10:00:00'),
('Apple Watch Series 9', 2999.00, 'https://example.com/images/applewatch9.jpg', '2024-01-19 10:00:00'),
('华为 Mate 60 Pro', 6999.00, 'https://example.com/images/mate60pro.jpg', '2024-01-20 10:00:00'),
('小米 14 Ultra', 5999.00, 'https://example.com/images/mi14ultra.jpg', '2024-01-21 10:00:00'),
('联想 ThinkPad X1', 8999.00, 'https://example.com/images/thinkpadx1.jpg', '2024-01-22 10:00:00'),
('戴尔 XPS 13', 7999.00, 'https://example.com/images/dellxps13.jpg', '2024-01-23 10:00:00'),
('索尼 WH-1000XM5', 2499.00, 'https://example.com/images/sonyxm5.jpg', '2024-01-24 10:00:00');

-- 插入订单数据
-- 注意：订单的user_id需要对应users表中的id，address应该使用用户的实际地址
INSERT INTO orders (user_id, order_time, total_amount, status, address) VALUES
(2, '2024-01-25 14:30:00', 7999.00, TRUE, '上海市浦东新区'),
(3, '2024-01-26 15:20:00', 14999.00, TRUE, '广州市天河区'),
(2, '2024-01-27 16:10:00', 1899.00, FALSE, '上海市浦东新区'),
(4, '2024-01-28 10:00:00', 4399.00, TRUE, '深圳市南山区'),
(3, '2024-01-29 11:30:00', 2999.00, FALSE, '广州市天河区'),
(5, '2024-01-30 09:15:00', 6999.00, TRUE, '杭州市西湖区'),
(2, '2024-02-01 13:45:00', 12998.00, FALSE, '上海市浦东新区'),
(4, '2024-02-02 14:20:00', 5999.00, TRUE, '深圳市南山区'),
(6, '2024-02-03 10:30:00', 8999.00, TRUE, '成都市锦江区'),
(7, '2024-02-04 11:00:00', 2499.00, FALSE, '武汉市洪山区');

-- 插入订单商品数据
-- 注意：order_id对应orders表的id，good_id对应goods表的id
INSERT INTO orders_goods (order_id, good_id, purchase_quantity) VALUES
-- 订单1：用户2(zhangsan)购买iPhone 15 Pro
(1, 1, 1),
-- 订单2：用户3(lisi)购买MacBook Pro
(2, 2, 1),
-- 订单3：用户2(zhangsan)购买AirPods Pro 2
(3, 3, 1),
-- 订单4：用户4(wangwu)购买iPad Air
(4, 4, 1),
-- 订单5：用户3(lisi)购买Apple Watch
(5, 5, 1),
-- 订单6：用户5(zhaoliu)购买华为 Mate 60 Pro
(6, 6, 1),
-- 订单7：用户2(zhangsan)购买MacBook Pro和AirPods Pro 2（多商品订单）
(7, 2, 1),
(7, 3, 1),
-- 订单8：用户4(wangwu)购买小米 14 Ultra
(8, 7, 1),
-- 订单9：用户6(test01)购买联想 ThinkPad X1
(9, 8, 1),
-- 订单10：用户7(test02)购买索尼 WH-1000XM5
(10, 10, 1);

