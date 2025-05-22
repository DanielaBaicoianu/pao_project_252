package Repo;

import Entitati.Furnizor;
import java.util.ArrayList;
import java.util.List;

public class FurnizorRepo {
    private List<Furnizor> furnizori;

    public FurnizorRepo() {
        this.furnizori = new ArrayList<>();
    }

    public void insert(Furnizor furnizor) {
        furnizori.add(furnizor);
    }

    public Furnizor get(String nume) {
        for (Furnizor furnizor : furnizori) {
            if (furnizor.getNume().equals(nume)) {
                return furnizor;
            }
        }
        return null;
    }

    public void delete(String nume) {
        furnizori.removeIf(furnizor -> furnizor.getNume().equals(nume));
    }

    public void update(String nume, Furnizor furnizorActualizat) {
        for (int i = 0; i < furnizori.size(); i++) {
            if (furnizori.get(i).getNume().equals(nume)) {
                furnizori.set(i, furnizorActualizat);
                break;
            }
        }
    }

    public List<Furnizor> getAll() {
        List<Furnizor> copiaFurnizori = new ArrayList<>();
        for (Furnizor furnizor : furnizori) {
            copiaFurnizori.add(new Furnizor(furnizor.getNume(), furnizor.getContact()));
        }
        return copiaFurnizori;
    }
}
