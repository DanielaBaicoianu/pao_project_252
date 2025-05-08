package Servicii;

import Entitati.*;
import Repositories.*;
import Utile.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

public class ServiciuPlatforma {

    /* 1  inregistrare Student */
    public Student inregistreazaStudent(String n, String e, String p) {
        Student s = new Student(n, e, p);
        UserRepo.USERS.put(s.getId(), s);
        System.out.println(Actiuni.LOG + "1: student înregistrat");
        return s;
    }

    /* 2  inregistrare Tutor (cu materie) */
    public Tutor inregistreazaTutor(String n, String e, String p,
                                    double tarif, Materie materie) {
        Tutor tu = new Tutor(n, e, p, tarif, materie);
        UserRepo.USERS.put(tu.getId(), tu);
        System.out.println(Actiuni.LOG + "2: tutor înregistrat (Materie: " + materie + ")");
        return tu;
    }

    /* 3  login */
    public Utilizator login(String email, String pass) {
        return UserRepo.USERS.values().stream()
                .filter(u -> u.getEmail().equals(email) && u.checkPass(pass))
                .findFirst()
                .orElse(null);
    }

    /* 4  creare curs si legare la tutor */
    public Curs creeazaCurs(String titlu, Materie m, Tutor t) {
        Curs c = new Curs(titlu, m, t);
        CursRepo.CURSURI.add(c);
        t.adaugaCurs(c);                       // tutorul stie ce curs preda
        System.out.println(Actiuni.LOG + "4: curs creat");
        return c;
    }

    /* 5  inscrie student */
    public void inscrieStudent(UUID cursId, Student s) {
        CursRepo.CURSURI.stream()
                .filter(c -> c.getId().equals(cursId))
                .findFirst()
                .ifPresent(c -> c.inscrie(s));
    }

    /* 6  programeaza sesiune */
    public Sesiune programeaza(UUID cursId, LocalDateTime when) {
        Curs c = CursRepo.CURSURI.stream()
                .filter(x -> x.getId().equals(cursId))
                .findFirst()
                .orElse(null);
        Sesiune se = new Sesiune(c, when);
        SesiuneRepo.SESIUNI.add(se);
        return se;
    }

    /* 7  anuleaza sesiune */
    public void anuleaza(UUID sesiuneId) {
        SesiuneRepo.SESIUNI.removeIf(s -> s.getId().equals(sesiuneId));
    }

    /* 8  listare tutori (detaliat) */
    public void listeazaTutori() {
        UserRepo.USERS.values().stream()
                .filter(u -> u instanceof Tutor)
                .map(u -> (Tutor) u)
                .forEach(t -> System.out.println(
                        "Nume: " + t.getNume() +
                                ", Materie: " + t.getMaterie() +
                                ", Tarif/h: " + t.getTarifOra() +
                                ", Cursuri: " + (t.getCursuri().isEmpty()
                                ? "-" :
                                t.getCursuri().stream()
                                        .map(Curs::getTitlu)
                                        .collect(Collectors.joining("; ")))
                ));
    }

    /* 9  plata */
    public Plata plateste(Student s, Tutor t, double suma) {
        return new Plata(s, t, suma);
    }

    /* 10 feedback */
    public void feedback(Student s, Tutor t, int st, String txt) {
        Feedback f = new Feedback(s, t, st, txt);
        SesiuneRepo.CACHE[0] = f;   // simpla stocare pentru demo
    }
}
