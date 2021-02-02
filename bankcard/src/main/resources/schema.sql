-- create table users
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  login VARCHAR(25) NOT NULL,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(25) DEFAULT 'name',
  surname VARCHAR(25) DEFAULT 'surname',
  money FLOAT(2) DEFAULT 0 CONSTRAINT positive_money CHECK (money >= 0),
);

-- create table cards
CREATE TABLE cards(
  id SERIAL PRIMARY KEY,
  name VARCHAR(25),
  money FLOAT(2) DEFAULT 0 CONSTRAINT positive_money CHECK (money >= 0),
  user_id INT REFERENCES users(id)
);

-- create table roles
CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(25)
);

-- create table users_roles
CREATE TABLE users_roles(
  user_id INT REFERENCES users(id),
  role_id INT REFERENCES roles(id)
);