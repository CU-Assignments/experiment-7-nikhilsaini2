CREATE DATABASE student_portal;
USE student_portal;

CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(50),
    attendance_date DATE,
    status VARCHAR(10)
);
