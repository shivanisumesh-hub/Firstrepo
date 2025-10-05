-- 1. Create database
CREATE DATABASE rentdb
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

-- 2. Create a dedicated user
CREATE USER 'rentuser'@'localhost' IDENTIFIED BY 'RentPassword123!';

-- 3. Grant privileges to the user
GRANT ALL PRIVILEGES ON rentdb.* TO 'rentuser'@'localhost';
FLUSH PRIVILEGES;

-- 4. Switch to the new database
USE rentdb;

-- 5. Create a sample table
CREATE TABLE property (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

-- 6. Insert sample data
INSERT INTO property (name, location, price) VALUES ('Dream Apartment', 'City Center', 1200.50);
