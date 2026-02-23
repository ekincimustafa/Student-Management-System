import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AcademicEntityReader {

    public List<Departments> readDepartments(String input, List<Persons> persons) {
        List<Departments> departments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            StudentManagementSystem.outputLines.add("Reading Departments");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                String code = parts[0].trim();
                String name = parts[1].trim();
                String description = parts[2].trim();
                String headIdStr = parts[3].trim();

                try {
                    int headId = Integer.parseInt(headIdStr);
                    // headId’nin gerçekten var olup olmadığını ve academic member olup olmadığını kontrol et
                    Persons headPerson = findAcademicMemberById(persons, headId);
                    if (headPerson == null) {
                        StudentManagementSystem.outputLines.add("Academic Member Not Found with ID " + headId);
                    }

                    departments.add(new Departments(code, name, description, headIdStr));

                } catch (NumberFormatException e) {
                    //
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return departments;
    }

    private Persons findAcademicMemberById(List<Persons> persons, int id) {
        for (Persons p : persons) {
            if (p.getId() == id && p instanceof AcademicRole) {
                AcademicRole academic = (AcademicRole) p;
                if (academic.isAcademicMember())
                    return p;
            }
        }
        return null;
    }

    public List<Programs> readPrograms(String input) {
        List<Programs> programs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            StudentManagementSystem.outputLines.add("Reading Programs");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6);
                if (parts.length == 6) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    String department = parts[3].trim();
                    String degreeLevel = parts[4].trim();
                    try {
                        int totalCredits = Integer.parseInt(parts[5].trim());
                        programs.add(new Programs(code, name, description, department, degreeLevel, totalCredits));
                    } catch (NumberFormatException e) {
                        StudentManagementSystem.outputLines.add("Invalid total credit format in line: " + line);
                    }
                } else {
                    StudentManagementSystem.outputLines.add("Invalid data format in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return programs;
    }

    public List<Courses> readCourses(String input, List<Programs> validPrograms) {
        List<Courses> courses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            StudentManagementSystem.outputLines.add("Reading Courses");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6);
                if (parts.length == 6) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String department = parts[2].trim();
                    try {
                        int credits = Integer.parseInt(parts[3].trim());
                        String semester = parts[4].trim();
                        String program = parts[5].trim();

                        // ✅ PROGRAM VAR MI DİYE KONTROL ET
                        boolean programExists = validPrograms.stream()
                                .anyMatch(p -> p.getCode().equals(program));

                        if (!programExists) {
                            StudentManagementSystem.outputLines.add("Program " + program + " Not Found");
                            continue; // Bu course'u atla
                        }

                        courses.add(new Courses(code, name, department, credits, semester, program));
                    } catch (NumberFormatException e) {
                        StudentManagementSystem.outputLines.add("Invalid credit format in line: " + line);
                    }
                } else {
                    StudentManagementSystem.outputLines.add("Invalid data format in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }


}
