public class Grades {

    private String letterGrade;
    private int studentId;
    private String courseCode;

    public Grades(String letterGrade, int studentId, String courseCode) {
        this.letterGrade = letterGrade;
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public static double convertGradeToPoint(String letterGrade) {
        switch (letterGrade) {
            case "A1": return 4.0;
            case "A2": return 3.5;
            case "B1": return 3.0;
            case "B2": return 2.5;
            case "C1": return 2.0;
            case "C2": return 1.5;
            case "D1": return 1.0;
            case "D2": return 0.5;
            case "F3": return 0.0;
            default: return 0.0;
        }
    }

    public static boolean isValidLetterGrade(String grade) {
        String[] validGrades = {"A1", "A2", "B1", "B2", "C1","C2", "D1", "D2","F"};
        for (String g : validGrades) {
            if (g.equals(grade)) return true;
        }
        return false;
    }
}
