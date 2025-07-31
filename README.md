# Mini Moodle

Mini Moodle is a lightweight Learning Management System (LMS) developed as a term project for the CSE 108 (Object-Oriented Programming Sessional) course (L-1 T-2). 

The platform is built using Java and JavaFX, offering a graphical user interface for efficient course and user management.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Screenshots](#screenshots)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Contributors](#contributors)
- [License](#license)

## Overview

Mini Moodle serves as a simplified version of popular LMS platforms, focusing on the core functionalities needed for academic course management. The system allows instructors and students to interact in a structured environment, supporting user authentication, course enrollment, grading and notification.

## Features

- **User Roles**: Supports three user roles—Student, Teacher, and Admin.
- **Secure Authentication**: All users have secure login. Students can register for an account. All users can change their password.
- **Admin Functionalities**:
  - Add new courses and assign teachers to courses.
  - View complete information of all students, including grades, names, and emails.
- **Teacher Functionalities**:
  - Approve or reject students applying for their courses.
  - Grade enrolled students.
  - Send notifications to students in their courses.
- **Student Functionalities**:
  - View available (unregistered) and applied courses.
  - Apply for courses.
  - See grades for enrolled courses.
  - Receive and view notifications from teachers (if enrolled in their courses).

## Screenshots

Here is the link for the screenshots: [mehedihasankanon/Mini-Moodle/files/Mini-Moodle/Screenshots](https://github.com/mehedihasankanon/Mini-Moodle/tree/main/Mini-Moodle/Screenshots)

## Getting Started

### Prerequisites

- Java 8 or higher
- JavaFX SDK

### How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/mehedihasankanon/Mini-Moodle.git
   ```
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Make sure JavaFX is properly configured in your project settings.
4. Build and run the `Mini-Moodle` main class.

## Project Structure

```
Mini-Moodle/
│
├── .idea/                                  # IDE config files
├── .mvn/                                   # Maven wrapper support
│
├── resources/                              # Database and seed data for the app
│   ├── moodle.db
│   └── moodle.sql
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       ├── minimoodle/             # Main app controllers & entry points
│       │       │   ├── adminfunctionalities/     # Admin logic/controllers
│       │       │   ├── studentfunctionalities/   # Student logic/controllers
│       │       │   ├── teacherfunctionalities/   # Teacher logic/controllers
│       │       │   └── ...                  # Main controllers (login, dashboard, etc.)
│       │       ├── servicecodes/           # Backend logic, services, data models
│       │       └── utils/                  # Utility classes (DB, server, etc.)
│       │
│       ├── resources/
│       │   └── com/example/minimoodle/     # UI: FXML layouts, CSS styles
│       │       ├── ...                     # FXML and CSS files for all app pages
│       │
│       └── module-info.java                # Java module definition
│
├── .gitignore
├── pom.xml                                # Maven project config
├── LICENSE
└── README.md
```
## Contributors

- **Wahidul Hoque**  
  [GitHub](https://github.com/Wahidul-Hoque)

- **Mehedi Hasan Kanon**  
  [GitHub](https://github.com/mehedihasankanon)

## Acknowledgments

Supervised by Professor Dr. M Saifur Rahman (Dept. of CSE, BUET).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

> _Mini Moodle_ is a project for educational purposes, inspired by the core ideas of modern LMS platforms, reimagined with simplicity and clarity in mind.
