package Utile;

import Entitati.*;
import java.util.List;
import java.util.stream.Collectors;

/** Fnctii de afisare / alegere ocmune UI din consola  */
public final class UiUtils {
    private UiUtils() {}

    public static <T> T alegeDinLista(List<T> lista, String label) {
        if (lista.isEmpty()) {
            System.out.println("Nu exista optiuni pentru " + label.toLowerCase() + "!");
            return null;
        }
        System.out.println(label + ":");
        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, labelFor(lista.get(i)));
        }
        int idx = Input.citesteInt("Alege (numar):") - 1;
        return (idx >= 0 && idx < lista.size()) ? lista.get(idx) : null;
    }

    public static Utile.Materie alegeMaterie(String label) {
        System.out.println(label + ":");
        Utile.Materie[] v = Utile.Materie.values();
        for (int i = 0; i < v.length; i++) System.out.printf("%d. %s%n", i + 1, v[i]);
        int idx = Input.citesteInt("Alege materie (numar):") - 1;
        return (idx >= 0 && idx < v.length) ? v[idx] : alegeMaterie(label);
    }

    public static String labelFor(Object o) {
        if (o instanceof Student s)  return s.getNume();
        if (o instanceof Tutor   t)  return t.getNume() + " (" + t.getMaterie() + ")";
        if (o instanceof Curs    c)  return c.getTitlu() + " [" + c.getTutor().getNume() + "]";
        if (o instanceof Sesiune se) return se.getCurs().getTitlu() + " @ " + se.getWhen();
        return String.valueOf(o);
    }

    /** Pentru liste preluate din repo */
    public static List<Student> obtineStudenti() {
        return Repositories.UserRepo.USERS.values().stream()
                .filter(u -> u instanceof Student).map(u -> (Student) u).collect(Collectors.toList());
    }
    public static List<Tutor> obtineTutori() {
        return Repositories.UserRepo.USERS.values().stream()
                .filter(u -> u instanceof Tutor).map(u -> (Tutor) u).collect(Collectors.toList());
    }
}
