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
       ('student4', '4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a','student4','student4@example.com'),
       ('student5', 'ef2d127de37b942baad06145e54b0c619a1f22327b2ebbcfbec78f5564afe39d','student5','student5@example.com'),
       ('student6', 'e7f6c011776e8db7cd330b54174fd76f7d0216b612387a5ffcfb81e6f0919683','student6','student6@example.com'),
       ('student7', '7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451','student7','student7@example.com'),
       ('student8', '2c624232cdd221771294dfbb310aca000a0df6ac8b66b696d90ef06fdefb64a3','student8','student8@example.com'),
       ('student9', '19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7','student9','student9@example.com'),
       ('student10', '4a44dc15364204a80fe80e9039455cc1608281820fe2b24f1e5233ade6af1dd5','student10','student10@example.com'),
       ('student11', '4fc82b26aecb47d2868c4efbe3581732a3e7cbcc6c2efb32062c08170a05eeb8','student11','student11@example.com'),
       ('student12', '6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918','student12','student12@example.com'),
       ('student13', '3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278','student13','student13@example.com'),
       ('student14', '8527a891e224136950ff32ca212b45bc93f69fbb801c3b1ebedac52775f99e61','student14','student14@example.com'),
       ('student15', 'e629fa6598d732768f7c726b4b621285f9c3b85303900aa912017db7617d8bdb','student15','student15@example.com'),
       ('student16', 'b17ef6d19c7a5b1ee83b907c595526dcb1eb06db8227d650d5dda0a9f4ce8cd9','student16','student16@example.com'),
       ('student17', '4523540f1504cd17100c4835e85b7eefd49911580f8efff0599a8f283be6b9e3','student17','student17@example.com'),
       ('student18', '4ec9599fc203d176a301536c2e091a19bc852759b255bd6818810a42c5fed14a','student18','student18@example.com'),
       ('student19', '9400f1b21cb527d7fa3d3eabba93557a18ebe7a2ca4e471cfe5e4c5b4ca7f767','student19','student19@example.com'),
       ('student20', 'f5ca38f748a1d6eaf726b8a42fb575c3c71f1864a8143301782de13da2d9202b','student20','student20@example.com');
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
       (2, 2, 'pending', 'student2@example.com', 'NOT_SET'),
        (3, 2, 'approved', 'student3@example.com', 'B+'),
        (4, 2, 'approved', 'student4@example.com', 'A+'),
       (5, 2, 'approved', 'student5@example.com', 'A-'),
       (6, 2, 'approved', 'student6@example.com', 'B'),
       (7, 2, 'approved', 'student7@example.com', 'C'),
       (8, 2, 'approved', 'student8@example.com', 'D'),
       (9, 2, 'approved', 'student9@example.com', 'B+'),
       (10, 2, 'approved', 'student10@example.com', 'B-'),
       (11, 2, 'approved', 'student11@example.com', 'A-'),
       (12, 2, 'approved', 'student12@example.com', 'A'),
       (13, 2, 'pending', 'student13@example.com', 'NOT_SET'),
       (14, 2, 'pending', 'student14@example.com', 'NOT_SET'),
       (15, 2, 'pending', 'student15@example.com', 'NOT_SET'),
       (16, 2, 'pending', 'student16@example.com', 'NOT_SET'),
       (17, 2, 'pending', 'student17@example.com', 'NOT_SET'),
       (18, 2, 'pending', 'student18@example.com', 'NOT_SET'),
       (19, 2, 'pending', 'student19@example.com', 'NOT_SET'),
       (20, 2, 'pending', 'student20@example.com', 'NOT_SET');



CREATE TABLE notifications (
    id INTEGER PRIMARY KEY AUTOINCREMENT,      -- Unique ID for each notification
    course_id INTEGER,                         -- Foreign Key referencing the course table
    message TEXT NOT NULL,                     -- The notification message
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, -- Timestamp of when the notification was sent
    FOREIGN KEY (course_id) REFERENCES course(id) -- Link to the course table
);



