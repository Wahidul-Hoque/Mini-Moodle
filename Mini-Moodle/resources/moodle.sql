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
VALUES ('wahid', '78d8b6e2ec5dde994a31b498f16595d609995935e1e69f99f56aa0c968b7e079','Wahidul', 'wahid.com'),
       ('teacher1', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'teacher1','teacher1@gmail.com'),
       ('teacher2', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', 'teacher2','teacher2@gmail.com'),
       ('teacher3', '4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce', 'teacher3','teacher3@gmail.com');

-- Create Student table
CREATE TABLE student (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    name TEXT,
    email TEXT
);
INSERT INTO student ( username, password_hash,name,email)
VALUES ('student1', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','student1','student1@example.com'),
       ('student2', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35','student2','student2@example.com'),
       ('student3', '4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce','student3','student3@example.com'),
       ('student4', '4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a','student4','student4@example.com');
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
VALUES ('CSE 101', 'Intro to Computer Science', 2),
       ('CSE 103', 'Discrete Mathematics', 1) ,
       ('MATH 141', 'MATH', 3) ,
       ('CSE 108', 'OOP SESSIONAL', 4) ;

--enrollment
CREATE TABLE enrollment (
    id INTEGER PRIMARY KEY AUTOINCREMENT,        -- Unique ID for each record
    student_id INTEGER,                          -- Foreign Key referencing student table
    course_id INTEGER,                           -- Foreign Key referencing course table
    status TEXT CHECK(status IN ('pending', 'approved', 'rejected')),  -- Enrollment status
    email TEXT,                                  -- Student's email
    grade TEXT,                                  -- Grade assigned to the student
    FOREIGN KEY (student_id) REFERENCES student(id),  -- Link to student
    FOREIGN KEY (course_id) REFERENCES course(id)     -- Link to course
);

-- Student applies for enrollment in a course
INSERT INTO enrollment (student_id, course_id, status, email, grade)
VALUES (1, 2, 'approved', 'student1@example.com', 'A'),
       (2, 2, 'pending', 'student2@example.com', NULL),
       (3, 2, 'approved', 'student3@example.com', 'B+');




