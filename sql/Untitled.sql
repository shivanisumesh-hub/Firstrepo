USE rentdb_demo;
CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150),
  password VARCHAR(150),
  dtype VARCHAR(31)
);
CREATE TABLE student (
  id BIGINT PRIMARY KEY,
  admission_no VARCHAR(100) UNIQUE,
  college VARCHAR(200),
  FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE
);
CREATE TABLE owner (
  id BIGINT PRIMARY KEY,
  phone_number VARCHAR(50) UNIQUE,
  email VARCHAR(150),
  FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE
);
CREATE TABLE accommodation (
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
CREATE TABLE room (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  room_number VARCHAR(50),
  is_occupied BOOLEAN,
  rent DOUBLE,
  accommodation_id BIGINT,
  FOREIGN KEY (accommodation_id) REFERENCES accommodation(id) ON DELETE CASCADE
);
CREATE TABLE book (
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
CREATE TABLE rental_record (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(50),
  item_id BIGINT,
  student_id BIGINT,
  start_date DATE,
  due_date DATE,
  returned BOOLEAN
);
INSERT INTO user (name, password, dtype) VALUES
('Alice','pass123','Student'),
('Bob','pass123','Student'),
('Charlie','pass123','Student'),
('Mr. Raj','owner123','Owner'),
('Ms. Meera','owner123','Owner');
INSERT INTO student (id, admission_no, college)
SELECT id, 'S001', 'KTU' FROM user WHERE name='Alice'
UNION ALL
SELECT id, 'S002', 'Cochin University' FROM user WHERE name='Bob'
UNION ALL
SELECT id, 'S003', 'NIT' FROM user WHERE name='Charlie';
INSERT INTO owner (id, phone_number, email)
SELECT id, '+91-987650001', 'raj@renttogo.com' FROM user WHERE name='Mr. Raj'
UNION ALL
SELECT id, '+91-987650002', 'meera@renttogo.com' FROM user WHERE name='Ms. Meera';
INSERT INTO accommodation (name, address, city, price_per_month, contact_number, total_rooms, available_rooms, latitude, longitude)
VALUES
('Blue Oak PG','Near KTU Campus, Trivandrum','Trivandrum',6000,'+91-987650001',20,6,8.5241,76.9366),
('Green Stay Hostel','Near College Road, Kochi','Kochi',4500,'+91-987650002',15,3,9.9312,76.2673),
('Sunny Side PG','MG Road, Kochi','Kochi',5000,'+91-987650003',18,10,9.9612,76.2863),
('Lotus Residency','Near Technopark, Trivandrum','Trivandrum',5500,'+91-987650004',12,5,8.5240,76.9365),
('Palm Grove PG','Vyttila, Kochi','Kochi',4800,'+91-987650005',16,8,9.9667,76.2875);
INSERT INTO room (room_number, is_occupied, rent, accommodation_id) VALUES
('101', false, 6000, 1),
('102', false, 6000, 1),
('201', false, 4500, 2),
('202', false, 4500, 2),
('301', false, 5000, 3),
('302', false, 5000, 3),
('401', false, 5500, 4),
('402', false, 5500, 4),
('501', false, 4800, 5),
('502', false, 4800, 5);
INSERT INTO book (title, author, isbn, price, rent_per_week, available, owner_id)
SELECT 'Java Programming','Herbert Schildt','ISBN001',500,50,true,id FROM owner WHERE phone_number='+91-987650001'
UNION ALL
SELECT 'Spring Boot Guide','John Doe','ISBN002',600,60,true,id FROM owner WHERE phone_number='+91-987650002'
UNION ALL
SELECT 'Data Structures','Mark Allen','ISBN003',450,45,true,id FROM owner WHERE phone_number='+91-987650001';
INSERT INTO rental_record (type, item_id, student_id, start_date, due_date, returned) VALUES
('book',1,(SELECT id FROM student WHERE admission_no='S001'),'2025-10-01','2025-10-08',false),
('book',2,(SELECT id FROM student WHERE admission_no='S002'),'2025-10-02','2025-10-09',false),
('book',3,(SELECT id FROM student WHERE admission_no='S003'),'2025-10-03','2025-10-10',false);
