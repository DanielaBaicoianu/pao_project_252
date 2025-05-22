package Repozitorii;

import Entitati.Comanda;

import java.util.ArrayList;
import java.util.List;

public class ComandaRepo {
    private final List<Comanda> comenzi = new ArrayList<>();

    public void insert(Comanda comanda) {
        comenzi.add(comanda);
    }

    public Comanda get(int id) {
        for (Comanda c : comenzi) {
            if (c.getId() == id) {
                return new Comanda(c); // deep copy
            }
        }
        return null;
    }

    public boolean delete(int id) {
        return comenzi.removeIf(c -> c.getId() == id);
    }

    public boolean update(Comanda comandaNoua) {
        for (int i = 0; i < comenzi.size(); i++) {
            if (comenzi.get(i).getId() == comandaNoua.getId()) {
                comenzi.set(i, comandaNoua);
                return true;
            }
        }
        return false;
    }

    public List<Comanda> getAll() {
        List<Comanda> copie = new ArrayList<>();
        for (Comanda c : comenzi) {
            copie.add(new Comanda(c)); // deep copy
        }
        return copie;
    }
}
