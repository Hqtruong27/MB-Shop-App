create  database Mobile_Shop
go
use Mobile_Shop
go

create table Users (
	id int identity primary key,
	name nvarchar(100) not null,
	email nvarchar(100) not null,
	phone nvarchar(20) not null,
	password nvarchar(100) not null,
	group_name bit default(0),
	address ntext not null ,
	gender bit,
	birthday datetime
)
go
insert into Users values(N'Hqtruong27','Hqtruong27@gmail.com',0963712001,'00000000',1,'QN',1,'07-22-1998')
insert into Users values(N'Truong','Hqtruong27@gmail.com',0963712001,'00000000',0,'QN',1,'07-22-1998')
-- into Users(name,email,phone,password,address,gender,birthday) values(N'1 Truong','Hqtruong27@gmail.com',0963712001,'1','QN',1,'07-22-1998')
go

create table Category (
	id int identity primary key,
	name nvarchar(100) not null,	
)
go
insert into Category values(N'Iphone')
insert into Category values(N'Sam Sung')
insert into Category values(N'Huawei')
insert into Category values(N'Xiaomi')
insert into Category values(N'Bphone')
go
--
create table Product  (
	id int identity primary key,
	name nvarchar(100) not null,
	price float not null,
	saleprice float null,
	images nvarchar(200),
	content ntext,
	category_id int foreign key references Category(id),
	guarantee nvarchar(100),
	status bit
)
go
/*insert into Product values(N'Iphone 5',2000000,100000,'src/images/Iphone5.jpg',N'đây là Iphone 5',1,N'2 Năm',1)
insert into Product values(N'Iphone 6',4000000,200000,'src/images/iphone-6.jpg',N'đây là Iphone 6',1,N'2 Năm',1)
insert into Product values(N'Iphone 7',7999000,150000,'src/images/iphone-7.jpg',N'đây là Iphone 7',1,N'1 Năm',1)
insert into Product values(N'Sam Sung Note 9',22000000,7000000,'src/images/samsung-galaxy-note-9.png',N'đây là Sam Sung Note 9',2,N'2 Năm',1)
insert into Product values(N'Sam Sung s9',12299000,1000000,'src/images/samsung_galaxy_s9.png',N'đây là Sam Sung s9',2,N'2 Năm',1)
insert into Product values(N'Huawei Nova 3i',6400000,500000,'src/images/nova3i-XANH-3.jpg',N'đây là Huawei Nova 3i',3,N'2 Năm',1)
insert into Product values(N'Huawei Mate 20Pro',21990000 ,500000,'src/images/huawei-mate-20-pro.png',N'đây là Huawei Mate 20Pro',3,N'2 Năm',1)
insert into Product values(N'Xiaomi Mi8',8000000,0,'src/images/xiaomi-mi-8.png',N'đây là Xiaomi Mi8',4,N'1,5 Năm',1)
insert into Product values(N'Xiaomi Mi8',6490000,0,'src/images/bphone-3-black.png',N'đây là Xiaomi Mi8',5,N'1,5 Năm',1)*/
go
create table [Order] (
	id int identity primary key,
	users_id int foreign key references Users(id),
	order_date datetime,
	fullname nvarchar(100) not null,
	email nvarchar(100) not null,
	phone nvarchar(20) not null,
	address ntext,
	status tinyint default(0)
)
go
select * from Users 
go
--insert into [Order](users_id,order_date,fullname,email,phone,address) values(,N'1/1/1999',N'v',N'haha',0,N'a')
go
create table Order_detail (
	id int identity primary key,
	order_id int foreign key references [Order](id),
	productName nvarchar(100) ,
	quantity int,
	price float,
	totalprice float,
	
)
go
--alter table Order_detail alter column  [Status] bit default0
go
create table News (
	news_id int identity primary key,
	name nvarchar(200),
	createby int foreign key references Users(id),
	content ntext,
	createdate datetime,
	status bit
)
go

create table Contact (
	contact_id int identity primary key,
	name nvarchar(100) not null,
	email nvarchar(100) not null,
	phone nvarchar(20) not null,
	comment ntext,
	created datetime
)
go
--
create table feedback (
Id int  identity primary key,
rate bit not null,
comment ntext not null
)

--

--proc

Create proc LoginUser
as
select * from Users 
go

--select category

Create proc pCategory
as
select * from Category
go

---product

create proc pProduct 
	@name nvarchar(100),
	@price float,
	@saleprice float,
	@images nvarchar(200),
	@content ntext,
	@category_id int,
	@guarantee nvarchar(100),
	@status bit
as
insert into Product values(@name,@price,@saleprice,@images,@content,@category_id,@guarantee,@status)
go


--select sản phẩm theo danh mục

create proc _selectProduct
as
select p.*,c.name as 'categoryName' from Product p inner join Category c on p.category_id = c.id 

go 

----
--Danh muc theo san pham-
create   proc _ProductforCategory
@id int
as
select * from Product where category_id=@id
Go
----
----order 
create proc getOrderId
@users_id int
as
select * from [Order] where users_id = @users_id
go
----order 
create proc getOrder
as
select * from [Order]  
go
--Orderdetail by Order_id
create proc orderdetail
as
select * from Order_detail
go
create proc orderdetailbyOrderId
@order_id int
as
select * from Order_detail where order_id = @order_id
go

create proc GetallProductbyid
@id int
as
select p.*,c.name as 'categoryName' from Product p inner join Category c on p.category_id = c.id  where p.id=@id
go
--update proc
create proc UpdateProduct
	@id int,
	@name nvarchar(100),
	@price float,
	@saleprice float,
	@images nvarchar(200),
	@content ntext,
	@category_id int,
	@guarantee nvarchar(100),
	@status bit
	as
	update Product
	set name=@name,price=@price,saleprice=@saleprice,images=@images,content=@content,category_id=@category_id, guarantee=@guarantee, status=@status where id=@id


go
create proc Sp_User_AddAdmin
	@name nvarchar(100) ,
	@email nvarchar(100) ,
	@phone nvarchar(20) ,
	@password nvarchar(100),
	@address ntext,
	@gender bit,
	@birthday  datetime
as
	insert into Users(name,email,phone,password,group_name,address,gender,birthday) values
	(@name,@email,@phone,@password,'admin',@address,@gender,@birthday)

go




Create proc SP_User_phanquyen
@id int
as
update Users
set group_name = 'customer'
where id = @id

go