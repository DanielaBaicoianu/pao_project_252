package Entitati;

public class Department {
    private final int idDepartament;
    private final String numeDepartament;
    private final String facultate;

    public Department(int idDepartament, String numeDepartament, String facultate) {
        this.idDepartament = idDepartament;
        this.numeDepartament = numeDepartament;
        this.facultate = facultate;
    }

    public int getIdDepartament() {
        return idDepartament;
    }
    public String getNumeDepartament() {
        return numeDepartament;
    }
    public String getFacultate() {
        return facultate;
    }
}