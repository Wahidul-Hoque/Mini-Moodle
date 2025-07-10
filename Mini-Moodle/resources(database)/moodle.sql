-- moodle.sql

-- Drop tables if they exist
DROP TABLE IF EXISTS enrollment;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;

-- Create Admin table
CREATE TABLE admin (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    email TEXT
);

-- Insert Admin
INSERT INTO admin ( username, password,email) VALUES ('admin', 'admin123','admin@moodle.com');

-- Create Teacher table
CREATE TABLE teacher (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    name TEXT,
    email TEXT
);

-- Insert Teacher
INSERT INTO teacher ( username, password,email, name)
VALUES ('t_john', 'teach123','john@moodle.com', 'John Cen');

-- Create Student table
CREATE TABLE student (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    name TEXT,
    email TEXT
);

--add course
CREATE TABLE course (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT,
    teacher_id INTEGER,
    FOREIGN KEY (teacher_id) REFERENCES teacher(id)
);

--enrollment
CREATE TABLE enrollment (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_id INTEGER,
    course_id INTEGER,
    status TEXT CHECK(status IN ('pending', 'approved', 'rejected')),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

-- Student applies for enrollment in a course
INSERT INTO enrollment (student_id, course_id, status)
VALUES (1, 2, 'pending'); -- Assuming student 1 applies for course 2




