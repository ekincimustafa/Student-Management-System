import java.util.*;

public class Main {
    public static void main(String[] args) {

        PersonsReader personsReader = new PersonsReader();
        List<Persons> persons = personsReader.readPersons(args[0]);

        AcademicEntityReader entityReader = new AcademicEntityReader();
        List<Departments> departments = entityReader.readDepartments(args[1],persons);
        List<Programs> programs = entityReader.readPrograms(args[2]);
        List<Courses> courses = entityReader.readCourses(args[3],programs);

        AssignmentsReader assignmentsReader = new AssignmentsReader();
        List<Assignments> assignments = assignmentsReader.readAssignments(args[4],persons,courses);

        GradesReader gradesReader = new GradesReader();
        List<Grades> grades = gradesReader.readGrades(args[5],persons,courses);

        OutputWriter outputWriter = new OutputWriter(args[6]);

        StudentManagementSystem sms = new StudentManagementSystem(persons,departments, programs,
                                                        courses,assignments,grades,outputWriter);
        sms.run();

        outputWriter.writeOutput(StudentManagementSystem.outputLines);
    }
}
