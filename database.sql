CREATE DATABASE company;
USE company;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    department VARCHAR(50)
);

INSERT INTO employees (name, department) VALUES 
('Alice', 'IT'), 
('Bob', 'HR'), 
('Charlie', 'Finance');
