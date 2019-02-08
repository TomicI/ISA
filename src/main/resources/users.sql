INSERT INTO roles (id,name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id,name) VALUES (2, 'ROLE_ADMIN_HOTEL');
INSERT INTO roles (id,name) VALUES (3, 'ROLE_ADMIN_AVIO');
INSERT INTO roles (id,name) VALUES (4, 'ROLE_ADMIN_RENT');
INSERT INTO roles (id,name) VALUES (5, 'ROLE_USER_REG');

-- glavniadmin
INSERT INTO USERS (username,enabled, password, first_name, last_name, email,reset) VALUES ('glavni',1, '$2a$10$eukKXJfuGd8.ab3sZgRO9eWfWhwVP3CGTZ3U.WRjb9FXLTgQW7o/W', 'Admin', 'Admin', 'mailad@mail.com',0);
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (1, 3);
INSERT INTO user_role (user_id, role_id) VALUES (1, 4);
INSERT INTO user_role (user_id, role_id) VALUES (1, 5);


-- admincar
INSERT INTO USERS (username,enabled, password, first_name, last_name, email,rent_id,reset) VALUES ('andrew',1, '$2a$10$kfCokmWJWz2RPkDt2jpJ2uT9MQpgzHeJf6ZqKSBY9vYIdfqngUirS', 'Andrew', 'Wisse', 'mailadr@mail.com',1,0);
INSERT INTO user_role (user_id, role_id) VALUES (2, 4);
INSERT INTO user_role (user_id, role_id) VALUES (2, 5);

-- rentpanel
INSERT INTO USERS (username,enabled, password, first_name, last_name, email,rent_id,reset) VALUES ('john',1, '$2a$10$Rw.FhL3xsLQqytz1YoukAeOv5kS8hFxWff3sJa49Zw8.D1FBDAj3S', 'John', 'Green', 'mailadr@mail.com',2,0);
INSERT INTO user_role (user_id, role_id) VALUES (3, 4);
INSERT INTO user_role (user_id, role_id) VALUES (3, 5);

-- useruser
INSERT INTO USERS (username,enabled, password, first_name, last_name, email,reset) VALUES ('user',1, '$2a$10$/zCszDHxHo25nJH0ex0LeOvehOCK.tX1cUUPTPinARX62AOFxGGdi', 'User', 'User', 'mailru@mail.com',0);
INSERT INTO user_role (user_id, role_id) VALUES (4, 5);

select * from users;
select * from user_role;
select * from roles;

select * from verification_token;


