import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradesReader {

    public List<Grades> readGrades(String filePath, List<Persons> persons, List<Courses> courses) {
        List<Grades> gradesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StudentManagementSystem.outputLines.add("Reading Grades");

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 3);

                String letterGrade = parts[0].trim();
                int studentId;
                String courseCode = parts[2].trim();

                studentId = Integer.parseInt(parts[1].trim());

                Persons student = findPersonById(persons, studentId);
                Courses course = findCourseByCode(courses, courseCode);

                // Student Control
                if (student == null || !student.getType().equals("S")) {
                    StudentManagementSystem.outputLines.add("Student Not Found with ID " + studentId);
                    continue;
                }

                // Course Control
                if (course == null) {
                    StudentManagementSystem.outputLines.add("Course " + courseCode + " Not Found");
                    continue;
                }

                // Letter grade control
                if (!Grades.isValidLetterGrade(letterGrade)) {
                    StudentManagementSystem.outputLines.add("The grade " + letterGrade + " is not valid");
                    continue;
                }

                // if it is valid then add it
                gradesList.add(new Grades(letterGrade, studentId, courseCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gradesList;
    }

    private Persons findPersonById(List<Persons> persons, int id) {
        for (Persons p : persons) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private Courses findCourseByCode(List<Courses> courses, String code) {
        for (Courses c : courses) {
            if (c.getCode().equals(code)) return c;
        }
        return null;
    }
}