package Entitati;

import java.util.UUID;

public class Abonament {
    private final UUID id = UUID.randomUUID();
    private Student student;
    private Tutor   tutor;
    private int     ore;

    public Abonament(Student s, Tutor t, int ore) {
        this.student = s;
        this.tutor   = t;
        this.ore     = ore;
    }

    public UUID   getId()   { return id; }
    public int    getOre()  { return ore; }
}
