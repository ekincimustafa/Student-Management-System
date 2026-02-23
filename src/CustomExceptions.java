public class CustomExceptions {

    public static class InvalidPersonTypeException extends Exception {
        public InvalidPersonTypeException(String message) {
            super(message);
        }
    }

    public static class NonExistentStudentException extends Exception {
        public NonExistentStudentException(String message) {
            super(message);
        }
    }

    public static class NonExistentAcademicMemberException extends Exception {
        public NonExistentAcademicMemberException(String message) {
            super(message);
        }
    }

    public static class NonExistentDepartmentException extends Exception {
        public NonExistentDepartmentException(String message) {
            super(message);
        }
    }

    public static class NonExistentProgramException extends Exception {
        public NonExistentProgramException(String message) {
            super(message);
        }
    }

    public static class NonExistentCourseException extends Exception {
        public NonExistentCourseException(String message) {
            super(message);
        }
    }

    public static class InvalidLetterGradeException extends Exception {
        public InvalidLetterGradeException(String message) {
            super(message);
        }
    }
}
