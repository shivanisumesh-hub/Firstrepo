CREATE DATABASE IF NOT EXISTS rentdb;
USE rentdb;

CREATE TABLE IF NOT EXISTS user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150),
  password VARCHAR(150),
  dtype VARCHAR(31)
);

CREATE TABLE IF NOT EXISTS student (
  id BIGINT PRIMARY KEY,
  admission_no VARCHAR(100) UNIQUE,
  college VARCHAR(200),
  FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS owner (
  id BIGINT PRIMARY KEY,
  phone_number VARCHAR(50) UNIQUE,
  email VARCHAR(150),
  FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS accommodation (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  address TEXT,
  latitude DOUBLE,
  longitude DOUBLE,
  price_per_month DOUBLE,
  contact_number VARCHAR(50),
  city VARCHAR(100),
  total_rooms INT,
  available_rooms INT
);

CREATE TABLE IF NOT EXISTS room (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  room_number VARCHAR(50),
  is_occupied BOOLEAN,
  rent DOUBLE,
  accommodation_id BIGINT,
  FOREIGN KEY (accommodation_id) REFERENCES accommodation(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS book (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  author VARCHAR(255),
  isbn VARCHAR(100),
  price DOUBLE,
  rent_per_week DOUBLE,
  available BOOLEAN,
  owner_id BIGINT,
  FOREIGN KEY (owner_id) REFERENCES owner(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS rental_record (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(50),
  item_id BIGINT,
  student_id BIGINT,
  start_date DATE,
  due_date DATE,
  returned BOOLEAN
);

INSERT INTO accommodation (name,address,city,price_per_month,contact_number,total_rooms,available_rooms)
VALUES 
('Blue Oak PG','Near KTU Campus, Trivandrum','Trivandrum',6000,'+91-987650001',20,6),
('Green Stay Hostel','Near College Road, Kochi','Kochi',4500,'+91-987650002',15,3);
