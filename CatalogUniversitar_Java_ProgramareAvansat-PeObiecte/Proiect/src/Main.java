import Entitati.*;
import Servicii.CatalogService;
import Utile.IdGenerator;

import java.time.LocalDate;
import java.util.*;


public class Main {

    private static final Set<Integer> studentIds = new HashSet<>();
    private static final Set<Integer> professorIds = new HashSet<>();
    private static final Set<Integer> offeringIds = new HashSet<>();
    private static final Set<Integer> enrollmentIds = new HashSet<>();

    private static final CatalogService catalog = CatalogService.getInstance();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        populateDemo();

        while (true) {
            afiseazaMeniu();
            int opt = citesteInt("Alegeți opțiunea: ");

            switch (opt) {
                case 0  -> { System.out.println("La revedere!"); return; }
                case 1  -> actionInscriereStudent();
                case 2  -> actionRetragereStudent();
                case 3  -> actionListareCursuriSemestru();
                case 4  -> actionAtribuireProfesor();
                case 5  -> actionAdaugareMaterie();
                case 6  -> actionModificareMaterie();
                case 7  -> actionVizualizareStudenti();
                case 8  -> actionInregistrareNota();
                case 9  -> actionNoteSiMedieStudent();
                case 10 -> actionCautareCursuriDept();
                default -> System.out.println("Opțiune invalidă!");
            }
            System.out.println("\n──────────────────────────────────────\n");
        }
    }

    /* ===================== MENIU  ============================= */
    private static void afiseazaMeniu() {
        System.out.println("""
                ========= Catalog Universitar =========
                0. Ieșire
                1. Înscriere student la curs
                2. Retragere student din curs
                3. Listare cursuri disponibile (semestru + an)
                4. Atribuire profesor la curs
                5. Adăugare materie în catalog
                6. Modificare informații materie
                7. Vizualizare studenți înscriși la un curs
                8. Înregistrare notă pentru student
                9. Listare note și medie student
               10. Căutare cursuri după departament
               =========================================""");
    }

    /* ================== ACȚIUNI  ============================== */

    //  1
    private static void actionInscriereStudent() {
        int idStud = citesteIdValid("Id student: ", studentIds, "Nu există acest student! Introduceți alt ID: ");
        int idOf = citesteIdValid("Id ofertă curs: ", offeringIds, "Nu există oferta! Introduceți alt ID: ");

        Enrollment e = catalog.inscriereStudentLaCurs(idStud, idOf);
        enrollmentIds.add(e.getIdInscriere());
        System.out.println("Înscriere realizată: id=" + e.getIdInscriere());
    }

    //  2
    private static void actionRetragereStudent() {
        int idIns = citesteIdValid("Id înscriere: ", enrollmentIds, "Nu există înscrierea! Introduceți alt ID: ");
        catalog.retragereStudentDinCurs(idIns);
        enrollmentIds.remove(idIns);
        System.out.println("Înscriere anulată.");
    }

    //  3
    private static void actionListareCursuriSemestru() {
        int sem = citesteInt("Semestru (1/2): ");
        int an = citesteInt("An universitar (1/2/3): ");
        List<CourseOffering> list = catalog.listareCursuri(sem, an);
        list.forEach(System.out::println);
    }

    //  4
    private static void actionAtribuireProfesor() {
        int idProf = citesteIdValid("Id profesor: ", professorIds, "Nu există profesorul! Introduceți alt ID: ");
        int idOf = citesteIdValid("Id ofertă curs: ", offeringIds, "Nu există oferta! Introduceți alt ID: ");
        catalog.atribuireProfesorLaCurs(idProf, idOf);
        System.out.println("Profesor atribuit.");
    }

    //  5
    private static void actionAdaugareMaterie() {
        String cod = citesteStr("Cod materie: ");
        String nume = citesteStr("Denumire: ");
        int credite = citesteInt("Credite: ");
        String dept = citesteStr("Departament: ");
        Department d = new Department(IdGenerator.generareId(), dept, "FMI");
        Course c = new Course(cod, nume, credite, d);
        catalog.adaugaMaterie(c);
        System.out.println("Materie adăugată.");
    }

    //  6
    private static void actionModificareMaterie() {
        String cod = citesteStr("Cod materie: ");
        int credite = citesteInt("Număr credite nou: ");
        catalog.modificaMaterie(cod, credite);
        System.out.println("Actualizat.");
    }

    //  7
    private static void actionVizualizareStudenti() {
        int idOf = citesteIdValid("Id ofertă curs: ", offeringIds, "Nu există oferta! Introduceți alt ID: ");
        catalog.vizualizareStudentiCurs(idOf).forEach(System.out::println);
    }

    //  8
    private static void actionInregistrareNota() {
        int idIns = citesteIdValid("Id înscriere: ", enrollmentIds, "Nu există înscrierea! Introduceți alt ID: ");
        double nota = citesteDouble("Valoare notă: ");
        catalog.inregistrareNota(idIns, nota);
        System.out.println("Notă adăugată.");
    }

    //  9
    private static void actionNoteSiMedieStudent() {
        int idStud = citesteIdValid("Id student: ", studentIds, "Nu există acest student! Introduceți alt ID: ");
        catalog.noteStudent(idStud).forEach(g -> System.out.println("Notă: " + g.getValoareNota()));
        System.out.printf("Media: %.2f%n", catalog.medieStudent(idStud));
    }

    //  10
    private static void actionCautareCursuriDept() {
        String dept = citesteStr("Nume departament: ");
        catalog.cautaCursuriDepartament(dept).forEach(System.out::println);
    }

    // VALIDAREA ID-ului
    private static int citesteIdValid(String msg, Set<Integer> set, String msgNuExista) {
        int id = citesteInt(msg);
        while (!set.contains(id)) {
            System.out.print(msgNuExista);
            id = citesteInt("");
        }
        return id;
    }

    // HELPERE I/O
    private static int citesteInt(String msg) {
        System.out.print(msg);
        while (!in.hasNextInt()) { in.next(); System.out.print("Introduceți un număr întreg: "); }
        return in.nextInt();
    }
    private static double citesteDouble(String msg) {
        System.out.print(msg);
        while (!in.hasNextDouble()) { in.next(); System.out.print("Introduceți un număr: "); }
        return in.nextDouble();
    }
    private static String citesteStr(String msg) {
        System.out.print(msg);
        in.nextLine();
        return in.nextLine();
    }

    // DATE DEMO
    private static void populateDemo() {

        // Departamente
        Department info = new Department(1, "Informatica", "FMI");
        Department mate = new Department(2, "Matematica", "FMI");
        Department fizica = new Department(3, "Fizica", "Facultatea de Fizica");

        // Cursuri
        Course cProg = new Course("CS101", "Programare", 6, info);
        Course cAlgo = new Course("CS102", "Algoritmi", 5, info);
        Course cAMat = new Course("MA201", "Analiza Matematica", 5, mate);
        Course cEDif = new Course("MA202", "Ecuatii Diferentiale", 4, mate);
        Course cFiz1 = new Course("PH101", "Fizica I", 6, fizica);

        // Înregistrăm cursurile
        List.of(cProg, cAlgo, cAMat, cEDif, cFiz1).forEach(catalog::adaugaMaterie);

        // Profesori
        Professor profIonescu = new Professor(IdGenerator.generareId(), "Cristian Ionescu", "Lect. univ. dr.", info); //id 1
        Professor profNazarov = new Professor(IdGenerator.generareId(), "Maria Nazarov", "Conf. univ. dr.", mate); //id 2
        Professor profGheorghe = new Professor(IdGenerator.generareId(), "Matei Gheorghe", "Prof. univ. dr.", fizica); //id 3

        List.of(profIonescu, profNazarov, profGheorghe).forEach(p -> {
            catalog.adaugaProfesor(p);
            professorIds.add(p.getId());
        });

        // Oferte de curs (semestru/an)
        CourseOffering offProg = new CourseOffering(IdGenerator.generareId(), cProg, 1, 1); //id 4
        CourseOffering offAlgo = new CourseOffering(IdGenerator.generareId(), cAlgo, 2, 2); //id 5
        CourseOffering offAMat = new CourseOffering(IdGenerator.generareId(), cAMat, 1, 2); //id 6
        CourseOffering offEDif = new CourseOffering(IdGenerator.generareId(), cEDif, 2, 1); //id 7
        CourseOffering offFiz1 = new CourseOffering(IdGenerator.generareId(), cFiz1, 1, 3); //id 8

        List.of(offProg, offAlgo, offAMat, offEDif, offFiz1).forEach(o -> {
            catalog.adaugaOferta(o);
            offeringIds.add(o.getIdOferta());
        });

        // Atribuim profesori la unele cursuri
        catalog.atribuireProfesorLaCurs(profIonescu.getId(), offProg.getIdOferta());
        catalog.atribuireProfesorLaCurs(profIonescu.getId(), offAlgo.getIdOferta());
        catalog.atribuireProfesorLaCurs(profNazarov.getId(), offAMat.getIdOferta());
        catalog.atribuireProfesorLaCurs(profNazarov.getId(), offEDif.getIdOferta());
        catalog.atribuireProfesorLaCurs(profGheorghe.getId(), offFiz1.getIdOferta());

        // Studenți
        Student sAna = new Student(IdGenerator.generareId(), "Popa Ana", LocalDate.of(2004, 5,10), "Informatică"); //id 9
        Student sBogdan = new Student(IdGenerator.generareId(), "Marin Bogdan", LocalDate.of(2003,11,21), "Matematică"); //id 10
        Student sCarmen = new Student(IdGenerator.generareId(), "Preda Carmen", LocalDate.of(2004, 2, 2), "Fizică"); //id 11

        List.of(sAna, sBogdan, sCarmen).forEach(s -> {
            catalog.adaugaStudent(s);
            studentIds.add(s.getId());
        });

        // Înscrieri + Note
        
        // Ana la Programare și Algoritmi
        Enrollment eAnaProg = catalog.inscriereStudentLaCurs(sAna.getId(), offProg.getIdOferta()); //id 12
        enrollmentIds.add(eAnaProg.getIdInscriere());
        catalog.inregistrareNota(eAnaProg.getIdInscriere(), 9.5); //id 13
        catalog.inregistrareNota(eAnaProg.getIdInscriere(), 8.0); //id 14

        Enrollment eAnaAlgo = catalog.inscriereStudentLaCurs(sAna.getId(), offAlgo.getIdOferta()); //id 15
        enrollmentIds.add(eAnaAlgo.getIdInscriere());
        catalog.inregistrareNota(eAnaAlgo.getIdInscriere(), 10); //id 16

        // Bogdan la Programare
        Enrollment eBogdanProg = catalog.inscriereStudentLaCurs(sBogdan.getId(), offProg.getIdOferta()); //id 17
        enrollmentIds.add(eBogdanProg.getIdInscriere());

        // Carmen la Analiză și Fizică I
        Enrollment eCarmenAMat = catalog.inscriereStudentLaCurs(sCarmen.getId(), offAMat.getIdOferta()); //id 18
        enrollmentIds.add(eCarmenAMat.getIdInscriere());
        catalog.inregistrareNota(eCarmenAMat.getIdInscriere(), 7.5); //id 19

        Enrollment eCarmenFiz = catalog.inscriereStudentLaCurs(sCarmen.getId(), offFiz1.getIdOferta()); //id 20
        enrollmentIds.add(eCarmenFiz.getIdInscriere());
    }

}
