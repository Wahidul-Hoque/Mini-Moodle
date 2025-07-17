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
    password_hash TEXT NOT NULL,
    email TEXT
);

-- Insert Admin
INSERT INTO admin ( username, password_hash,email) VALUES ('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9','admin@moodle.com');

-- Create Teacher table
CREATE TABLE teacher (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    name TEXT,
    email TEXT
);

-- Insert Teacher
INSERT INTO teacher ( username, password_hash,name,email)
VALUES ('t_john', '63cb9c6fa2d65784658539a93ad47f2274a02ddff344537beb97bd399938ad22','John Cen','john@moodle.com');
INSERT INTO teacher ( username, password_hash,name,email)
VALUES ('wahid', '78d8b6e2ec5dde994a31b498f16595d609995935e1e69f99f56aa0c968b7e079','Wahidul', 'wahid.com');
-- Create Student table
CREATE TABLE student (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
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
-- Insert sample courses with a fixed teacher ID
INSERT INTO course (title, description, teacher_id)
VALUES ('CS101', 'Intro to Computer Science', 1);

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




