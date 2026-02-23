import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonsReader {

    public List<Persons> readPersons(String input) {
        List<Persons> persons = new ArrayList<>();
        boolean control = true;

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    String type = parts[0].trim();
                    int id = Integer.parseInt(parts[1].trim());
                    String name = parts[2].trim();
                    String email = parts[3].trim();
                    String department = parts[4].trim();

                    Persons person;

                    switch (type) {
                        case "S":
                            person = new Students(id, name, email, department,type);
                            break;
                        case "F":
                            person = new AcademicMembers(id, name, email, department,type);
                            break;
                        default:
                            throw new CustomExceptions.InvalidPersonTypeException("Invalid Person Type");
                    }

                    persons.add(person);

                    if (control) {
                        StudentManagementSystem.outputLines.add("Reading Person Information");
                        control = false;
                    }

                } catch (CustomExceptions.InvalidPersonTypeException e) {
                    StudentManagementSystem.outputLines.add("Invalid Person Type");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }
}
