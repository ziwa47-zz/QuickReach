USE [qrdata]
GO

/****** Object:  Table [dbo].[iorders_master]    Script Date: 2017/2/19 上午 11:55:48 ******/
DROP TABLE [dbo].[iorders_master]
GO

/****** Object:  Table [dbo].[iorders_master]    Script Date: 2017/2/19 上午 11:55:48 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[iorders_master](
	[Item] [int] IDENTITY(1,1) NOT NULL,
	[QR_id] [nvarchar](100) NOT NULL,
	[transactionId] [nvarchar](100) NULL,
	[platform] [nvarchar](45) NULL,
	[guest] [nvarchar](45) NULL,
	[orderDate] [datetime] NULL,
	[payDate] [datetime] NULL,
	[logistics] [nvarchar](45) NULL,
	[orderStatus] [nvarchar](45) NULL,
	[shippingDate] [datetime] NULL,
	[shippingFees] [decimal](10, 2) NULL,
	[refundShippingFees] [decimal](10, 2) NULL,
	[otherFees] [decimal](10, 2) NULL,
	[paypalFees] [decimal](10, 2) NULL,
	[insurance] [bit] NULL,
	[insuranceFee] [decimal](10, 2) NULL,
	[insuranceTotal] [decimal](10, 2) NULL,
	[currency] [nvarchar](45) NULL,
	[weight] [decimal](10, 2) NULL,
	[totalWeight] [decimal](10, 2) NULL,
	[FedexService] [nvarchar](45) NULL,
	[FedexPacking] [nvarchar](45) NULL,
	[staffName] [nvarchar](45) NULL,
	[size] [nvarchar](45) NULL,
	[totalPrice] [decimal](10, 2) NULL,
	[trackingCode] [nvarchar](500) NULL,
	[comment] [nvarchar](100) NULL,
	[packageFees] [decimal](10, 2) NULL,
	[paypalTotal] [decimal](10, 2) NULL,
	[paypalNet] [decimal](10, 2) NULL,
	[isCombine] [nvarchar](50) NULL,
	[CombineSku] [nvarchar](100) NULL,
	[paypalPrice] [decimal](10, 2) NULL,
 CONSTRAINT [PK_iorders_master] PRIMARY KEY CLUSTERED 
(
	[Item] ASC,
	[QR_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

