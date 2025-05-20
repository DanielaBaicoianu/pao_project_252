package Entitati;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Enrollment {
    private final int idInscriere;
    private final Student student;
    private final CourseOffering oferta;
    private final LocalDate dataInscrierii;
    private final List<Grade> note = new ArrayList<>();

    public Enrollment(int idInscriere, Student student,
                      CourseOffering oferta, LocalDate dataInscrierii) {
        this.idInscriere = idInscriere;
        this.student = student;
        this.oferta = oferta;
        this.dataInscrierii = dataInscrierii;
    }

    public void adaugaNota(Grade g) {
        note.add(g);
    }
    public List<Grade> getNote() {
        return note;
    }

    public int getIdInscriere() {
        return idInscriere;
    }
    public Student getStudent() {
        return student;
    }
    public CourseOffering getOferta() {
        return oferta;
    }
}
