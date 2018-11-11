
-- create database simpleapi;
-- create table users(
-- 	id int,
-- 	username varchar(255),
-- 	password varchar(255),
-- 	first_name varchar(255),
-- 	last_name varchar(255),
-- 	email varchar(255)
-- );

-- create table chat (
-- 	id int ,
-- 	user_id int,
-- 	message varchar(255),
-- 	posted_date date
-- );

-- create table chat_message (
-- 	id int,
-- 	user_id int,
-- 	chat_id int,
-- 	message varchar(255),
-- 	posted_date date
-- );

insert into simpleapi.users(id, username, password, first_name, last_name, email) values(1,'fnugraha', '1234', 'Fajar', 'Nugraha', 'fnugraha@gmail.edu');
insert into simpleapi.users(id, username, password, first_name, last_name, email) values(2,'caesar', '1234', 'caesar', 'dor', 'casesar@mum.edu');
insert into simpleapi.users(id, username, password, first_name, last_name, email) values(3,'shopper', '1234', 'shopper', 'shopper', 'shopper@hf.id');
insert into simpleapi.users(id, username, password, first_name, last_name, email) values(4,'driver', '1234', 'gusti', 'driver', 'driver@hf.id');
insert into simpleapi.users(id, username, password, first_name, last_name, email) values(5,'ranger', '1234', 'ranger', 'id', 'ranger@hf.id');
insert into simpleapi.users(id, username, password, first_name, last_name, email) values(10,'admin', '1234', 'admin', 'hf', 'admin@mum.edu');


insert into simpleapi.chat(id, user_id, message, posted_date) values(1, 3, 'First Posted by user shopper id 1', '2017-03-04 22:44:30');
insert into simpleapi.chat(id, user_id, message, posted_date) values(2, 4, 'First Posted by user driver id 2', '2017-06-07 20:44:30');


insert into simpleapi.chat_message(id, user_id, chat_id, message, posted_date) values(1, 3, 1, 'reply to chat id 1', '2017-06-04 22:45:00');
insert into simpleapi.chat_message(id, user_id, chat_id, message, posted_date) values(2, 4, 2, 'reply to chat id 2 user 2', '2017-07-07 10:45:00');
insert into simpleapi.chat_message(id, user_id, chat_id, message, posted_date) values(3, 3, 2, 'reply to chat id 1 user 1', '2017-06-06 10:45:00');

