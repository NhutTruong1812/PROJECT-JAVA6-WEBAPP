USE [master]
GO
/****** Object:  Database [Latipee]    Script Date: 8/8/2022 9:14:51 AM ******/
CREATE DATABASE [Latipee]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Latipee', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Latipee.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Latipee_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Latipee_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Latipee] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Latipee].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Latipee] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Latipee] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Latipee] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Latipee] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Latipee] SET ARITHABORT OFF 
GO
ALTER DATABASE [Latipee] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Latipee] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Latipee] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Latipee] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Latipee] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Latipee] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Latipee] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Latipee] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Latipee] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Latipee] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Latipee] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Latipee] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Latipee] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Latipee] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Latipee] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Latipee] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Latipee] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Latipee] SET RECOVERY FULL 
GO
ALTER DATABASE [Latipee] SET  MULTI_USER 
GO
ALTER DATABASE [Latipee] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Latipee] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Latipee] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Latipee] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Latipee] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Latipee] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Latipee', N'ON'
GO
ALTER DATABASE [Latipee] SET QUERY_STORE = OFF
GO
USE [Latipee]
GO
/****** Object:  Table [dbo].[AccessHistory]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccessHistory](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Uri] [nvarchar](500) NOT NULL,
	[UserId] [bigint] NOT NULL,
	[AccessDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Address]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[UserId] [bigint] NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[PhoneNumber] [nvarchar](10) NOT NULL,
	[Address] [nvarchar](500) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Authority]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authority](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[UserId] [bigint] NOT NULL,
	[RoleId] [nvarchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cart](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[UserId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CartItem]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CartItem](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[CartId] [bigint] NOT NULL,
	[ProductId] [bigint] NOT NULL,
	[CreateDate] [datetime] NULL,
	[Quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Id] [nvarchar](10) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[UserId] [bigint] NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[PhoneNumber] [nvarchar](10) NOT NULL,
	[Address] [nvarchar](500) NOT NULL,
	[CreateDate] [datetime] NULL,
	[StatusId] [nvarchar](10) NOT NULL,
	[Note] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[OrderId] [bigint] NOT NULL,
	[ProductId] [bigint] NOT NULL,
	[PriceHistoryId] [bigint] NOT NULL,
	[Quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PriceHistory]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PriceHistory](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ProductId] [bigint] NOT NULL,
	[Price] [float] NOT NULL,
	[ChangeDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Banner] [nvarchar](50) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[Quantity] [int] NULL,
	[Description] [nvarchar](500) NULL,
	[CategoryId] [nvarchar](10) NOT NULL,
	[Deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductImage]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductImage](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](20) NOT NULL,
	[ProductId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductOwner]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductOwner](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ProductId] [bigint] NOT NULL,
	[UserId] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[Id] [nvarchar](10) NOT NULL,
	[Name] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[Id] [nvarchar](10) NOT NULL,
	[Name] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 8/8/2022 9:14:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](20) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[PhoneNumber] [nvarchar](10) NOT NULL,
	[RegisterDate] [date] NULL,
	[Activated] [bit] NULL,
	[Avatar] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
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
INSERT [dbo].[Category] ([Id], [Name], [Deleted]) VALUES (N'CLOTHES', N'Quần áo', 0)
INSERT [dbo].[Category] ([Id], [Name], [Deleted]) VALUES (N'SHOES', N'Giày dép', 0)
GO
SET IDENTITY_INSERT [dbo].[PriceHistory] ON 

INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (1, 1, 500000, CAST(N'2022-08-08T08:54:12.827' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (2, 2, 620000, CAST(N'2022-08-08T08:54:19.857' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (3, 3, 250000, CAST(N'2022-08-08T08:54:27.607' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (4, 4, 280000, CAST(N'2022-08-08T08:54:34.913' AS DateTime))
SET IDENTITY_INSERT [dbo].[PriceHistory] OFF
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
SET IDENTITY_INSERT [dbo].[ProductOwner] ON 

INSERT [dbo].[ProductOwner] ([Id], [ProductId], [UserId]) VALUES (1, 1, 1)
INSERT [dbo].[ProductOwner] ([Id], [ProductId], [UserId]) VALUES (2, 2, 3)
INSERT [dbo].[ProductOwner] ([Id], [ProductId], [UserId]) VALUES (3, 3, 1)
INSERT [dbo].[ProductOwner] ([Id], [ProductId], [UserId]) VALUES (4, 4, 2)
SET IDENTITY_INSERT [dbo].[ProductOwner] OFF
GO
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'ADMIN', N'Quản trị viên')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'ANONYMOUS', N'Người dùng ẩn danh')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'DIRECT', N'Quản lý')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'GUEST', N'Khách')
INSERT [dbo].[Role] ([Id], [Name]) VALUES (N'SALER', N'Người bán hàng')
GO
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'CANCELED', N'Đã hủy')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'CONFIRMED', N'Đã xác nhận')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'DELIVERED', N'Đã giao hàng')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'DELIVERING', N'Đang giao hàng')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'PACKING', N'Đang đóng gói')
INSERT [dbo].[Status] ([Id], [Name]) VALUES (N'WATING', N'Đang chờ xác nhận')
GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (1, N'HAOTN', N'123456', N'Nhựt Hào', N'haotndev@gmail.com', N'0938764882', CAST(N'2022-08-05' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (2, N'TRUONGNVN', N'123456', N'Nhựt Trường', N'truongnvn@gmail.com', N'0329848394', CAST(N'2022-08-05' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (3, N'QUANGHN', N'123456', N'Nhật Quang', N'quanghn@gmail.com', N'0938395320', CAST(N'2022-08-05' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (4, N'DUCVH', N'123456', N'Hữu Đức', N'ducvh@gmail.com', N'0928394837', CAST(N'2022-08-05' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (5, N'USER1', N'111111', N'Người dùng 1', N'nguoidung1@gmail.com', N'0985672637', CAST(N'2022-08-08' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (6, N'USER2', N'222222', N'Người dùng 2', N'nguoidung2@gmail.com', N'039875027', CAST(N'2022-08-08' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (7, N'USER3', N'333333', N'Người dùng 3', N'nguoidung3@gmail.com', N'0983647842', CAST(N'2022-08-08' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (8, N'USER4', N'444444', N'Người dùng 4', N'nguoidung4@gmail.com', N'0983092146', CAST(N'2022-08-08' AS Date), 1, N'user.png')
SET IDENTITY_INSERT [dbo].[User] OFF
GO
ALTER TABLE [dbo].[AccessHistory] ADD  DEFAULT (getdate()) FOR [AccessDate]
GO
ALTER TABLE [dbo].[CartItem] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[CartItem] ADD  DEFAULT ((1)) FOR [Quantity]
GO
ALTER TABLE [dbo].[Category] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[Order] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[PriceHistory] ADD  DEFAULT (getdate()) FOR [ChangeDate]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ('product.png') FOR [Banner]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT (getdate()) FOR [CreateDate]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((1)) FOR [Quantity]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0)) FOR [Deleted]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT (CONVERT([date],getdate())) FOR [RegisterDate]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT ((0)) FOR [Activated]
GO
ALTER TABLE [dbo].[User] ADD  DEFAULT ('user.png') FOR [Avatar]
GO
ALTER TABLE [dbo].[AccessHistory]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Address]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Authority]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Cart]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CartItem]  WITH CHECK ADD FOREIGN KEY([CartId])
REFERENCES [dbo].[Cart] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CartItem]  WITH CHECK ADD FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([StatusId])
REFERENCES [dbo].[Status] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([OrderId])
REFERENCES [dbo].[Order] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([PriceHistoryId])
REFERENCES [dbo].[PriceHistory] ([Id])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[PriceHistory]  WITH CHECK ADD FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductImage]  WITH CHECK ADD FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductOwner]  WITH CHECK ADD FOREIGN KEY([ProductId])
REFERENCES [dbo].[Product] ([Id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductOwner]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
ON UPDATE CASCADE
GO
USE [master]
GO
ALTER DATABASE [Latipee] SET  READ_WRITE 
GO
