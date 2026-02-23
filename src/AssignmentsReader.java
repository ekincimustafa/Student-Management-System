import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentsReader {

    public List<Assignments> readAssignments(String filePath, List<Persons> persons, List<Courses> courses) {
        List<Assignments> assignments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StudentManagementSystem.outputLines.add("Reading Course Assignments");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);

                String personType = parts[0].trim(); // "S" or "F"
                int personId = Integer.parseInt(parts[1].trim());
                String courseCode = parts[2].trim();

                Persons person = findPersonByIdAndType(persons, personId,personType);
                Courses course = findCourseByCode(courses, courseCode);

                // Person Control
                if (person == null) {
                    if (personType.equals("S")) {
                        StudentManagementSystem.outputLines.add("Student Not Found with ID " + personId);
                    } else if (personType.equals("F")) {
                        StudentManagementSystem.outputLines.add("Academic Member Not Found with ID " + personId);
                    } else {
                        StudentManagementSystem.outputLines.add("Invalid person type: " + personType);
                    }
                    continue;
                }

                // Type Control
                if (!person.getType().equals(personType)) {
                    if (personType.equals("S")) {
                        StudentManagementSystem.outputLines.add("Student Not Found with ID " + personId);
                    } else if (personType.equals("F")) {
                        StudentManagementSystem.outputLines.add("Academic Member Not Found with ID " + personId);
                    } else {
                        StudentManagementSystem.outputLines.add("Invalid person type: " + personType);
                    }
                    continue;
                }

                // Course Control
                if (course == null) {
                    StudentManagementSystem.outputLines.add("Course " + courseCode + " Not Found");
                    continue;
                }

                // if all the things are okay then add it
                assignments.add(new Assignments(personType, personId, courseCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    private Persons findPersonByIdAndType(List<Persons> persons, int id, String type) {
        for (Persons p : persons) {
            if (p.getId() == id && p.getType().equals(type)) {
                return p;
            }
        }
        return null;
    }

    private Courses findCourseByCode(List<Courses> courses, String code) {
        for (Courses c : courses) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}