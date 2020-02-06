create table opportunity (
	id bigint auto_increment not null,
	prospectus_name varchar(80) not null, 
	description varchar(200) not null,
	value decimal(10,2),
	
	primary key (id)
);