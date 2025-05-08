package Entitati;
import java.util.UUID;
public class Feedback {
    private final UUID id = UUID.randomUUID();
    private int stele;
    private String comentariu;
    private Student from;
    private Tutor to;


    public Feedback(Student f,Tutor t,int s,String c){ from=f;to=t;stele=s;comentariu=c; }
}
