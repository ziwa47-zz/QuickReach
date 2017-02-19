USE [qrdata]
GO

/****** Object:  Table [dbo].[guest]    Script Date: 2017/2/19 上午 11:55:28 ******/
DROP TABLE [dbo].[guest]
GO

/****** Object:  Table [dbo].[guest]    Script Date: 2017/2/19 上午 11:55:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[guest](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[guestId] [nvarchar](45) NULL,
	[name] [nvarchar](100) NULL,
	[company] [nvarchar](100) NULL,
	[platformAccount] [nvarchar](100) NULL,
	[email] [nvarchar](100) NULL,
	[country] [nvarchar](45) NULL,
	[tel] [nvarchar](45) NULL,
	[address] [nvarchar](200) NULL,
	[comment] [nvarchar](max) NULL,
	[phone] [nvarchar](50) NULL,
	[postcode] [nvarchar](50) NULL,
	[birthday] [date] NULL,
	[gender] [varchar](1) NULL,
 CONSTRAINT [PK_guest] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

