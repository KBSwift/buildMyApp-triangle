# buildMyApp-triangle
This is a Customer Relationship management Application

Below are the queries to make the necessary tables:

create table contracts(
contract_id int,
job_done boolean,
site_adress varchar(255),
employee_name varchar(255)
);
create table customer(
customer_id int,
first_name varchar(255),
last_name varchar(255),
address varchar(255),
phone_numer varchar(255)
);
create table employee(
availability boolean,
site_adress varchar(255),
employee_id int,
first_name varchar(255),
last_name varchar(255),
phone_numer varchar(255),
email varchar(255)
);