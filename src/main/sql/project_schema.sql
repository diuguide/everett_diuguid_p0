create table users(
	user_id serial,
	username varchar(30) unique not null,
	password varchar not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	
	constraint pk_users primary key (user_id)
);

select * from users;

delete from users where user_id=8;
select * from accounts;
select * from transactions;

select * from transactions where user_id=4;

drop table transactions; 

create table transactions(
	transaction_id serial primary key,
	user_id int,
	trans_type varchar(30) not null,
	amount numeric,
	transaction_date varchar,
	
	constraint fk_transactions foreign key (user_id) references users (user_id) 
);

create table accounts(
	user_id int not null,
	account_num serial primary key,
	type varchar not null,
	balance numeric not null,
	
	constraint fk_accounts foreign key (user_id) references users
);



