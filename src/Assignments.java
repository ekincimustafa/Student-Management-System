public class Assignments {

    private String type;  // "S" or "F"
    private int personId;
    private String courseCode;

    public Assignments(String type, int personId, String courseCode) {
        this.type = type;
        this.personId = personId;
        this.courseCode = courseCode;
    }

    public String getType() {
        return type;
    }

    public int getPersonId() {
        return personId;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
