import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class StudentManagementSystem {

    private List<Persons> persons;   // All persons can be held at will
    private List<Students> students;
    private List<AcademicMembers> instructors;

    private List<Departments> departments;
    private List<Programs> programs;
    private List<Courses> courses;
    private List<Assignments> assignments;
    private List<Grades> grades;

    private OutputWriter outputWriter;

    public static List<String> outputLines = new ArrayList<>();

    public StudentManagementSystem(List<Persons> persons,List<Departments> departments,
                                   List<Programs> programs,List<Courses> courses, List<Assignments> assignments,
                                   List<Grades> grades, OutputWriter outputWriter) {
        this.persons = persons;
        this.departments = departments;
        this.programs = programs;
        this.courses = courses;
        this.grades = grades;
        this.assignments = assignments;
        this.outputWriter = outputWriter;

        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();

        separatePersonsByType();  // First separate the list
    }

    private void separatePersonsByType() {  // I will use this later(Because I have persons with the same ID)
        for (Persons p : persons) {
            if (p instanceof Students) {
                students.add((Students) p);
            } else if (p instanceof AcademicMembers) {
                instructors.add((AcademicMembers) p);
            }
        }
    }

    public void run() {
        processAssignments();  // Think like a commands.txt
        writeAcademicMembers();
        writeStudents();
        writeDepartments();
        writePrograms();
        writeCourses();
        writeCourseReports();
        writeStudentReports();
    }

    private void writeAcademicMembers() {
        outputLines.add("----------------------------------------");
        outputLines.add("            Academic Members");
        outputLines.add("----------------------------------------");
        for (AcademicMembers academicMember : instructors) {
            outputLines.add("Faculty ID: " + academicMember.getId());
            outputLines.add("Name: " + academicMember.getName());
            outputLines.add("Email: " + academicMember.getEmail());
            outputLines.add("Department: " + academicMember.getDepartment());
            outputLines.add("");
        }
        outputLines.add("----------------------------------------");
        outputLines.add("");
    }

    private void writeStudents() {
        outputLines.add("----------------------------------------");
        outputLines.add("                STUDENTS");
        outputLines.add("----------------------------------------");
        for (Students student : students) {
            outputLines.add("Student ID: " + student.getId());
            outputLines.add("Name: " + student.getName());
            outputLines.add("Email: " + student.getEmail());
            outputLines.add("Major: " + student.getDepartment());
            outputLines.add("Status: Active");
            outputLines.add("");
        }
        outputLines.add("----------------------------------------");
        outputLines.add("");
    }

    private void writeDepartments() {
        outputLines.add("---------------------------------------");
        outputLines.add("              DEPARTMENTS");
        outputLines.add("---------------------------------------");
        for (Departments dept : departments) {
            outputLines.add("Department Code: " + dept.getCode());
            outputLines.add("Name: " + dept.getName());

            int headId = Integer.parseInt(dept.getHead());
            AcademicMembers headPerson = findAcademicMemberById(headId);
            String headName;
            if (headPerson != null) {
                headName = headPerson.getName();
            } else {
                headName = "Not assigned";
            }
            outputLines.add("Head: " + headName);
            outputLines.add("");
            outputLines.add("----------------------------------------");
            outputLines.add("");
        }
    }

    private void writePrograms() {
        outputLines.add("--------------------------------------");
        outputLines.add("                PROGRAMS");
        outputLines.add("---------------------------------------");

        // Sorting programs by name
        programs.sort(Comparator.comparing(Programs::getName));

        for (Programs prog : programs) {
            outputLines.add("Program Code: " + prog.getCode());
            outputLines.add("Name: " + prog.getName());
            outputLines.add("Department: " + prog.getDepartment());
            outputLines.add("Degree Level: " + prog.getDegreeLevel());
            outputLines.add("Required Credits: " + prog.getTotalCredits());

            List<String> courseCodes = new ArrayList<>();
            for (Courses course : courses) {
                if (course.getProgram().equals(prog.getCode())) {
                    courseCodes.add(course.getCode());
                }
            }
            outputLines.add("Courses: {" + String.join(",", courseCodes) + "}");
            // I use String.join for to make a single text
            outputLines.add("");
        }
        outputLines.add("----------------------------------------");
        outputLines.add("");
    }

    private void writeCourses() {
        outputLines.add("---------------------------------------");
        outputLines.add("                COURSES");
        outputLines.add("---------------------------------------");

        courses.sort(Comparator.comparing(Courses::getCode));

        for (Courses course : courses) {
            outputLines.add("Course Code: " + course.getCode());
            outputLines.add("Name: " + course.getName());
            outputLines.add("Department: " + course.getDepartment());
            outputLines.add("Credits: " + course.getCredits());
            outputLines.add("Semester: " + course.getSemester());
            outputLines.add("");
        }
        outputLines.add("----------------------------------------");
        outputLines.add("");
    }

    private void writeCourseReports() {
        outputLines.add("----------------------------------------");
        outputLines.add("             COURSE REPORTS");
        outputLines.add("----------------------------------------");

        // For grade sorting classic list
        List<String> gradeOrder = new ArrayList<>();
        gradeOrder.add("A1");
        gradeOrder.add("A2");
        gradeOrder.add("B1");
        gradeOrder.add("B2");
        gradeOrder.add("C1");
        gradeOrder.add("C2");
        gradeOrder.add("D1");
        gradeOrder.add("D2");
        gradeOrder.add("F");

        for (Courses course : courses) {
            outputLines.add("Course Code: " + course.getCode());
            outputLines.add("Name: " + course.getName());
            outputLines.add("Department: " + course.getDepartment());
            outputLines.add("Credits: " + course.getCredits());
            outputLines.add("Semester: " + course.getSemester());
            outputLines.add("");

            String instructorName;
            if (course.getInstructor() != null) {
                instructorName = course.getInstructor().getName();
            } else {
                instructorName = "Not assigned";
            }
            outputLines.add("Instructor: " + instructorName);
            outputLines.add("");

            outputLines.add("Enrolled Students:");
            for (Students s : course.getEnrolledStudents()) {
                outputLines.add("- " + s.getName() + " (ID: " + s.getId() + ")");
            }
            outputLines.add("");

            // Calculate for grade distribution
            Map<String, Integer> gradeCount = new HashMap<>();
            double totalPoints = 0;
            int countGrades = 0;

            for (String g : gradeOrder) {
                gradeCount.put(g, 0);
            }

            for (Grades grade : grades) {
                if (grade.getCourseCode().equalsIgnoreCase(course.getCode())) {
                    String letter = grade.getLetterGrade();
                    if (gradeCount.containsKey(letter)) {
                        gradeCount.put(letter, gradeCount.get(letter) + 1);
                    } else {
                        gradeCount.put(letter, 1);  // if not available, I also add it
                    }
                    totalPoints += Grades.convertGradeToPoint(letter);
                    countGrades++;
                }
            }

            outputLines.add("Grade Distribution:");
            for (String g : gradeOrder) {
                int cnt = gradeCount.getOrDefault(g, 0);
                if (cnt > 0) {
                    outputLines.add(g + ": " + cnt);
                }
            }
            outputLines.add("");

            double average;
            if (countGrades > 0) {
                average = (totalPoints / countGrades);
            } else {
                average = 0;
            }

            outputLines.add(String.format(java.util.Locale.US, "Average Grade: %.2f", average));
            outputLines.add("");
            outputLines.add("----------------------------------------");
            outputLines.add("");
        }
    }

    private void processAssignments() {
        /*
           Reading the all assignments data
           If the course and person concerned are exist, the instructor assigns the lecture and enrolls
           the student to the course
        */
        for (Assignments assignment : assignments) {
            String type = assignment.getType();
            int personId = assignment.getPersonId();
            String courseCode = assignment.getCourseCode();

            Courses course = findCourseByCode(courseCode);
            if (course == null) {
                continue; // If there is no course keep going
            }

            if (type.equalsIgnoreCase("F")) {
                AcademicMembers instructor = findInstructorById(personId);
                if (instructor != null) {
                    course.setInstructor(instructor);
                }
            } else if (type.equalsIgnoreCase("S")) {
                Students student = findStudentById(personId);
                if (student != null) {
                    course.addStudent(student);
                }
            }
        }
    }

    private void writeStudentReports() {
        outputLines.add("----------------------------------------");
        outputLines.add("            STUDENT REPORTS");
        outputLines.add("----------------------------------------");

        for (Persons person : persons) {
            if (person instanceof Students) {
                Students student = (Students) person;

                outputLines.add("Student ID: " + student.getId());
                outputLines.add("Name: " + student.getName());
                outputLines.add("Email: " + student.getEmail());
                outputLines.add("Major: " + student.getDepartment());
                outputLines.add("Status: Active");
                outputLines.add("");
                outputLines.add("");

                // Only enrolled but not completed courses
                outputLines.add("Enrolled Courses:");
                List<Courses> enrolledNotCompleted = new ArrayList<>();
                for (Courses course : courses) {
                    boolean isEnrolled = course.getEnrolledStudents().contains(student);
                    boolean hasGrade = false;

                    for (Grades grade : grades) {
                        if (grade.getStudentId() == student.getId()) {
                            if (grade.getCourseCode().equalsIgnoreCase(course.getCode())) {
                                hasGrade = true;
                                break;
                            }
                        }
                    }
                    if (isEnrolled && !hasGrade) {
                        enrolledNotCompleted.add(course);
                    }
                }

                if (enrolledNotCompleted.isEmpty()) {
                    outputLines.add("");
                } else {
                    for (Courses c : enrolledNotCompleted) {
                        outputLines.add("- " + c.getName() + " (" + c.getCode() + ")");
                    }
                }
                outputLines.add("");

                // Calculating Completed Courses & GPA

                outputLines.add("Completed Courses:");
                double totalPointsTimesCredits = 0;
                int totalCredits = 0;
                boolean hasCompletedCourses = false;

                for (Grades grade : grades) {
                    if (grade.getStudentId() == student.getId()) {
                        String courseCode = grade.getCourseCode();
                        String courseName = getCourseNameByCode(courseCode);
                        int credits = getCourseCreditsByCode(courseCode);

                        outputLines.add("- " + courseName + " (" + courseCode + "): " + grade.getLetterGrade());

                        double points = Grades.convertGradeToPoint(grade.getLetterGrade());
                        totalPointsTimesCredits += points * credits;
                        totalCredits += credits;

                        hasCompletedCourses = true;
                    }
                }
                if (!hasCompletedCourses) {
                    outputLines.add("");
                }
                outputLines.add("");

                double gpa;
                if (totalCredits > 0) {
                    gpa = totalPointsTimesCredits / totalCredits;
                } else {
                    gpa = 0.0;
                }
                outputLines.add(String.format(java.util.Locale.US, "GPA: %.2f", gpa));
                outputLines.add("----------------------------------------");
                outputLines.add("");
            }
        }
    }

    private String getCourseNameByCode(String code) {
        for (Courses course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course.getName();
            }
        }
        return "Unknown Course";
    }

    private int getCourseCreditsByCode(String code) {
        for (Courses course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course.getCredits();
            }
        }
        return 0;
    }

    private AcademicMembers findInstructorById(int id) {
        for (AcademicMembers a : instructors) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    private Students findStudentById(int id) {
        for (Students s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    private AcademicMembers findAcademicMemberById(int id) {
        for (AcademicMembers instructor : instructors) {
            if (instructor.getId() == id) {
                return instructor;
            }
        }
        return null;
    }

    private Courses findCourseByCode(String code) {
        for (Courses c : courses) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    public List<Students> getStudents() {
        return students;
    }

    public List<AcademicMembers> getInstructors() {
        return instructors;
    }
}
