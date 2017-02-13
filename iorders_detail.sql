USE [qrdata]
GO

/****** Object:  Table [dbo].[iorders_detail]    Script Date: 2017/2/11 ¤U¤È 03:38:44 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[iorders_detail](
	[Item] [int] IDENTITY(1,1) NOT NULL,
	[QR_id] [nvarchar](100) NOT NULL,
	[tansactionId] [nvarchar](100) NULL,
	[SKU] [nvarchar](100) NULL,
	[productName] [nvarchar](1000) NULL,
	[invoiceName] [nvarchar](1000) NULL,
	[price] [decimal](10, 2) NULL,
	[invoicePrice] [decimal](10, 2) NULL,
	[qty] [int] NULL,
	[warehouse] [nvarchar](100) NULL,
	[comment] [nvarchar](3000) NULL,
	[owner] [nvarchar](45) NULL,
 CONSTRAINT [PK_iorders_detail] PRIMARY KEY CLUSTERED 
(
	[Item] ASC,
	[QR_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


