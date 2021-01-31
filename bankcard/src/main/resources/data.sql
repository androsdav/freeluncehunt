-- adds type transport to table type_transport
INSERT INTO type_transport(name) VALUES('car');
INSERT INTO type_transport (name) VALUES ('motorcycle');

-- adds transport to table transport with type_transport = car;
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'BMV',
  '30000',
  CURRENT_DATE,
  '1'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Mazda',
  '15000',
  CURRENT_DATE,
  '1'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Maserati',
  '50000',
  CURRENT_DATE,
  '1'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Audi',
  '25000',
  CURRENT_DATE,
  '1'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Rover',
  '10000',
  CURRENT_DATE,
  '1'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Porsche',
  '40000',
  CURRENT_DATE,
  '1'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Volvo',
  '23000',
  CURRENT_DATE,
  '1'
);

-- adds transport to table transport with type_transport = motorcycle;
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Suzuki',
  '900',
  CURRENT_DATE,
  '1'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Honda',
  '850',
  CURRENT_DATE,
  '2'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Yamaha',
  '1200',
  CURRENT_DATE,
  '2'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Kawasaki',
  '600',
  CURRENT_DATE,
  '2'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'KTM',
  '8000',
  CURRENT_DATE,
  '2'
);

INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Harley Davidson',
  '10000',
  CURRENT_DATE,
  '2'
);
INSERT INTO transport (name, price, date_create, type_transport_id) VALUES (
  'Ducati',
  '20000',
  CURRENT_DATE,
  '2'
);
-- insert three users (password = 'user')
INSERT INTO users(login, password) VALUES ('user1', '$2a$10$0DotfQA1EPIkuL15D8EH4eFVFdNnYsU7M85FTRQLv0NILFYmvC8Oa');
INSERT INTO users(login, password) VALUES ('user2', '$2a$10$0DotfQA1EPIkuL15D8EH4eFVFdNnYsU7M85FTRQLv0NILFYmvC8Oa');
INSERT INTO users(login, password) VALUES ('user3', '$2a$10$0DotfQA1EPIkuL15D8EH4eFVFdNnYsU7M85FTRQLv0NILFYmvC8Oa');
-- insert two roles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
-- insert role to all users
INSERT INTO users_roles(user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (2, 1);
INSERT INTO users_roles(user_id, role_id) VALUES (3, 1);
-- insert card to all users
INSERT INTO cards(name, money, user_id) VALUES ('visa', 100, 1);
INSERT INTO cards(name, money, user_id) VALUES ('mastercard', 50, 1);
INSERT INTO cards(name, money, user_id) VALUES ('visa', 100, 2);
INSERT INTO cards(name, money, user_id) VALUES ('mastercard', 50, 2);
INSERT INTO cards(name, money, user_id) VALUES ('visa', 100, 3);
INSERT INTO cards(name, money, user_id) VALUES ('mastercard', 50, 3);