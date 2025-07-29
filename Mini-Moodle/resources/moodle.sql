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
VALUES
    ('wahid', '78d8b6e2ec5dde994a31b498f16595d609995935e1e69f99f56aa0c968b7e079', 'Wahidul Hoque', 'wahid.com'),
    ('teacher1', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'Md Saifur Rahman', 'teacher1@gmail.com'),
    ('teacher2', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', 'Hasan Mahmud', 'teacher2@gmail.com'),
    ('teacher3', '4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce', 'Reaza', 'teacher3@gmail.com'),
    ('teacher4', '4b227777d4dd1fc61c6f884f48641d02b6f9d24d8fa6a4f1fe5b6b5e3d2e9a2b', 'Ahmad uddin', 'teacher4@gmail.com'),
    ('teacher5', 'ef2d127de37b942be5a8e4c262f8c6f9a6aa1f6ed1d8e6b8d7a1a7efb97a3c7b', 'Karim Uddin', 'teacher5@gmail.com'),
    ('teacher6', 'e1671797c52e15f763380b45e841ec32f1b5100576f46e6dd4cfdc8c6b8efd71', 'John Cena', 'teacher6@gmail.com'),
    ('teacher7', '5f4dcc3b5aa765d61d8327deb882cf99a6d6f8b9b9c8c4f1e9c8c7b8a8e7f8b7', 'Abu Fayeem', 'teacher7@gmail.com'),
    ('teacher8', '2c624232cdd221771294dfbb310aca000a0df6ac8b66b696d90ef06fdefb64a3', 'Mehedi Hasan', 'teacher8@gmail.com'),
    ('teacher9', '1956f25ad0c4f4f3e9d3b3a1a2d6f7a1e9d1a2f6b9f6a6e1c1a8c7d3f7b9c8b6', 'Rifat Shahriar', 'teacher9@gmail.com'),
    ('teacher10', '4a44dc15364204a80fe80e9039455cc1608281820fe2b24dd8f2d6aeb4b6f4e2', 'Random', 'teacher10@gmail.com'),
    ('teacher11', '6b51c5b964ca9f1e0e2c8e6a3b1a9d8f7d1c6b2d9e4e7f3b2d7b8c9e1c3f5d2a', 'kohn doe', 'teacher11@gmail.com'),
    ('teacher12', '6a9c7b8e2f6e7a1d4b5c1f8e9b1d2c3a5e7b9c8d4f1e2d3c7a8b9e1d2f4c3b1a', 'Geof smith', 'teacher12@gmail.com'),
    ('teacher13', '1dcca23355272056f04fe8bf20edfce0e28b3889b6b4b898d1347e7a623b2075', 'hello world', 'teacher13@gmail.com'),
    ('teacher14', '1b7f8e4c3f6a9b2d5e7c1d2f8b9e4d3c7a1b9e2f4d6c8a7b5e1c3f7d8b2a9c6e', 'abu wahid', 'teacher14@gmail.com'),
    ('teacher15', '3b0c8ac703f828b04c6c197006d17218b8c5c2f8b0b3b7a1e9f7c8e7b5d2a6f9', 'emon ahsan', 'teacher15@gmail.com'),
    ('teacher16', '4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce', 'noman ch', 'teacher16@gmail.com'),
    ('teacher17', '6ea9ab1baa0efb9e19094440c317e21b2eaf40dc5ec4e0e4c5d8e6c5e5e4e4e4', 'shihab hasan', 'teacher17@gmail.com'),
    ('teacher18', '6f4922f45568161a8cdf4ad2299f6d22e2e06fae3e1a1e4e8c9c1f8d6b7b5e3c', 'tutor', 'teacher18@gmail.com'),
    ('teacher19', '2e7d2c03a9507ae265ecf5b5356885a53393a2029bc2c1b6baf8186e7c0a69b5', 'ahmed hamza', 'teacher19@gmail.com'),
    ('teacher20', 'e9d71f5ee7c92d6dc9e92ffdad17b8bd49418f98c91b6c6d4019f4a6e6b7e5c3', 'nafis', 'teacher20@gmail.com');

-- Create Student table
CREATE TABLE student (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    name TEXT,
    email TEXT
);
INSERT INTO student ( username, password_hash, name, email)
VALUES
    ('student1', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'Ava Patel', 'student1@example.com'),
    ('student2', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', 'Liam Johnson', 'student2@example.com'),
    ('student3', '4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce', 'Sophia Kim', 'student3@example.com'),
    ('student4', '4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a', 'Noah Chen', 'student4@example.com'),
    ('student5', 'ef2d127de37b942baad06145e54b0c619a1f22327b2ebbcfbec78f5564afe39d', 'Mia Garcia', 'student5@example.com'),
    ('student6', 'e7f6c011776e8db7cd330b54174fd76f7d0216b612387a5ffcfb81e6f0919683', 'Jackson Lee', 'student6@example.com'),
    ('student7', '7902699be42c8a8e46fbbb4501726517e86b22c56a189f7625a6da49081b2451', 'Isabella Brown', 'student7@example.com'),
    ('student8', '2c624232cdd221771294dfbb310aca000a0df6ac8b66b696d90ef06fdefb64a3', 'Elijah Davis', 'student8@example.com'),
    ('student9', '19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7', 'Amelia Wilson', 'student9@example.com'),
    ('student10', '4a44dc15364204a80fe80e9039455cc1608281820fe2b24f1e5233ade6af1dd5', 'Lucas Martinez', 'student10@example.com'),
    ('student11', '4fc82b26aecb47d2868c4efbe3581732a3e7cbcc6c2efb32062c08170a05eeb8', 'Evelyn Walker', 'student11@example.com'),
    ('student12', '6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918', 'Benjamin Clark', 'student12@example.com'),
    ('student13', '3fdba35f04dc8c462986c992bcf875546257113072a909c162f7e470e581e278', 'Harper Lewis', 'student13@example.com'),
    ('student14', '8527a891e224136950ff32ca212b45bc93f69fbb801c3b1ebedac52775f99e61', 'Henry Robinson', 'student14@example.com'),
    ('student15', 'e629fa6598d732768f7c726b4b621285f9c3b85303900aa912017db7617d8bdb', 'Ella Young', 'student15@example.com'),
    ('student16', 'b17ef6d19c7a5b1ee83b907c595526dcb1eb06db8227d650d5dda0a9f4ce8cd9', 'Alexander King', 'student16@example.com'),
    ('student17', '4523540f1504cd17100c4835e85b7eefd49911580f8efff0599a8f283be6b9e3', 'Scarlett Wright', 'student17@example.com'),
    ('student18', '4ec9599fc203d176a301536c2e091a19bc852759b255bd6818810a42c5fed14a', 'Logan Scott', 'student18@example.com'),
    ('student19', '9400f1b21cb527d7fa3d3eabba93557a18ebe7a2ca4e471cfe5e4c5b4ca7f767', 'Grace Hall', 'student19@example.com'),
    ('student20', 'f5ca38f748a1d6eaf726b8a42fb575c3c71f1864a8143301782de13da2d9202b', 'William Green', 'student20@example.com'),
    ('student21', 'e3c8c9856d6637a0c785b8d5f1c1c5b5d6e4f4bb740f6d6d6e4e5b5e4c9c6b5c', 'Chloe Edwards', 'student21@example.com'),
    ('student22', '5d41402abc4b2a76b9719d911017c592', 'James Bennett', 'student22@example.com'),
    ('student23', '4b43b0aee35624cd95b910189b3dc231', 'Avery Nguyen', 'student23@example.com'),
    ('student24', 'e1671797c52e15f763380b45e841ec32f1b5100576f46e6dd4cfdc8c6b8efd71', 'Ella Stewart', 'student24@example.com'),
    ('student25', '6c8349cc7260ae62e3b1396831a8398f', 'Carter Torres', 'student25@example.com'),
    ('student26', 'cd73502828457d15655bbd7a63fb0bc8', 'Layla Price', 'student26@example.com'),
    ('student27', '9b8619251a19057cff70779273e95aa6', 'Sebastian Perez', 'student27@example.com'),
    ('student28', '7b8b965ad4bca0e41ab51de7b31363a1', 'Stella Reed', 'student28@example.com'),
    ('student29', '8f14e45fceea167a5a36dedd4bea2543', 'Julian Cox', 'student29@example.com'),
    ('student30', 'c9f0f895fb98ab9159f51fd0297e236d', 'Penelope Flores', 'student30@example.com'),
    ('student31', '45c48cce2e2d7fbdea1afc51c7c6ad26', 'Wyatt Rivera', 'student31@example.com'),
    ('student32', 'd3d9446802a44259755d38e6d163e820', 'Nora Wood', 'student32@example.com'),
    ('student33', '6512bd43d9caa6e02c990b0a82652dca', 'Levi Watson', 'student33@example.com'),
    ('student34', 'c20ad4d76fe97759aa27a0c99bff6710', 'Hazel Brooks', 'student34@example.com'),
    ('student35', 'c51ce410c124a10e0db5e4b97fc2af39', 'Gabriel Sanders', 'student35@example.com'),
    ('student36', 'aab3238922bcc25a6f606eb525ffdc56', 'Lillian Simmons', 'student36@example.com'),
    ('student37', '9bf31c7ff062936a96d3c8bd1f8f2ff3', 'David Powell', 'student37@example.com'),
    ('student38', 'c74d97b01eae257e44aa9d5bade97baf', 'Aurora Butler', 'student38@example.com'),
    ('student39', '70efdf2ec9b086079795c442636b55fb', 'Lincoln Patterson', 'student39@example.com'),
    ('student40', '6f4922f45568161a8cdf4ad2299f6d22', 'Violet Barnes', 'student40@example.com'),
    ('student41', '1f0e3dad99908345f7439f8ffabdffc4', 'Matthew Bailey', 'student41@example.com'),
    ('student42', '98f13708210194c475687be6106a3b84', 'Zoey Cooper', 'student42@example.com'),
    ('student43', '3c59dc048e8850243be8079a5c74d079', 'Samuel Gomez', 'student43@example.com'),
    ('student44', 'b6d767d2f8ed5d21a44b0e5886680cb9', 'Natalie Martinez', 'student44@example.com'),
    ('student45', '37693cfc748049e45d87b8c7d8b9aacd', 'Luke Murphy', 'student45@example.com'),
    ('student46', '1ff1de774005f8da13f42943881c655f', 'Aria Mitchell', 'student46@example.com'),
    ('student47', '8e296a067a37563370ded05f5a3bf3ec', 'Jack Bryant', 'student47@example.com'),
    ('student48', '4e732ced3463d06de0ca9a15b6153677', 'Ellie Hughes', 'student48@example.com'),
    ('student49', '02e74f10e0327ad868d138f2b4fdd6f0', 'Owen Russell', 'student49@example.com'),
    ('student50', '33e75ff09dd601bbe69f351039152189', 'Sofia Griffin', 'student50@example.com'),
    ('student51', '6ea9ab1baa0efb9e19094440c317e21b', 'Jaxon Hayes', 'student51@example.com'),
    ('student52', '34173cb38f07f89ddbebc2ac9128303f', 'Victoria West', 'student52@example.com'),
    ('student53', 'c16a5320fa475530d9583c34fd356ef5', 'Caleb Jenkins', 'student53@example.com'),
    ('student54', '6364d3f0f495b6ab9dcf8d3b5c6e0b01', 'Aubrey Perry', 'student54@example.com'),
    ('student55', '182be0c5cdcd5072bb1864cdee4d3d6e', 'Mason Ford', 'student55@example.com'),
    ('student56', 'e369853df766fa44e1ed0ff613f563bd', 'Addison Graham', 'student56@example.com'),
    ('student57', '1c383cd30b7c298ab50293adfecb7b18', 'Eli Hamilton', 'student57@example.com'),
    ('student58', '19ca14e7ea6328a42e0eb13d585e4c22', 'Hannah Stone', 'student58@example.com'),
    ('student59', 'a5e00132373a7031000fd987a3c9f87b', 'Aiden Wallace', 'student59@example.com'),
    ('student60', 'a5771bce93e200c36f7cd9dfd0e5deaa', 'Savannah Chapman', 'student60@example.com'),
    ('student61', 'd67d8ab4f4c10bf22aa353e27879133c', 'Isaac Hart', 'student61@example.com'),
    ('student62', 'f7177163c833dff4b38fc8d2872f1ec6', 'Leah Black', 'student62@example.com'),
    ('student63', '6c569aabbf7775ef8fc570e228c16b98', 'Christopher Stephens', 'student63@example.com'),
    ('student64', 'd1fe173d08e959397adf34b1d77e88d7', 'Zoey Fisher', 'student64@example.com'),
    ('student65', '8613985ec49eb8f757ae6439e879bb2a', 'Jonathan Ross', 'student65@example.com'),
    ('student66', '4b825dc642cb6eb9a060e54bf8d69288', 'Camila Kennedy', 'student66@example.com'),
    ('student67', '36f1b8b6b6c447ddb94c29c3d29c0b82', 'Hudson Shaw', 'student67@example.com'),
    ('student68', '1c8bfe8f801d79745c4631d09fff36f8', 'Madelyn Holmes', 'student68@example.com'),
    ('student69', 'e2ef524fbf3d9fe611d5a8e90fefdc9c', 'Nathan Carr', 'student69@example.com'),
    ('student70', '161b3c2c23c3a0fa9b2a5a9f6b6c2d1c', 'Aurora Andrews', 'student70@example.com');
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
VALUES ('CSE 101', 'Intro to Computer Science', 1),
       ('CSE 102', 'Data Structures', 2),
       ('CSE 103', 'Discrete Mathematics', 3),
       ('CSE 104', 'Algorithms', 4),
       ('CSE 205', 'Database Systems', 5),
       ('CSE 206', 'Operating Systems', 6),
       ('CSE 207', 'Computer Networks', 7),
       ('CSE 108', 'OOP Sessional', 8),
       ('CSE 309', 'Digital Logic Design', 9),
       ('CSE 310', 'Software Engineering', 10),
       ('CSE 311', 'Web Technologies', 11),
       ('CSE 312', 'Artificial Intelligence', 12),
       ('CSE 313', 'Compiler Design', 13),
       ('CSE 414', 'Numerical Methods', 14),
       ('CSE 415', 'Machine Learning', 15),
       ('CSE 416', 'Microprocessors', 16),
       ('CSE 217', 'Computer Graphics', 17),
       ('CSE 418', 'Cryptography', 18),
       ('CSE 419', 'Cyber Security', 19),
       ('CSE 320', 'Human-Computer Interaction', 20),
       ('CSE 321', 'Cloud Computing', 21);

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
VALUES

    (1, 1, 'approved', 'student1@example.com', 'A'),
    (2, 1, 'approved', 'student2@example.com', 'A-'),
    (3, 1, 'approved', 'student3@example.com', 'B+'),
    (4, 1, 'approved', 'student4@example.com', 'B'),
    (5, 1, 'pending',  'student5@example.com', 'NOT_SET'),
    (6, 1, 'approved', 'student6@example.com', 'C+'),
    (7, 1, 'approved', 'student7@example.com', 'A'),
    (8, 1, 'pending',  'student8@example.com', 'NOT_SET'),
    (9, 1, 'approved', 'student9@example.com', 'A-'),
    (10, 1, 'approved', 'student10@example.com', 'B-'),
    (11, 1, 'approved', 'student11@example.com', 'A-'),
    (12, 1, 'approved', 'student12@example.com', 'A'),
    (13, 1, 'pending',  'student13@example.com', 'NOT_SET'),
    (14, 1, 'pending',  'student14@example.com', 'NOT_SET'),
    (15, 1, 'approved', 'student15@example.com', 'A'),
    (16, 1, 'approved', 'student16@example.com', 'B+'),
    (17, 1, 'pending',  'student17@example.com', 'NOT_SET'),
    (18, 1, 'approved', 'student18@example.com', 'A'),
    (19, 1, 'approved', 'student19@example.com', 'B+'),
    (20, 1, 'approved', 'student20@example.com', 'A-'),
    (21, 1, 'pending',  'student21@example.com', 'NOT_SET'),
    (22, 1, 'approved', 'student22@example.com', 'B'),
    (23, 1, 'approved', 'student23@example.com', 'A'),
    (24, 1, 'approved', 'student24@example.com', 'A+'),
    (25, 1, 'approved', 'student25@example.com', 'B-'),
    (26, 1, 'approved', 'student26@example.com', 'C'),
    (27, 1, 'pending',  'student27@example.com', 'NOT_SET'),
    (28, 1, 'approved', 'student28@example.com', 'A-'),
    (29, 1, 'pending',  'student29@example.com', 'NOT_SET'),
    (30, 1, 'approved', 'student30@example.com', 'B'),

       (1, 2, 'approved', 'student1@example.com', 'A'),
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
       (20, 2, 'pending', 'student20@example.com', 'NOT_SET'),

-- Course 3, students 1-15
       (1, 3, 'approved', 'student1@example.com', 'B+'),
       (2, 3, 'approved', 'student2@example.com', 'A'),
       (3, 3, 'approved', 'student3@example.com', 'A-'),
       (4, 3, 'pending', 'student4@example.com', 'NOT_SET'),
       (5, 3, 'pending', 'student5@example.com', 'NOT_SET'),
       (6, 3, 'approved', 'student6@example.com', 'C+'),
       (7, 3, 'approved', 'student7@example.com', 'B'),
       (8, 3, 'pending', 'student8@example.com', 'NOT_SET'),
       (9, 3, 'approved', 'student9@example.com', 'A-'),
       (10, 3, 'approved', 'student10@example.com', 'B-'),
       (11, 3, 'approved', 'student11@example.com', 'A'),
       (12, 3, 'approved', 'student12@example.com', 'A+'),
       (13, 3, 'pending', 'student13@example.com', 'NOT_SET'),
       (14, 3, 'pending', 'student14@example.com', 'NOT_SET'),
       (15, 3, 'pending', 'student15@example.com', 'NOT_SET'),

-- Course 4, students 5-24
       (5, 4, 'approved', 'student5@example.com', 'A'),
       (6, 4, 'pending', 'student6@example.com', 'NOT_SET'),
       (7, 4, 'approved', 'student7@example.com', 'B'),
       (8, 4, 'approved', 'student8@example.com', 'B+'),
       (9, 4, 'approved', 'student9@example.com', 'A-'),
       (10, 4, 'approved', 'student10@example.com', 'A'),
       (11, 4, 'pending', 'student11@example.com', 'NOT_SET'),
       (12, 4, 'pending', 'student12@example.com', 'NOT_SET'),
       (13, 4, 'approved', 'student13@example.com', 'C+'),
       (14, 4, 'pending', 'student14@example.com', 'NOT_SET'),
       (15, 4, 'approved', 'student15@example.com', 'B+'),
       (16, 4, 'approved', 'student16@example.com', 'B'),
       (17, 4, 'approved', 'student17@example.com', 'A-'),
       (18, 4, 'pending', 'student18@example.com', 'NOT_SET'),
       (19, 4, 'approved', 'student19@example.com', 'B-'),
       (20, 4, 'approved', 'student20@example.com', 'A+'),
       (21, 4, 'pending', 'student21@example.com', 'NOT_SET'),
       (22, 4, 'pending', 'student22@example.com', 'NOT_SET'),
       (23, 4, 'pending', 'student23@example.com', 'NOT_SET'),
       (24, 4, 'approved', 'student24@example.com', 'B'),

-- Course 5, students 9-28
       (9, 5, 'pending', 'student9@example.com', 'NOT_SET'),
       (10, 5, 'approved', 'student10@example.com', 'A'),
       (11, 5, 'approved', 'student11@example.com', 'A+'),
       (12, 5, 'pending', 'student12@example.com', 'NOT_SET'),
       (13, 5, 'approved', 'student13@example.com', 'B'),
       (14, 5, 'approved', 'student14@example.com', 'A-'),
       (15, 5, 'approved', 'student15@example.com', 'B+'),
       (16, 5, 'pending', 'student16@example.com', 'NOT_SET'),
       (17, 5, 'pending', 'student17@example.com', 'NOT_SET'),
       (18, 5, 'approved', 'student18@example.com', 'A'),
       (19, 5, 'approved', 'student19@example.com', 'C+'),
       (20, 5, 'approved', 'student20@example.com', 'B'),
       (21, 5, 'approved', 'student21@example.com', 'B-'),
       (22, 5, 'pending', 'student22@example.com', 'NOT_SET'),
       (23, 5, 'approved', 'student23@example.com', 'A-'),
       (24, 5, 'approved', 'student24@example.com', 'A'),
       (25, 5, 'approved', 'student25@example.com', 'A+'),
       (26, 5, 'pending', 'student26@example.com', 'NOT_SET'),
       (27, 5, 'pending', 'student27@example.com', 'NOT_SET'),
       (28, 5, 'approved', 'student28@example.com', 'B'),

-- Course 6, students 13-32
       (13, 6, 'approved', 'student13@example.com', 'A-'),
       (14, 6, 'approved', 'student14@example.com', 'C'),
       (15, 6, 'pending', 'student15@example.com', 'NOT_SET'),
       (16, 6, 'approved', 'student16@example.com', 'A+'),
       (17, 6, 'pending', 'student17@example.com', 'NOT_SET'),
       (18, 6, 'approved', 'student18@example.com', 'B+'),
       (19, 6, 'approved', 'student19@example.com', 'B'),
       (20, 6, 'pending', 'student20@example.com', 'NOT_SET'),
       (21, 6, 'approved', 'student21@example.com', 'A'),
       (22, 6, 'approved', 'student22@example.com', 'A-'),
       (23, 6, 'pending', 'student23@example.com', 'NOT_SET'),
       (24, 6, 'approved', 'student24@example.com', 'C+'),
       (25, 6, 'approved', 'student25@example.com', 'A'),
       (26, 6, 'pending', 'student26@example.com', 'NOT_SET'),
       (27, 6, 'pending', 'student27@example.com', 'NOT_SET'),
       (28, 6, 'approved', 'student28@example.com', 'A-'),
       (29, 6, 'pending', 'student29@example.com', 'NOT_SET'),
       (30, 6, 'pending', 'student30@example.com', 'NOT_SET'),
       (31, 6, 'approved', 'student31@example.com', 'B'),
       (32, 6, 'approved', 'student32@example.com', 'B+'),

-- Course 7, students 17-36
       (17, 7, 'approved', 'student17@example.com', 'A'),
       (18, 7, 'pending', 'student18@example.com', 'NOT_SET'),
       (19, 7, 'approved', 'student19@example.com', 'B-'),
       (20, 7, 'approved', 'student20@example.com', 'A+'),
       (21, 7, 'pending', 'student21@example.com', 'NOT_SET'),
       (22, 7, 'approved', 'student22@example.com', 'B+'),
       (23, 7, 'approved', 'student23@example.com', 'B'),
       (24, 7, 'pending', 'student24@example.com', 'NOT_SET'),
       (25, 7, 'approved', 'student25@example.com', 'C+'),
       (26, 7, 'approved', 'student26@example.com', 'B'),
       (27, 7, 'approved', 'student27@example.com', 'A-'),
       (28, 7, 'pending', 'student28@example.com', 'NOT_SET'),
       (29, 7, 'pending', 'student29@example.com', 'NOT_SET'),
       (30, 7, 'pending', 'student30@example.com', 'NOT_SET'),
       (31, 7, 'approved', 'student31@example.com', 'A'),
       (32, 7, 'pending', 'student32@example.com', 'NOT_SET'),
       (33, 7, 'approved', 'student33@example.com', 'A-'),
       (34, 7, 'approved', 'student34@example.com', 'B'),
       (35, 7, 'pending', 'student35@example.com', 'NOT_SET'),
       (36, 7, 'approved', 'student36@example.com', 'A'),

-- Course 8, students 21-40
       (21, 8, 'pending', 'student21@example.com', 'NOT_SET'),
       (22, 8, 'approved', 'student22@example.com', 'B+'),
       (23, 8, 'approved', 'student23@example.com', 'A'),
       (24, 8, 'pending', 'student24@example.com', 'NOT_SET'),
       (25, 8, 'approved', 'student25@example.com', 'B-'),
       (26, 8, 'approved', 'student26@example.com', 'A-'),
       (27, 8, 'approved', 'student27@example.com', 'A+'),
       (28, 8, 'pending', 'student28@example.com', 'NOT_SET'),
       (29, 8, 'approved', 'student29@example.com', 'B'),
       (30, 8, 'approved', 'student30@example.com', 'A-'),
       (31, 8, 'pending', 'student31@example.com', 'NOT_SET'),
       (32, 8, 'approved', 'student32@example.com', 'A'),
       (33, 8, 'approved', 'student33@example.com', 'C+'),
       (34, 8, 'pending', 'student34@example.com', 'NOT_SET'),
       (35, 8, 'pending', 'student35@example.com', 'NOT_SET'),
       (36, 8, 'approved', 'student36@example.com', 'B+'),
       (37, 8, 'approved', 'student37@example.com', 'A'),
       (38, 8, 'approved', 'student38@example.com', 'B'),
       (39, 8, 'pending', 'student39@example.com', 'NOT_SET'),
       (40, 8, 'pending', 'student40@example.com', 'NOT_SET'),

-- Course 9, students 25-44
       (25, 9, 'approved', 'student25@example.com', 'A-'),
       (26, 9, 'pending', 'student26@example.com', 'NOT_SET'),
       (27, 9, 'approved', 'student27@example.com', 'B'),
       (28, 9, 'approved', 'student28@example.com', 'A'),
       (29, 9, 'pending', 'student29@example.com', 'NOT_SET'),
       (30, 9, 'approved', 'student30@example.com', 'B-'),
       (31, 9, 'approved', 'student31@example.com', 'A+'),
       (32, 9, 'approved', 'student32@example.com', 'A'),
       (33, 9, 'pending', 'student33@example.com', 'NOT_SET'),
       (34, 9, 'pending', 'student34@example.com', 'NOT_SET'),
       (35, 9, 'approved', 'student35@example.com', 'B+'),
       (36, 9, 'approved', 'student36@example.com', 'A-'),
       (37, 9, 'pending', 'student37@example.com', 'NOT_SET'),
       (38, 9, 'approved', 'student38@example.com', 'B'),
       (39, 9, 'approved', 'student39@example.com', 'C+'),
       (40, 9, 'approved', 'student40@example.com', 'A'),
       (41, 9, 'pending', 'student41@example.com', 'NOT_SET'),
       (42, 9, 'approved', 'student42@example.com', 'A-'),
       (43, 9, 'pending', 'student43@example.com', 'NOT_SET'),
       (44, 9, 'approved', 'student44@example.com', 'A'),
       -- Course 10
       (31, 10, 'approved', 'student31@example.com', 'A'),
       (32, 10, 'approved', 'student32@example.com', 'B+'),
       (33, 10, 'pending', 'student33@example.com', 'NOT_SET'),
       (34, 10, 'approved', 'student34@example.com', 'A-'),
       (35, 10, 'pending', 'student35@example.com', 'NOT_SET'),
       (36, 10, 'approved', 'student36@example.com', 'B'),
       (37, 10, 'pending', 'student37@example.com', 'NOT_SET'),

-- Course 11
       (38, 11, 'approved', 'student38@example.com', 'A-'),
       (39, 11, 'pending', 'student39@example.com', 'NOT_SET'),
       (40, 11, 'approved', 'student40@example.com', 'B+'),
       (41, 11, 'approved', 'student41@example.com', 'A'),
       (42, 11, 'pending', 'student42@example.com', 'NOT_SET'),
       (43, 11, 'approved', 'student43@example.com', 'B'),
       (44, 11, 'approved', 'student44@example.com', 'A+'),

-- Course 12
       (45, 12, 'pending', 'student45@example.com', 'NOT_SET'),
       (46, 12, 'approved', 'student46@example.com', 'B+'),
       (47, 12, 'approved', 'student47@example.com', 'A-'),
       (48, 12, 'pending', 'student48@example.com', 'NOT_SET'),
       (49, 12, 'approved', 'student49@example.com', 'A'),
       (50, 12, 'approved', 'student50@example.com', 'C+'),
       (51, 12, 'pending', 'student51@example.com', 'NOT_SET'),

-- Course 13
       (52, 13, 'approved', 'student52@example.com', 'A'),
       (53, 13, 'approved', 'student53@example.com', 'B'),
       (54, 13, 'pending', 'student54@example.com', 'NOT_SET'),
       (55, 13, 'approved', 'student55@example.com', 'A+'),
       (56, 13, 'pending', 'student56@example.com', 'NOT_SET'),
       (57, 13, 'approved', 'student57@example.com', 'B-'),
       (58, 13, 'approved', 'student58@example.com', 'A-'),

-- Course 14
       (59, 14, 'pending', 'student59@example.com', 'NOT_SET'),
       (60, 14, 'approved', 'student60@example.com', 'A'),
       (61, 14, 'approved', 'student61@example.com', 'B+'),
       (62, 14, 'pending', 'student62@example.com', 'NOT_SET'),
       (63, 14, 'approved', 'student63@example.com', 'A-'),
       (64, 14, 'pending', 'student64@example.com', 'NOT_SET'),
       (65, 14, 'approved', 'student65@example.com', 'B'),

-- Course 15
       (66, 15, 'approved', 'student66@example.com', 'A'),
       (67, 15, 'approved', 'student67@example.com', 'A-'),
       (68, 15, 'pending', 'student68@example.com', 'NOT_SET'),
       (69, 15, 'approved', 'student69@example.com', 'B+'),
       (70, 15, 'approved', 'student70@example.com', 'A'),
       (21, 15, 'pending', 'student21@example.com', 'NOT_SET'),
       (22, 15, 'approved', 'student22@example.com', 'B'),

-- Course 16
       (23, 16, 'approved', 'student23@example.com', 'A'),
       (24, 16, 'pending', 'student24@example.com', 'NOT_SET'),
       (25, 16, 'approved', 'student25@example.com', 'B+'),
       (26, 16, 'approved', 'student26@example.com', 'A-'),
       (27, 16, 'pending', 'student27@example.com', 'NOT_SET'),
       (28, 16, 'approved', 'student28@example.com', 'B'),
       (29, 16, 'approved', 'student29@example.com', 'A+'),

-- Course 17
       (30, 17, 'pending', 'student30@example.com', 'NOT_SET'),
       (31, 17, 'approved', 'student31@example.com', 'B-'),
       (32, 17, 'approved', 'student32@example.com', 'A'),
       (33, 17, 'pending', 'student33@example.com', 'NOT_SET'),
       (34, 17, 'approved', 'student34@example.com', 'B+'),
       (35, 17, 'approved', 'student35@example.com', 'A-'),
       (36, 17, 'pending', 'student36@example.com', 'NOT_SET'),

-- Course 18
       (37, 18, 'approved', 'student37@example.com', 'B'),
       (38, 18, 'approved', 'student38@example.com', 'A'),
       (39, 18, 'pending', 'student39@example.com', 'NOT_SET'),
       (40, 18, 'approved', 'student40@example.com', 'B+'),
       (41, 18, 'approved', 'student41@example.com', 'A-'),
       (42, 18, 'pending', 'student42@example.com', 'NOT_SET'),
       (43, 18, 'approved', 'student43@example.com', 'B'),

-- Course 19
       (44, 19, 'approved', 'student44@example.com', 'A'),
       (45, 19, 'approved', 'student45@example.com', 'A-'),
       (46, 19, 'pending', 'student46@example.com', 'NOT_SET'),
       (47, 19, 'approved', 'student47@example.com', 'B+'),
       (48, 19, 'pending', 'student48@example.com', 'NOT_SET'),
       (49, 19, 'approved', 'student49@example.com', 'A'),
       (50, 19, 'approved', 'student50@example.com', 'B+'),

-- Course 20
       (51, 20, 'approved', 'student51@example.com', 'A-'),
       (52, 20, 'approved', 'student52@example.com', 'B'),
       (53, 20, 'pending', 'student53@example.com', 'NOT_SET'),
       (54, 20, 'approved', 'student54@example.com', 'A'),
       (55, 20, 'approved', 'student55@example.com', 'B+'),
       (56, 20, 'pending', 'student56@example.com', 'NOT_SET'),
       (57, 20, 'approved', 'student57@example.com', 'A+'),

-- Course 21
       (58, 21, 'approved', 'student58@example.com', 'A'),
       (59, 21, 'pending', 'student59@example.com', 'NOT_SET'),
       (60, 21, 'approved', 'student60@example.com', 'B+'),
       (61, 21, 'approved', 'student61@example.com', 'A-'),
       (62, 21, 'pending', 'student62@example.com', 'NOT_SET'),
       (63, 21, 'approved', 'student63@example.com', 'B'),
       (64, 21, 'approved', 'student64@example.com', 'A');



CREATE TABLE notifications (
    id INTEGER PRIMARY KEY AUTOINCREMENT,      -- Unique ID for each notification
    course_id INTEGER,                         -- Foreign Key referencing the course table
    message TEXT NOT NULL,                     -- The notification message
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, -- Timestamp of when the notification was sent
    FOREIGN KEY (course_id) REFERENCES course(id) -- Link to the course table
);

INSERT INTO notifications (course_id, message)
VALUES
-- Course 1
(1, 'Welcome to CSE 101! Please check the syllabus uploaded in the portal.'),
(1, 'CSE 101: Lab schedule has been updated.'),
(1, 'Assignment 1 is released. Submission deadline: next Monday.'),
(1, 'Midterm exam will be held on September 15th.'),

-- Course 2
(2, 'Welcome to CSE 102! First class starts tomorrow at 9 AM.'),
(2, 'CSE 102: Quiz 1 will be conducted online.'),
(2, 'Assignment 2 is available now.'),
(2, 'Project proposal submissions are due this Friday.'),

-- Course 3
(3, 'Welcome to CSE 103: Discrete Mathematics.'),
(3, 'CSE 103: Homework 1 has been posted.'),
(3, 'Class on August 10th is cancelled.'),
(3, 'Midterm review session scheduled for next week.'),

-- Course 4
(4, 'CSE 104: Algorithms class materials updated.'),
(4, 'Lab 2 will be held in Room 302.'),
(4, 'New practice problems added to the course portal.'),
(4, 'Assignment 3 deadline extended by 2 days.'),

-- Course 5
(5, 'CSE 105: Database Systems - welcome note and resources uploaded.'),
(5, 'Database assignment 1 is due this weekend.'),
(5, 'Lab session will focus on SQL queries.'),
(5, 'Group project guidelines are now available.'),

-- Course 6
(6, 'CSE 106: Operating Systems course orientation tomorrow.'),
(6, 'Homework 2 is now open for submission.'),
(6, 'Lab 1 attendance is mandatory.'),
(6, 'Guest lecture on virtualization this Friday.'),

-- Course 7
(7, 'CSE 107: Computer Networks - class rescheduled to Wednesday.'),
(7, 'Network simulation assignment released.'),
(7, 'Quiz 1 marks have been published.'),
(7, 'Doubt-clearing session on Friday at 4 PM.'),

-- Course 8
(8, 'CSE 108: OOP Sessional - first lab on Thursday.'),
(8, 'Lab partners list is now available.'),
(8, 'Submit your first code assignment by Sunday.'),
(8, 'Lab equipment checklist uploaded.'),

-- Course 9
(9, 'CSE 109: Digital Logic Design - new reading material added.'),
(9, 'Lab 2 will focus on flip-flop circuits.'),
(9, 'Assignment 1 solutions posted.'),
(9, 'Class slides for week 2 are online.'),

-- Course 10
(10, 'CSE 110: Software Engineering - kickoff meeting today.'),
(10, 'Assignment 1 group allocation is complete.'),
(10, 'Class on Agile methodologies tomorrow.'),
(10, 'Demo for project tools is scheduled for next Monday.'),

-- Course 11
(11, 'CSE 111: Web Technologies - welcome to the course!'),
(11, 'First web lab starts this Wednesday.'),
(11, 'Assignment 1: Create a personal portfolio page.'),
(11, 'Class on client-server architecture this Friday.'),

-- Course 12
(12, 'CSE 112: Artificial Intelligence - introductory lecture slides uploaded.'),
(12, 'Quiz on search algorithms next week.'),
(12, 'AI project teams announced.'),
(12, 'Class recording for last session is online.'),

-- Course 13
(13, 'CSE 113: Compiler Design - homework 1 released.'),
(13, 'Lab on lexical analysis this Thursday.'),
(13, 'Assignment 2 deadline extended.'),
(13, 'Extra class scheduled for next Tuesday.'),

-- Course 14
(14, 'CSE 114: Numerical Methods - welcome!'),
(14, 'Assignment 1: Newton-Raphson method implementation.'),
(14, 'Lab 1 feedback posted.'),
(14, 'Class on root-finding algorithms tomorrow.'),

-- Course 15
(15, 'CSE 115: Machine Learning - join the introduction session.'),
(15, 'Assignment 1 on linear regression released.'),
(15, 'Lab partners assigned.'),
(15, 'Guest talk on deep learning this Friday.'),

-- Course 16
(16, 'CSE 116: Microprocessors - course outline posted.'),
(16, 'Lab 1 will cover assembly programming.'),
(16, 'Midterm date announced: October 5th.'),
(16, 'Practical demo session on microcontrollers next week.'),

-- Course 17
(17, 'CSE 117: Computer Graphics - introductory slides uploaded.'),
(17, 'Lab 1: Drawing basic shapes using OpenGL.'),
(17, 'Assignment 1: 2D transformations.'),
(17, 'Online quiz on raster graphics next week.'),

-- Course 18
(18, 'CSE 118: Cryptography - welcome, check course resources.'),
(18, 'Assignment 1: Caesar cipher implementation.'),
(18, 'Lab on symmetric encryption next Wednesday.'),
(18, 'Class on public-key cryptography on Friday.'),

-- Course 19
(19, 'CSE 119: Cyber Security - introduction and syllabus available.'),
(19, 'Lab 1 on network security tools.'),
(19, 'Assignment 1: Security audit report.'),
(19, 'Seminar on web vulnerabilities next Thursday.'),

-- Course 20
(20, 'CSE 120: Human-Computer Interaction - welcome session today.'),
(20, 'Assignment 1: User interface design.'),
(20, 'Lab 1: Usability testing practice.'),
(20, 'Class on cognitive models scheduled for next week.'),

-- Course 21
(21, 'CSE 121: Cloud Computing - course materials uploaded.'),
(21, 'Lab 1: Introduction to AWS.'),
(21, 'Assignment 1: Cloud deployment basics.'),
(21, 'Class on virtualization and containers this Friday.');

