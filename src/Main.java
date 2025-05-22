
import Servicii.ServiciuPlatforma;
import Entitati.*;
import Repositories.*;
import Utile.Materie;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Utile.Input.*;
import static Utile.UiUtils.*;
public class Main {
    /**  resurse globale */
    private static final Scanner sc = new Scanner(System.in);
    private static final ServiciuPlatforma service = new ServiciuPlatforma();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            afiseazaMeniu();
            int opt = citesteInt("Alege o optiune: ");
            switch (opt) {
                case 1  -> inregistreazaStudent();
                case 2  -> inregistreazaTutor();
                case 3  -> login();
                case 4  -> creeazaCurs();
                case 5  -> inscriereLaCurs();
                case 6  -> programeazaSesiune();
                case 7  -> anuleazaSesiune();
                case 8  -> service.listeazaTutori();
                case 9  -> plateste();
                case 10 -> feedback();
                case 0  -> { running = false; System.out.println("Salut!"); }
                default -> System.out.println("Optiune invalida!");
            }
        }
        sc.close();
    }

    /** ================= MENIU ================= */
    private static void afiseazaMeniu() {
        System.out.println("\n=== Meniu Platforma Educationala ===");
        System.out.println("1. Inregistrare Student");
        System.out.println("2. Inregistrare Tutor");
        System.out.println("3. Login");
        System.out.println("4. Creare Curs");
        System.out.println("5. Inscriere Student la Curs");
        System.out.println("6. Programeaza Sesiune");
        System.out.println("7. Anuleaza Sesiune");
        System.out.println("8. Listeaza Tutori");
        System.out.println("9. Plateste");
        System.out.println("10. Feedback");
        System.out.println("0. Iesire");
    }

    /* ============== OPTIUNI ============== */
    private static void inregistreazaStudent() {
        Student s = service.inregistreazaStudent(citeste("Nume student:"), citeste("Email:"), citeste("Parola:"));
        System.out.println("Student creat: " + s.getNume());
    }

    private static void inregistreazaTutor() {
        String nume  = citeste("Nume tutor:");
        String email = citeste("Email:");
        String pass  = citeste("Parola:");
        double tarif = citesteDouble("Tarif orar:");
        Materie materie = alegeMaterie("Materie predata");
        Tutor t = service.inregistreazaTutor(nume, email, pass, tarif, materie);
        System.out.println("Tutor creat: " + labelFor(t));
    }

    private static void login() {
        Utilizator u = service.login(citeste("Email:"), citeste("Parola:"));
        System.out.println(u != null ? "Login reusit: " + u.getNume() : "Date invalide!");
    }

    private static void creeazaCurs() {
        String titlu   = citeste("Titlu curs:");
        Materie materie = alegeMaterie("Materie curs");
        List<Tutor> tutori = obtineTutori().stream()
                .filter(t -> t.getMaterie() == materie)
                .collect(Collectors.toList());
        if (tutori.isEmpty()) { System.out.println("Nu exista tutor pentru materia aleasa!"); return; }
        Tutor tutor = alegeDinLista(tutori, "Alege tutor");
        if (tutor == null) return;
        Curs c = service.creeazaCurs(titlu, materie, tutor);
        System.out.println("Curs creat: " + labelFor(c));
    }

    private static void inscriereLaCurs() {
        Student st = alegeDinLista(obtineStudenti(), "Alege student");
        if (st == null) return;
        Curs c = alegeDinLista(CursRepo.CURSURI, "Alege curs");
        if (c == null) return;
        service.inscrieStudent(c.getId(), st);
        System.out.println(st.getNume() + " Inscris la " + c.getTitlu());
    }

    private static void programeazaSesiune() {
        Curs c = alegeDinLista(CursRepo.CURSURI, "Alege curs pentru sesiune");
        if (c == null) return;
        try {
            LocalDateTime when = LocalDateTime.parse(citeste("Data si ora (yyyy-MM-ddTHH:mm) Exemplu format: 2025-05-07T14:30\n:"));
            Sesiune s = service.programeaza(c.getId(), when);
            System.out.println("Sesiune programata pentru " + c.getTitlu() + " la " + when);
        } catch (Exception e) { System.out.println("Format data/ora invalid!"); }
    }

    private static void anuleazaSesiune() {
        Sesiune s = alegeDinLista(SesiuneRepo.SESIUNI.stream().toList(), "Alege sesiune de anulat");
        if (s == null) return;
        service.anuleaza(s.getId());
        System.out.println("Sesiunea pentru " + s.getCurs().getTitlu() + " a fost anulata.");
    }

    private static void plateste() {
        Student st = alegeDinLista(obtineStudenti(), "Alege student");
        Tutor   t  = alegeDinLista(obtineTutori(),   "Alege tutor");
        if (st == null || t == null) return;
        double suma = citesteDouble("Suma plata:");
        System.out.println("Plată creata: " + service.plateste(st, t, suma));
    }

    private static void feedback() {
        Student st = alegeDinLista(obtineStudenti(), "Alege student");
        Tutor   t  = alegeDinLista(obtineTutori(),   "Alege tutor");
        if (st == null || t == null) return;
        int stele = citesteInt("Stelă (1-5):");
        service.feedback(st, t, stele, citeste("Comentariu:"));
        System.out.println("Feedback inregistrat.");
    }
}
