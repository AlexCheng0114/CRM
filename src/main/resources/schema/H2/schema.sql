

CREATE TABLE company (
	id 		IDENTITY  not null primary key,
	name 	char(20),
	address char(50)
);

CREATE TABLE client(
	id IDENTITY not null primary key,
	company_id 	BIGINT, 
	name 		char(20),
	email		char(50),
	phone		char(20)
);
 
