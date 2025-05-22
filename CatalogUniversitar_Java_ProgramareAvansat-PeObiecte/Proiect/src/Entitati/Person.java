package Entitati;

public abstract class Person {
    protected final int id;
    protected final String nume;

    public Person(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }
    public String getNume() {
        return nume;
    }

    @Override public String toString() {
        return nume + " (id=" + id + ")";
    }
}