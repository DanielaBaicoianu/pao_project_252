package Entitati;

import java.time.*;
import java.util.*;

public class Sesiune implements Comparable<Sesiune>{
    private final UUID id = UUID.randomUUID();
    private LocalDateTime start;
    private Curs curs;
    public Sesiune(Curs c, LocalDateTime s){ curs=c; start=s; }
    public UUID getId(){ return id; }
    @Override public int compareTo(Sesiune o){ return start.compareTo(o.start); }

    public Curs getCurs() {
        return curs;
    }

    public String getWhen() {
        return start.toString();
    }
}
