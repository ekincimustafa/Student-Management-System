import java.util.ArrayList;
import java.util.List;

public class AcademicEntity {
    private String code;
    private String name;
    private String description;

    public AcademicEntity(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
}

class Departments extends AcademicEntity {
    private String head;

    public Departments(String code, String name, String description,String head) {
        super(code, name, description);
        this.head = head;
    }

    public String getHead() {
        return head;
    }
}

class Programs extends AcademicEntity {
    private String department;
    private String degreeLevel;
    private int totalCredits;

    public Programs(String code, String name, String description, String department, String degreeLevel, int totalCredits) {
        super(code, name, description);
        this.department = department;
        this.degreeLevel = degreeLevel;
        this.totalCredits = totalCredits;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDegreeLevel() {
        return degreeLevel;
    }

    public int getTotalCredits() {
        return totalCredits;
    }
}

class Courses extends AcademicEntity {
    private int credits;
    private String semester;
    private String department;
    private String program;

    private AcademicMembers instructor;
    private List<Students> enrolledStudents = new ArrayList<>();

    public Courses(String code, String name, String department, int credits, String semester, String program) {
        super(code, name, null);  // description not using, we can use null
        this.department = department;
        this.credits = credits;
        this.semester = semester;
        this.program = program;
    }

    public AcademicMembers getInstructor() {
        return instructor;
    }

    public void setInstructor(AcademicMembers instructor) {
        this.instructor = instructor;
    }

    public List<Students> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Students student) {
        enrolledStudents.add(student);
    }

    public int getCredits() {
        return credits;
    }

    public String getSemester() {
        return semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProgram() {
        return program;
    }
}
