create table users(
user_id Number primary key,
name varchar2(255),
email varchar2(255),
password varchar2(255),
phone_number number );


create sequence user_id_seq
start with 1
increment by 1
NOCACHE;