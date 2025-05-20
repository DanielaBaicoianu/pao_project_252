package Servicii;

import Entitati.*;
import Repositories.*;
import Utile.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class CatalogService {
    private static CatalogService instance;

    private final CourseRepo courseRepo = CourseRepo.getInstance();
    private final CourseOfferingRepo offeringRepo = CourseOfferingRepo.getInstance();
    private final Map<Integer, Student> studenti = new HashMap<>();
    private final Map<Integer, Professor> profesori = new HashMap<>();
    private final Map<Integer, Enrollment> inscrieri = new HashMap<>();

    private final AuditService audit = AuditService.getInstance();

    private CatalogService() {}

    public static CatalogService getInstance() {
        if (instance == null) instance = new CatalogService();
        return instance;
    }

    // 1. Înscriere student la curs
    public Enrollment inscriereStudentLaCurs(int idStudent, int idOferta) {
        Student s = studenti.get(idStudent);
        CourseOffering o = offeringRepo.get(idOferta);
        Enrollment e = new Enrollment(IdGenerator.generareId(), s, o, LocalDate.now());
        inscrieri.put(e.getIdInscriere(), e);
        s.adaugaInscriere(e);
        audit.log("inscriere_student_la_curs");
        return e;
    }

    // 2. Retragere student din curs
    public void retragereStudentDinCurs(int idInscriere) {
        Enrollment e = inscrieri.remove(idInscriere);
        if (e != null) e.getStudent().stergeInscriere(e);
        audit.log("retragere_student_din_curs");
    }

    // 3. Listare cursuri disponibile pentru un semestru
    public List<CourseOffering> listareCursuri(int sem, int an) {
        audit.log("listare_cursuri_semestru");
        return offeringRepo.all().values().stream()
                .filter(o -> o.getSemestru()==sem && o.getAn()==an)
                .collect(Collectors.toList());
    }

    // 4. Atribuire profesor la curs
    public void atribuireProfesorLaCurs(int idProfesor, int idOferta) {
        CourseOffering o = offeringRepo.get(idOferta);
        o.setProfesor(profesori.get(idProfesor));
        audit.log("atribuire_profesor_la_curs");
    }

    // 5. Adăugare materie în catalog
    public void adaugaMaterie(Course c) {
        courseRepo.add(c);
        audit.log("adaugare_materie");
    }

    // 6. Modificare informații materie
    public void modificaMaterie(String cod, int crediteNoi) {
        Course c = courseRepo.get(cod);
        if (c != null) c.setCredite(crediteNoi);
        audit.log("modificare_materie");
    }

    // 7. Vizualizare studenți înscriși la un curs
    public List<Student> vizualizareStudentiCurs(int idOferta) {
        audit.log("vizualizare_studenti_curs");
        return inscrieri.values().stream()
                .filter(e -> e.getOferta().getIdOferta()==idOferta)
                .map(Enrollment::getStudent)
                .collect(Collectors.toList());
    }

    // 8. Înregistrare notă pentru student
    public void inregistrareNota(int idInscriere, double valoareNota) {
        Enrollment e = inscrieri.get(idInscriere);
        if (e != null) {
            e.adaugaNota(new Grade(IdGenerator.generareId(), e, valoareNota));
            audit.log("inregistrare_nota");
        }
    }

    // 9. Listare note & medie student
    public List<Grade> noteStudent(int idStudent) {
        audit.log("listare_note_student");
        return new ArrayList<>(studenti.get(idStudent).getNote());
    }
    public double medieStudent(int idStudent) {
        audit.log("medie_student");
        return studenti.get(idStudent).calculeazaMedie();
    }

    // 10. Căutare cursuri după departament
    public List<Course> cautaCursuriDepartament(String numeDept) {
        audit.log("cautare_cursuri_departament");
        return courseRepo.getAll().stream()
                .filter(c -> c.getDepartment().getNumeDepartament().equalsIgnoreCase(numeDept))
                .collect(Collectors.toList());
    }

    // metode auxiliare
    public void adaugaStudent(Student s) {
        studenti.put(s.getId(), s);
    }
    public void adaugaProfesor(Professor p) {
        profesori.put(p.getId(), p);
    }
    public void adaugaOferta(CourseOffering o) {
        offeringRepo.add(o);
    }
}
