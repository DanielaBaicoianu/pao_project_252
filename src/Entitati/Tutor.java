package Entitati;

import Utile.Materie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tutor extends Utilizator {
    private double tarifOra;
    private Materie materie;
    private List<Curs> cursuri;

    public Tutor(String n, String e, String p, double tarif, Materie materie) {
        super(n, e, p);
        this.tarifOra = tarif;
        this.materie  = materie;
        this.cursuri  = new ArrayList<>();
    }

    // adauga un curs în lista proprie
    public void adaugaCurs(Curs c) {
        cursuri.add(c);
    }

    // Returneaza lista de cursuri (read-only)
    public List<Curs> getCursuri() {
        return Collections.unmodifiableList(cursuri);
    }

    public double getTarifOra() {
        return tarifOra;
    }

    public Materie getMaterie() {
        return materie;
    }
}
