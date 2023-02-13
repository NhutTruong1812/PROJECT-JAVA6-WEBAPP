USE MASTER
GO

IF EXISTS (SELECT NAME FROM SYS.databases WHERE NAME ='Latipee')
    DROP DATABASE Latipee
GO

CREATE DATABASE Latipee
GO

USE Latipee
GO

--TABLE USER
CREATE TABLE [User] (
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(20) NOT NULL,
    [Password] NVARCHAR(50) NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    PhoneNumber NVARCHAR(10) NOT NULL,
    RegisterDate DATE DEFAULT CONVERT(DATE, GETDATE()),
    Activated BIT DEFAULT 0,
    Avatar NVARCHAR(20) DEFAULT 'user.png'
);
GO

--TABLE ROLE
CREATE TABLE [Role](
    Id NVARCHAR(10) PRIMARY KEY,
    [Name] NVARCHAR(20) NOT NULL
);
GO

--TABLE AUTHORITY
CREATE TABLE Authority(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId BIGINT NOT NULL,
    RoleId  NVARCHAR(10) NOT NULL,
    FOREIGN KEY (UserId) REFERENCES [User](Id) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO


--TABLE CONTACT 
CREATE TABLE [Address](
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId BIGINT NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    PhoneNumber NVARCHAR(10) NOT NULL,
    [Address] NVARCHAR(500) NOT NULL,
    FOREIGN KEY (UserId) REFERENCES [User](Id) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO

--TABLE ACCESSHISTORY
CREATE TABLE AccessHistory(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    Uri NVARCHAR(500) NOT NULL,
    UserId BIGINT NOT NULL,
    AccessDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserId) REFERENCES [User](Id) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO

--TABLE CATEGORY
CREATE TABLE Category(
    Id NVARCHAR(10) PRIMARY KEY,
    [Name] NVARCHAR(50) NOT NULL,
    Deleted BIT DEFAULT 0
);
GO

--TABLE PRODUCT
CREATE TABLE Product(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    [Name] NVARCHAR(100) NOT NULL,
    Banner NVARCHAR(50) NOT NULL DEFAULT 'product.png',
    CreateDate DATETIME DEFAULT GETDATE() NOT NULL,
    Quantity INT DEFAULT 1,
    [Description] NVARCHAR(500) NULL,
    CategoryId NVARCHAR(10) NOT NULL,
    Deleted BIT DEFAULT 0,
    FOREIGN KEY (CategoryId) REFERENCES Category(Id) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO 


--TABLE PRODUCTIMAGE
CREATE TABLE ProductImage(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    [Name] NVARCHAR(20) NOT NULL,
    ProductId BIGINT NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Product(Id) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO

--TABLE PRICEHISTORY
CREATE TABLE PriceHistory(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ProductId BIGINT NOT NULL,
    Price FLOAT NOT NULL,
    ChangeDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (ProductId) REFERENCES Product(id) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO

--TABLE PRODUCT OWNER
CREATE TABLE ProductOwner(
	Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    ProductId BIGINT NOT NULL,
    UserId BIGINT NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Product(Id) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (UserId) REFERENCES [User](Id) ON DELETE NO ACTION ON UPDATE CASCADE
);

--TABLE CART
CREATE TABLE Cart(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId BIGINT NOT NULL,
    FOREIGN KEY (UserId) REFERENCES [User](Id) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO 

--TABLE CARTITEM
CREATE TABLE CartItem(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    CartId BIGINT NOT NULL,
    ProductId BIGINT NOT NULL,
    CreateDate DATETIME DEFAULT GETDATE(),
    Quantity INT DEFAULT 1,
    FOREIGN KEY (CartId) REFERENCES Cart(Id) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product(Id) ON DELETE NO ACTION ON UPDATE CASCADE
);
GO

--TABLE STATUS
CREATE TABLE [Status](
    Id NVARCHAR(10) PRIMARY KEY,
    [Name] NVARCHAR(20) NOT NULL
);
GO

--TABLE ORDER
CREATE TABLE [Order](
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    UserId BIGINT NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    PhoneNumber NVARCHAR(10) NOT NULL,
    [Address] NVARCHAR(500)NOT NULL,
    CreateDate DATETIME DEFAULT GETDATE(),
    StatusId NVARCHAR(10) NOT NULL,
    Note NVARCHAR(500) NULL,
    FOREIGN KEY (UserId) REFERENCES [User](Id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (StatusId) REFERENCES [Status](Id) ON DELETE NO ACTION ON UPDATE CASCADE,
);
GO



--TABLE ORDERDETAIL
CREATE TABLE OrderDetail(
    Id BIGINT IDENTITY(1,1) PRIMARY KEY,
    OrderId BIGINT NOT NULL,
    ProductId BIGINT NOT NULL,
    PriceHistoryId BIGINT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrderId) REFERENCES [Order](Id) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES [Product](Id) ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (PriceHistoryId) REFERENCES [PriceHistory](Id) ON DELETE NO ACTION ON UPDATE NO ACTION,
);

GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([Id], [Username], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar], [Password]) VALUES (1, N'HAOTN', N'Nhựt Hào', N'haotndev@gmail.com', N'0938764882', CAST(N'2022-08-05' AS Date), 1, N'user.png', N'123456')
INSERT [dbo].[User] ([Id], [Username], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar], [Password]) VALUES (2, N'TRUONGNVN', N'Nhựt Trường', N'truongnvn@gmail.com', N'0329848394', CAST(N'2022-08-05' AS Date), 1, N'user.png', N'123456')
INSERT [dbo].[User] ([Id], [Username], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar], [Password]) VALUES (3, N'QUANGHN', N'Nhật Quang', N'quanghn@gmail.com', N'0938395320', CAST(N'2022-08-05' AS Date), 1, N'user.png', N'123456')
INSERT [dbo].[User] ([Id], [Username], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar], [Password]) VALUES (4, N'DUCVH', N'Hữu Đức', N'ducvh@gmail.com', N'0928394837', CAST(N'2022-08-05' AS Date), 1, N'user.png', N'123456')
INSERT [dbo].[User] ([Id], [Username], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar], [Password]) VALUES (5, N'USER1', N'Người dùng 1', N'nguoidung1@gmail.com', N'0985672637', CAST(N'2022-08-08' AS Date), 1, N'user.png', N'111111')
INSERT [dbo].[User] ([Id], [Username], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar], [Password]) VALUES (6, N'USER2', N'Người dùng 2', N'nguoidung2@gmail.com', N'039875027', CAST(N'2022-08-08' AS Date), 1, N'user.png', N'222222')
INSERT [dbo].[User] ([Id], [Username], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar], [Password]) VALUES (7, N'USER3', N'Người dùng 3', N'nguoidung3@gmail.com', N'0983647842', CAST(N'2022-08-08' AS Date), 1, N'user.png', N'333333')
INSERT [dbo].[User] ([Id], [Username], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar], [Password]) VALUES (8, N'USER4', N'Người dùng 4', N'nguoidung4@gmail.com', N'0983092146', CAST(N'2022-08-08' AS Date), 1, N'user.png', N'444444')
SET IDENTITY_INSERT [dbo].[User] OFF

GO
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'ADMIN', N'Quản trị viên')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'ANONYMOUS', N'Người dùng ẩn danh')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'DIRECT', N'Quản lý')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'GUEST', N'Khách')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'SALER', N'Người bán hàng')
GO

GO
SET IDENTITY_INSERT [dbo].[Authority] ON 

INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (1, 1, N'ANONYMOUS')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (2, 1, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (3, 1, N'SALER')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (4, 1, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (5, 1, N'DIRECT')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (6, 2, N'ANONYMOUS')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (7, 2, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (8, 2, N'SALER')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (9, 2, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (10, 2, N'DIRECT')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (11, 3, N'ANONYMOUS')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (12, 3, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (13, 3, N'SALER')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (14, 3, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (15, 3, N'DIRECT')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (16, 4, N'ANONYMOUS')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (17, 4, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (18, 4, N'SALER')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (19, 4, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (20, 4, N'DIRECT')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (21, 5, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (22, 6, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (23, 6, N'SALER')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (24, 7, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (25, 7, N'SALER')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (26, 7, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (27, 8, N'ADMIN')
SET IDENTITY_INSERT [dbo].[Authority] OFF

GO
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'CANCELED', N'Đã hủy')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'CONFIRMED', N'Đã xác nhận')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'DELIVERED', N'Đã giao hàng')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'DELIVERING', N'Đang giao hàng')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'PACKING', N'Đang đóng gói')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'WATING', N'Đang chờ xác nhận')
GO

GO
INSERT [dbo].[Category] ([Id], [Name], [Deleted]) VALUES (N'CLOTHES', N'Quần áo', 0)
INSERT [dbo].[Category] ([Id], [Name], [Deleted]) VALUES (N'SHOES', N'Giày dép', 0)
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (1, N'Giày 1', N'product.png', CAST(N'2022-08-08T08:52:42.350' AS DateTime), 100, N'SP1', N'SHOES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (2, N'Giay2', N'product.png', CAST(N'2022-08-08T08:53:00.530' AS DateTime), 85, N'SP2', N'SHOES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (3, N'Quần áo 1', N'product.png', CAST(N'2022-08-08T08:53:22.833' AS DateTime), 76, N'Quan ao', N'CLOTHES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (4, N'Quần áo 2', N'product.png', CAST(N'2022-08-08T08:53:45.350' AS DateTime), 38, N'Quan ao', N'CLOTHES', 0)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductImage] ON 

INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (1, N'img1', 1)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (2, N'img2', 2)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (3, N'img3', 3)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (4, N'img4', 4)
SET IDENTITY_INSERT [dbo].[ProductImage] OFF
GO
SET IDENTITY_INSERT [dbo].[PriceHistory] ON 

INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (1, 1, 500000, CAST(N'2022-08-08T08:54:12.827' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (2, 2, 620000, CAST(N'2022-08-08T08:54:19.857' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (3, 3, 250000, CAST(N'2022-08-08T08:54:27.607' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (4, 4, 280000, CAST(N'2022-08-08T08:54:34.913' AS DateTime))
SET IDENTITY_INSERT [dbo].[PriceHistory] OFF
GO
INSERT [dbo].[ProductOwner] ([ProductId], [UserId]) VALUES (1, 1)
INSERT [dbo].[ProductOwner] ([ProductId], [UserId]) VALUES (2, 3)
INSERT [dbo].[ProductOwner] ([ProductId], [UserId]) VALUES (3, 1)
INSERT [dbo].[ProductOwner] ([ProductId], [UserId]) VALUES (4, 2)
GO
