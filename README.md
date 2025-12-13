# 优选商城项目

## 项目简介
这是一个基于Spring Boot + MyBatis的电商系统，实现了用户注册、商品浏览、购物车、订单管理等核心功能。

## 技术栈
- **后端框架**: Spring Boot 4.0.0
- **持久层**: MyBatis Plus
- **数据库**: MySQL
- **前端**: HTML + CSS + JavaScript (前后端分离)
- **构建工具**: Maven

## 项目结构
```
YouXuan-shop/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/youxuanshop/
│   │   │       ├── controller/     # 控制器层
│   │   │       ├── service/        # 业务逻辑层
│   │   │       ├── mapper/         # 数据访问层
│   │   │       └── entity/         # 实体类
│   │   └── resources/
│   │       ├── mapper/             # MyBatis XML映射文件
│   │       ├── static/             # 静态资源（HTML页面）
│   │       ├── application.yml     # 配置文件
│   │       └── schema.sql          # 数据库表结构
│   └── test/
└── pom.xml
```

## 数据库配置
1. 执行 `src/main/resources/schema.sql` 创建数据库表
2. 修改 `application.yml` 中的数据库连接信息

## 功能模块

### 用户功能
- 用户注册
- 用户登录/退出
- 浏览商品
- 加入购物车
- 查看购物车
- 提交订单
- 查看订单列表

### 管理员功能
- 管理员登录（用户名：admin）
- 查看所有订单
- 订单发货
- 上架新商品
- 查看商品列表

## 运行说明
1. 确保MySQL数据库已启动
2. 执行 `schema.sql` 创建表结构
3. 修改 `application.yml` 中的数据库配置
4. 运行 `YouXuanShopApplication.java`
5. 访问 `http://localhost:8086/index.html`

## API接口说明

### 用户相关
- `GET /user/query` - 查询登录状态
- `POST /user/login` - 用户登录
- `POST /user/register` - 用户注册
- `GET /user/logout` - 退出登录
- `GET /user/getAddress` - 获取收货地址

### 商品相关
- `GET /good/list` - 获取商品列表
- `POST /admin/good/add` - 上架新商品（管理员）

### 购物车相关
- `POST /cart/add` - 加入购物车
- `GET /cart/list` - 获取购物车列表

### 订单相关
- `POST /order/add` - 提交订单
- `GET /order/list` - 获取用户订单列表
- `GET /admin/order/list` - 获取所有订单（管理员）
- `POST /admin/order/deliver` - 订单发货（管理员）

## 注意事项
1. 购物车数据存储在Session中，退出登录后会清空
2. 管理员账号需要在数据库中手动设置 `is_admin = true`
3. 商品图片使用URL形式，不需要实际存储图片文件
4. 所有接口返回JSON格式数据（前后端分离）

