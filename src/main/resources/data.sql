insert into book(ISBN, TITLE, STATUS, AUTHOR) values ('978-979-2763-37-9','ROBIN HOOD','NOT_SHELVED','Paul Creswick');
insert into book(ISBN, TITLE, STATUS, AUTHOR) values ('978-979-91-0563-9','Norwegian Wood','NOT_SHELVED','Haruki Murakami');
insert into shelf(MAX_CAPACITY,CURRENT_CAPACITY) values(10,0);

insert into user (ID, USERNAME, PASSWORD)
values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');

insert into user (ID, USERNAME, PASSWORD)
values (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu'); 
 
insert into role (ID, NAME)
values (1, 'ROLE_ADMIN');
 
insert into role (ID, NAME)
values (2, 'ROLE_USER');
 
insert into users_roles (USER_ID, ROLE_ID)
values (1, 1);
 
insert into users_roles (USER_ID, ROLE_ID)
values (1, 2);
 
insert into users_roles (USER_ID, ROLE_ID)
values (2, 2);