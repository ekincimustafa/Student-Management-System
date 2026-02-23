
public abstract class Persons {

    private int id;
    private String name;
    private String email;
    private String department;
    private String type;

    public Persons(int id, String name, String email, String department,String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    //public abstract boolean isAcademicMember();

    //public abstract boolean isStudent();
}

class Students extends Persons implements StudentRole {
    public Students(int id, String name, String email, String department,String type) {
        super(id, name, email, department, type);
    }

    /*
    @Override
    public boolean isAcademicMember() {
        return false;
    }
     */

    @Override
    public boolean isStudent() {
        return true;
    }
}

class AcademicMembers extends Persons implements AcademicRole {
    public AcademicMembers(int id, String name, String email, String department,String type) {
        super(id, name, email, department,type);
    }

    @Override
    public boolean isAcademicMember() {
        return true;
    }
    /*
    @Override
    public boolean isStudent() {
        return false;
    }*/
}