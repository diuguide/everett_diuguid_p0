create table users(
	user_id serial,
	username varchar(30) unique not null,
	password varchar not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	
	constraint pk_users primary key (user_id)
);

select * from users;

create table transactions(
	transaction_id serial primary key,
	user_id int,
	account_type varchar(30) not null,
	amount numeric,
	transaction_date timestamp, 
	
	constraint fk_transactions foreign key (user_id) references users (user_id) 
);

create table accounts(
	user_id int not null,
	account_num serial primary key,
	type varchar not null,
	balance numeric not null,
	
	constraint fk_accounts foreign key (user_id) references users
);



