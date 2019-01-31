INSERT INTO roles (id,name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id,name) VALUES (2, 'ROLE_ADMIN_HOTEL');
INSERT INTO roles (id,name) VALUES (3, 'ROLE_ADMIN_AVIO');
INSERT INTO roles (id,name) VALUES (4, 'ROLE_ADMIN_RENT');
INSERT INTO roles (id,name) VALUES (5, 'ROLE_USER_REG');

-- glavniadmin
INSERT INTO USERS (username,enabled, password, first_name, last_name, email) VALUES ('glavni',1, '$2a$10$eukKXJfuGd8.ab3sZgRO9eWfWhwVP3CGTZ3U.WRjb9FXLTgQW7o/W', 'Admin', 'Admin', 'mailad@mail.com');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

-- admincar
INSERT INTO USERS (username,enabled, password, first_name, last_name, email) VALUES ('rent',1, '$2a$10$kfCokmWJWz2RPkDt2jpJ2uT9MQpgzHeJf6ZqKSBY9vYIdfqngUirS', 'Rent', 'Rent', 'mailadr@mail.com');
INSERT INTO user_role (user_id, role_id) VALUES (2, 4);
INSERT INTO user_role (user_id, role_id) VALUES (2, 5);

-- usercar
INSERT INTO USERS (username,enabled, password, first_name, last_name, email) VALUES ('user',1, '$2a$10$JTY1XHgD0aa7GnZD8xy0N.xvS/Y/lKkITmwTrLwobBtIbilsj3TQO', 'User', 'User', 'mailru@mail.com');
INSERT INTO user_role (user_id, role_id) VALUES (3, 5);

select * from users;
select * from user_role;
select * from roles;

select * from verification_token;


