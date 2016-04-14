1.导包
	BeanUtils
	logging
	jstl
	mysql_connector
	
2.建包
	cn.w28l30.domain
	cn.w28l30.dao
	cn.w28l30.dao.impl
	cn.w28l30.factory
	cn.w28l30.utils
	cn.w28l30.service
	cn.w28l30.service.impl
	cn.w28l30.controller
	cn.w28l30.exception
	cn.w28l30.UI
	junit.test
	
	WEB-INF/jsp

3.建表
	Create database day14_customer character set utf8 collate utf8_general_ci;
	use database day14_customer;
	create table customer
	(
		id varchar(40) primary key,
		name varchar(40) not null,
		gender varchar(4) not null,
		birthday date,
		cellphone varchar(20),
		email varchar(40),
		preference varchar(255),
		type varchar(100) not null,
		description varchar(255) not null
	);