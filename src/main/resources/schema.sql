DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name NVARCHAR(100) NOT NULL,
    age INTEGER,
    email NVARCHAR(50)
);