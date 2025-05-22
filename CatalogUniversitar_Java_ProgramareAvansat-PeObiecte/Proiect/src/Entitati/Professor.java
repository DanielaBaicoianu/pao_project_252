package Entitati;

public class Professor extends Person {
    private String titluDidactic;
    private Department department;

    public Professor(int idProfesor, String nume, String titluDidactic, Department department) {
        super(idProfesor, nume);
        this.titluDidactic = titluDidactic;
        this.department = department;
    }

    public String getTitluDidactic() {
        return titluDidactic;
    }
    public void setTitluDidactic(String titluDidactic) {
        this.titluDidactic = titluDidactic;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department dpt) {
        this.department = dpt;
    }
}