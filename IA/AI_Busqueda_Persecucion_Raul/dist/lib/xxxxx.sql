if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_Derivados_Productos_derivados]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[Derivados] DROP CONSTRAINT FK_Derivados_Productos_derivados
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_Informe_historico_productos_derivados_Productos_derivados]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[Informe_historico_productos_derivados] DROP CONSTRAINT FK_Informe_historico_productos_derivados_Productos_derivados
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_Informeembolsadorderivados_Productos_derivados]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[Informeembolsadorderivados] DROP CONSTRAINT FK_Informeembolsadorderivados_Productos_derivados
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_Articulos_unidades]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[Articulos] DROP CONSTRAINT FK_Articulos_unidades
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_Productos_derivados_unidades]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[Productos_derivados] DROP CONSTRAINT FK_Productos_derivados_unidades
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_Productos_terminados_unidades]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[Productos_terminados] DROP CONSTRAINT FK_Productos_terminados_unidades
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Productos_derivados]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[Productos_derivados]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[unidades]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[unidades]
GO

CREATE TABLE [dbo].[Productos_derivados] (
	[cod_derivado] [int] NOT NULL ,
	[cod_clase] [int] NULL ,
	[cod_externo] [char] (7) COLLATE Modern_Spanish_CI_AS NULL ,
	[nom_prod] [varchar] (50) COLLATE Modern_Spanish_CI_AS NULL ,
	[des_prod] [varchar] (50) COLLATE Modern_Spanish_CI_AS NULL ,
	[cod_uni] [int] NULL ,
	[conversor_bolsas] [float] NULL ,
	[cantidad] [float] NULL ,
	[sto_min] [decimal](18, 0) NULL ,
	[sto_max] [decimal](18, 0) NULL ,
	[pre_costo] [money] NULL ,
	[pre_venta] [money] NULL ,
	[otros] [varchar] (50) COLLATE Modern_Spanish_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[unidades] (
	[cod_uni] [int] NOT NULL ,
	[des_uni] [varchar] (25) COLLATE Modern_Spanish_CI_AS NULL ,
	[abr_uni] [char] (20) COLLATE Modern_Spanish_CI_AS NULL 
) ON [PRIMARY]
GO

