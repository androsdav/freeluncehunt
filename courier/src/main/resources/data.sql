-- insert three users (password = 'user')
INSERT INTO users(login, password, name, surname, money) VALUES (
'user1',
'$2a$10$0DotfQA1EPIkuL15D8EH4eFVFdNnYsU7M85FTRQLv0NILFYmvC8Oa',
'Andrey',
'Ivanov',
1000
);
INSERT INTO users(login, password) VALUES ('user2', '$2a$10$0DotfQA1EPIkuL15D8EH4eFVFdNnYsU7M85FTRQLv0NILFYmvC8Oa');
INSERT INTO users(login, password) VALUES ('user3', '$2a$10$0DotfQA1EPIkuL15D8EH4eFVFdNnYsU7M85FTRQLv0NILFYmvC8Oa');

-- insert two roles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- insert role to all users
INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (3, 1);

-- insert 4 customer
INSERT INTO customers(latitude, longitude) VALUES (50.4839843, 30.5350063);
INSERT INTO customers(latitude, longitude) VALUES (50.4716034, 30.4831969);
INSERT INTO customers(latitude, longitude) VALUES (50.4085094, 30.5713501);
INSERT INTO customers(latitude, longitude) VALUES (50.4258262, 30.5716457);