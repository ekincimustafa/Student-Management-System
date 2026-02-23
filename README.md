# University Student Management System

This project is a comprehensive **Student Management System** developed as part of the **BBM 104: Introduction to Programming Laboratory II** course at **Hacettepe University**. The system is designed to handle the storage, processing, and reporting of academic data, including students, faculty members, courses, departments, and academic programs using Java.

## 🚀 Project Overview

The system processes input from multiple text files to create a structured academic environment. It manages relationships between students, faculty members, departments, and courses while ensuring data integrity through custom exception handling.

### Key Features
* **Academic Reporting:** Generates detailed reports for courses (average grades, distribution) and students (GPA, completed courses).
* **OOP Architecture:** Built on the fundamental principles of Object-Oriented Programming: inheritance, encapsulation, polymorphism, and abstraction.
* **Robust Error Handling:** Manages invalid person types, non-existent entities, and invalid letter grades.
* **Grading System:** Implements a 4-point grading system for academic evaluations.

## 🛠 Technical Implementation

### Inheritance & Interfaces
* **Person Hierarchy:** A base `Persons` class extended by student and academic member types.
* **Academic Entities:** `AcademicEntity` base class for departments, programs, and courses.
* **Interface:** Utilization of `AcademicRole` for role-based logic.

### Data Processing
The program reads input data from text files:
* `persons.txt`: Type, ID, Name, Email, Department/Major.
* `departments.txt`: Code, Name, Description, Head.
* `programs.txt`: Code, Name, Description, Department, Degree Level, Total Credits.
* `courses.txt`: Code, Name, Department, Credits, Semester, Program Code.
* `assignments.txt`: Linking people to courses.
* `grades.txt`: Student performance data.

## 💻 Usage

### Prerequisites
* Java 8 (Oracle)

### Compilation and Execution
To run the system on a terminal, use the following commands:

```bash
javac Main.java
java Main persons.txt departments.txt programs.txt courses.txt assignments.txt grades.txt output.txt
```

## 📝 Grading System Reference
The GPA and Course Average calculations follow these standards:

| Letter Grade | 4-Point Value |
|--------------|---------------|
| A1           | 4.00          |
| A2           | 3.50          |
| B1           | 3.00          |
| B2           | 2.50          |
| C1           | 2.00          |
| C2           | 1.50          |
| D1           | 1.00          |
| D2           | 0.50          |
| F3           | 0.00          |

---
*Developed by a Computer Engineering Student at Hacettepe University.*
