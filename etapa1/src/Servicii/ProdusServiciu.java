package Servicii;

import Entitati.Produs;
import Repo.ProdusRepo;
import java.util.List;

public class ProdusServiciu implements ProdusServiciuInterfata {
    private ProdusRepo repo;

    public ProdusServiciu(ProdusRepo repo) {
        this.repo = repo;
    }

    @Override
    public void adaugaProdus(Produs produs) {
        repo.insert(produs);
    }

    @Override
    public Produs cautaProdus(String nume) {
        return repo.get(nume);
    }

    @Override
    public void stergeProdus(String nume) {
        repo.delete(nume);
    }

    @Override
    public void actualizeazaProdus(String nume, Produs produsActualizat) {
        repo.update(nume, produsActualizat);
    }

    @Override
    public List<Produs> obtineToateProdusele() {
        return repo.getAll();
    }
}
