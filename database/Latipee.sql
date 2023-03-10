USE [master]
GO

IF EXISTS (SELECT NAME FROM SYS.databases WHERE NAME ='Latipee')
    DROP DATABASE Latipee
GO

/****** Object:  Database [Latipee]    Script Date: 16/08/2022 7:09:15 PM ******/
CREATE DATABASE [Latipee]
 CONTAINMENT = NONE

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
ALTER DATABASE [Latipee] SET AUTO_CLOSE ON 
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
ALTER DATABASE [Latipee] SET RECOVERY SIMPLE 
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
ALTER DATABASE [Latipee] SET QUERY_STORE = OFF
GO
USE [Latipee]
GO
/****** Object:  Table [dbo].[Address]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[Authority]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[Cart]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[CartItem]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[Category]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[Order]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[PriceHistory]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[ProductImage]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[Role]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[Status]    Script Date: 16/08/2022 7:09:15 PM ******/
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
/****** Object:  Table [dbo].[User]    Script Date: 16/08/2022 7:09:15 PM ******/
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
SET IDENTITY_INSERT [dbo].[Address] ON 

INSERT [dbo].[Address] ([Id], [UserId], [Fullname], [PhoneNumber], [Address]) VALUES (1, 1, N'Tiêu nhựt Hào', N'0366555941', N'Cần thơ')
INSERT [dbo].[Address] ([Id], [UserId], [Fullname], [PhoneNumber], [Address]) VALUES (2, 1, N'Tiêu nhựt Hào', N'0366555941', N'Cần thơ')
INSERT [dbo].[Address] ([Id], [UserId], [Fullname], [PhoneNumber], [Address]) VALUES (3, 1, N'Hào phô mai', N'036655511', N'Cần thơ')
INSERT [dbo].[Address] ([Id], [UserId], [Fullname], [PhoneNumber], [Address]) VALUES (4, 2, N'Nguyễn Trường', N'0366156144', N'travelttt')
INSERT [dbo].[Address] ([Id], [UserId], [Fullname], [PhoneNumber], [Address]) VALUES (5, 1, N'Hào tiêu nhựt', N'0329848311', N'Cầntho')
INSERT [dbo].[Address] ([Id], [UserId], [Fullname], [PhoneNumber], [Address]) VALUES (6, 3, N'Quang huỳnh nhật', N'0366155555', N'Kiên giang')
INSERT [dbo].[Address] ([Id], [UserId], [Fullname], [PhoneNumber], [Address]) VALUES (7, 4, N'Đức văn hữu', N'0666888555', N'Sa đéc')
SET IDENTITY_INSERT [dbo].[Address] OFF
GO
SET IDENTITY_INSERT [dbo].[Authority] ON 

INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (1, 1, N'ANONYMOUS')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (2, 1, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (4, 1, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (5, 1, N'DIRECT')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (6, 2, N'ANONYMOUS')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (7, 2, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (9, 2, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (10, 2, N'DIRECT')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (11, 3, N'ANONYMOUS')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (12, 3, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (14, 3, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (15, 3, N'DIRECT')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (16, 4, N'ANONYMOUS')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (17, 4, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (19, 4, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (20, 4, N'DIRECT')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (21, 5, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (22, 6, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (24, 7, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (26, 7, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (27, 8, N'ADMIN')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (29, 9, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (30, 10, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (31, 11, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (32, 12, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (33, 13, N'GUEST')
INSERT [dbo].[Authority] ([Id], [UserId], [RoleId]) VALUES (34, 14, N'GUEST')
SET IDENTITY_INSERT [dbo].[Authority] OFF
GO
SET IDENTITY_INSERT [dbo].[Cart] ON 

INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (1, 1)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (2, 2)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (3, 3)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (4, 4)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (5, 5)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (6, 6)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (7, 7)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (8, 8)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (9, 9)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (10, 10)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (11, 11)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (12, 12)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (13, 13)
INSERT [dbo].[Cart] ([Id], [UserId]) VALUES (14, 14)
SET IDENTITY_INSERT [dbo].[Cart] OFF
GO
SET IDENTITY_INSERT [dbo].[CartItem] ON 

INSERT [dbo].[CartItem] ([Id], [CartId], [ProductId], [CreateDate], [Quantity]) VALUES (11, 6, 8, CAST(N'2022-08-18T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[CartItem] ([Id], [CartId], [ProductId], [CreateDate], [Quantity]) VALUES (12, 7, 7, CAST(N'2022-08-18T00:00:00.000' AS DateTime), 2)
INSERT [dbo].[CartItem] ([Id], [CartId], [ProductId], [CreateDate], [Quantity]) VALUES (13, 8, 9, CAST(N'2022-08-18T00:00:00.000' AS DateTime), 3)
INSERT [dbo].[CartItem] ([Id], [CartId], [ProductId], [CreateDate], [Quantity]) VALUES (14, 9, 1, CAST(N'2022-08-18T00:00:00.000' AS DateTime), 10)
INSERT [dbo].[CartItem] ([Id], [CartId], [ProductId], [CreateDate], [Quantity]) VALUES (15, 10, 1, CAST(N'2022-08-18T00:00:00.000' AS DateTime), 1)
INSERT [dbo].[CartItem] ([Id], [CartId], [ProductId], [CreateDate], [Quantity]) VALUES (16, 11, 2, CAST(N'2022-08-18T00:00:00.000' AS DateTime), 3)
INSERT [dbo].[CartItem] ([Id], [CartId], [ProductId], [CreateDate], [Quantity]) VALUES (19, 2, 1, CAST(N'2022-08-16T19:04:32.623' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[CartItem] OFF
GO
INSERT [dbo].[Category] ([Id], [Name], [Deleted]) VALUES (N'BALO', N'Ba lo', 0)
INSERT [dbo].[Category] ([Id], [Name], [Deleted]) VALUES (N'CLOTHES', N'Quần áo', 0)
INSERT [dbo].[Category] ([Id], [Name], [Deleted]) VALUES (N'HAT', N'Nón', 0)
INSERT [dbo].[Category] ([Id], [Name], [Deleted]) VALUES (N'SHOES', N'Giày dép', 0)
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([Id], [UserId], [Fullname], [PhoneNumber], [Address], [CreateDate], [StatusId], [Note]) VALUES (5, 2, N'Nguyễn Trường', N'0366156144', N'travelttt', CAST(N'2022-08-16T16:39:44.783' AS DateTime), N'WATING', NULL)
INSERT [dbo].[Order] ([Id], [UserId], [Fullname], [PhoneNumber], [Address], [CreateDate], [StatusId], [Note]) VALUES (6, 1, N'Hào tiêu nhựt', N'0329848311', N'Cầntho', CAST(N'2022-08-16T16:41:09.327' AS DateTime), N'WATING', NULL)
INSERT [dbo].[Order] ([Id], [UserId], [Fullname], [PhoneNumber], [Address], [CreateDate], [StatusId], [Note]) VALUES (7, 3, N'Quang huỳnh nhật', N'0366155555', N'Kiên giang', CAST(N'2022-08-16T16:42:00.310' AS DateTime), N'WATING', NULL)
INSERT [dbo].[Order] ([Id], [UserId], [Fullname], [PhoneNumber], [Address], [CreateDate], [StatusId], [Note]) VALUES (8, 4, N'Đức văn hữu', N'0666888555', N'Sa đéc', CAST(N'2022-08-16T16:43:26.943' AS DateTime), N'WATING', NULL)
INSERT [dbo].[Order] ([Id], [UserId], [Fullname], [PhoneNumber], [Address], [CreateDate], [StatusId], [Note]) VALUES (9, 5, N'Người dùng', N'066456222', N'mỹ tho', CAST(N'2022-08-16T16:44:51.457' AS DateTime), N'WATING', NULL)
INSERT [dbo].[Order] ([Id], [UserId], [Fullname], [PhoneNumber], [Address], [CreateDate], [StatusId], [Note]) VALUES (10, 2, N'Nguyễn Trường', N'0366156144', N'travelttt', CAST(N'2022-08-16T18:50:49.527' AS DateTime), N'DELIVERED', NULL)
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (2, 5, 2, 6, 3)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (3, 5, 3, 9, 1)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (4, 6, 3, 9, 1)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (5, 6, 1, 8, 2)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (6, 7, 1, 8, 2)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (7, 7, 3, 9, 1)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (8, 8, 4, 10, 6)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (9, 8, 6, 13, 2)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (10, 9, 4, 10, 2)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (11, 9, 5, 11, 3)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (12, 10, 9, 16, 1)
INSERT [dbo].[OrderDetail] ([Id], [OrderId], [ProductId], [PriceHistoryId], [Quantity]) VALUES (13, 10, 1, 8, 1)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[PriceHistory] ON 

INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (1, 1, 500000, CAST(N'2022-08-08T08:54:12.827' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (2, 2, 620000, CAST(N'2022-08-08T08:54:19.857' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (3, 3, 250000, CAST(N'2022-08-08T08:54:27.607' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (4, 4, 280000, CAST(N'2022-08-08T08:54:34.913' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (5, 1, 500000, CAST(N'2022-08-16T14:48:33.947' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (6, 2, 620000, CAST(N'2022-08-16T14:48:44.023' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (7, 1, 500000, CAST(N'2022-08-16T15:24:01.843' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (8, 1, 500000, CAST(N'2022-08-16T15:24:37.087' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (9, 3, 250000, CAST(N'2022-08-16T15:25:05.887' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (10, 4, 280000, CAST(N'2022-08-16T15:26:24.123' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (11, 5, 200000, CAST(N'2022-08-16T16:08:21.433' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (12, 6, 123000, CAST(N'2022-08-16T16:10:28.040' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (13, 6, 123000, CAST(N'2022-08-16T16:10:40.980' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (14, 7, 123000, CAST(N'2022-08-16T16:11:10.743' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (15, 8, 560000, CAST(N'2022-08-16T16:11:56.650' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (16, 9, 250000, CAST(N'2022-08-16T16:12:37.203' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (17, 10, 123561, CAST(N'2022-08-16T16:13:24.500' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (18, 11, 520000, CAST(N'2022-08-16T16:14:07.007' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (19, 7, 123000, CAST(N'2022-08-16T16:15:14.963' AS DateTime))
INSERT [dbo].[PriceHistory] ([Id], [ProductId], [Price], [ChangeDate]) VALUES (20, 8, 560000, CAST(N'2022-08-16T16:15:28.007' AS DateTime))
SET IDENTITY_INSERT [dbo].[PriceHistory] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (1, N'Áo Khoát', N'c558613.jpg', CAST(N'2022-08-16T15:24:35.563' AS DateTime), 99, NULL, N'CLOTHES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (2, N'Giay2', N'f1385b83.jpg', CAST(N'2022-08-16T14:48:43.017' AS DateTime), 85, NULL, N'SHOES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (3, N'áo thun', N'ced9844.jpg', CAST(N'2022-08-16T15:25:04.273' AS DateTime), 76, NULL, N'CLOTHES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (4, N'Áo Ngoài', N'a62ed4cb.jpg', CAST(N'2022-08-16T15:26:22.930' AS DateTime), 38, NULL, N'CLOTHES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (5, N'Nón lưỡi trau', N'f0605eda.jpg', CAST(N'2022-08-16T16:08:21.407' AS DateTime), 100, N'không có', N'HAT', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (6, N'Quần âu', N'c9d754e5.jpg', CAST(N'2022-08-16T16:10:39.790' AS DateTime), 100, NULL, N'CLOTHES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (7, N'dép sandrand', N'486122fe.jpg', CAST(N'2022-08-16T16:15:13.590' AS DateTime), 100, NULL, N'SHOES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (8, N'Balo Nam', N'622a35da.jpg', CAST(N'2022-08-16T16:15:26.757' AS DateTime), 100, NULL, N'BALO', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (9, N'Áo vãi', N'be447908.jpg', CAST(N'2022-08-16T16:12:35.240' AS DateTime), 100000, N'không có', N'CLOTHES', 0)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (10, N'Quần jean âu mỹ', N'67facfbd.jpg', CAST(N'2022-08-16T16:13:23.457' AS DateTime), 100, N'không có', N'CLOTHES', NULL)
INSERT [dbo].[Product] ([Id], [Name], [Banner], [CreateDate], [Quantity], [Description], [CategoryId], [Deleted]) VALUES (11, N'Balo nữ', N'c3de28f7.jpg', CAST(N'2022-08-16T16:14:05.667' AS DateTime), 1000, N'không có', N'CLOTHES', 0)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductImage] ON 

INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (6, N'img2', 2)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (9, N'601b05c0.jpg', 1)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (10, N'343830bf.jpg', 1)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (11, N'e9e0ad86.jpg', 3)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (12, N'48fd0b1a.jpg', 3)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (13, N'90a336e7.jpg', 4)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (14, N'9e8259e3.jpg', 4)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (15, N'13f3cb45.jpg', 5)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (16, N'42381063.jpg', 5)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (19, N'8b84242.jpg', 6)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (20, N'dc5f46c7.jpg', 6)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (23, N'5d2919a5.jpg', 9)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (24, N'a420463c.jpg', 9)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (25, N'6a996d99.jpg', 9)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (26, N'8a530f78.jpg', 10)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (27, N'116d417.jpg', 10)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (28, N'5b86eb75.jpg', 10)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (29, N'45aedf84.jpg', 11)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (30, N'c20a33d7.jpg', 11)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (31, N'17543b7a.jpg', 7)
INSERT [dbo].[ProductImage] ([Id], [Name], [ProductId]) VALUES (32, N'5af45bfd.jpg', 8)
SET IDENTITY_INSERT [dbo].[ProductImage] OFF
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

INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (1, N'HAOTN', N'123456', N'Nhựt Hào', N'haotndev@gmail.com', N'0938764882', CAST(N'2022-08-05' AS Date), 1, N'f41ed7b9.jpg')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (2, N'TRUONGNVN', N'123456', N'Nhựt Trường', N'truongnvn@gmail.com', N'0329848394', CAST(N'2022-08-05' AS Date), 1, N'e337495c.jpg')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (3, N'QUANGHN', N'123456', N'Nhật Quang', N'quanghn@gmail.com', N'0938395320', CAST(N'2022-08-05' AS Date), 1, N'dd863eec.jpg')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (4, N'DUCVH', N'123456', N'Hữu Đức', N'ducvh@gmail.com', N'0928394837', CAST(N'2022-08-05' AS Date), 1, N'fd5e82e6.jpg')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (5, N'USER1', N'111111', N'Người dùng 1', N'nguoidung1@gmail.com', N'0985672637', CAST(N'2022-08-08' AS Date), 1, N'e3391685.jpg')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (6, N'USER2', N'222222', N'Người dùng 2', N'nguoidung2@gmail.com', N'039875027', CAST(N'2022-08-08' AS Date), 1, N'ecb0b768.jpg')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (7, N'USER3', N'333333', N'Người dùng 3', N'nguoidung3@gmail.com', N'0983647842', CAST(N'2022-08-08' AS Date), 1, N'5f684371.jpg')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (8, N'USER4', N'444444', N'Người dùng 4', N'nguoidung4@gmail.com', N'0983092146', CAST(N'2022-08-08' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (9, N'Admin', N'555555', N'Quản trị 1', N'admin1@gmail.com', N'0333156111', CAST(N'2022-08-09' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (10, N'ANNV', N'123456', N'Nguyễn Văn An', N'annv@gmail.com', N'0366156222', CAST(N'2022-08-10' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (11, N'THANHNV', N'123456', N'Nguyễn Vũ Thanh', N'thanhnv@gmail.com', N'0366555111', CAST(N'2022-08-10' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (12, N'TRANGLK', N'123456', N'Trang Lê Khánh', N'tranglk@gmail.com', N'0336155125', CAST(N'2022-08-11' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (13, N'KHANHNS', N'123456', N'Nguyễn Sĩ Khanh', N'khanhns@gmail.com', N'055789444', CAST(N'2022-07-08' AS Date), 1, N'user.png')
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [PhoneNumber], [RegisterDate], [Activated], [Avatar]) VALUES (14, N'DUYTL', N'123456', N'Trịnh Lê Duy', N'duytl@gmail.com', N'0366551451', CAST(N'2022-04-11' AS Date), 1, N'user.png')
SET IDENTITY_INSERT [dbo].[User] OFF
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
USE [master]
GO
ALTER DATABASE [Latipee] SET  READ_WRITE 
GO
