package Entitati;

import java.util.UUID;

public class Plata {
    private final UUID id = UUID.randomUUID();
    private double suma;
    private Student platitor;
    private Tutor incasator;
    public Plata(Student s, Tutor t, double v){ platitor=s; incasator=t; suma=v; }
}
