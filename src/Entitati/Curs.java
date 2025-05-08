package Entitati;
import Utile.Materie;
import java.util.*;

public class Curs {
    private final UUID id = UUID.randomUUID();
    private String titlu;
    private Materie materie;
    private Tutor tutor;
    private final List<Student> inscrisi = new ArrayList<>();

    public Curs(String t, Materie m, Tutor tu){ titlu=t; materie=m; tutor=tu; }

    public String getTitlu() { return titlu; }
    public Materie getMaterie() { return materie; }
    public Tutor getTutor() { return tutor; }


    public UUID getId(){ return id; }
    public void inscrie(Student s){ inscrisi.add(s); }

}
