package Servicii;

import Entitati.Furnizor;
import Repo.FurnizorRepo;
import java.util.List;

public class FurnizorServiciu implements FurnizorServiciuInterfata {
    private FurnizorRepo repo;

    public FurnizorServiciu(FurnizorRepo repo) {
        this.repo = repo;
    }

    @Override
    public void adaugaFurnizor(Furnizor furnizor) {
        repo.insert(furnizor);
    }

    @Override
    public Furnizor cautaFurnizor(String nume) {
        return repo.get(nume);
    }

    @Override
    public void stergeFurnizor(String nume) {
        repo.delete(nume);
    }

    @Override
    public void actualizeazaFurnizor(String nume, Furnizor furnizorActualizat) {
        repo.update(nume, furnizorActualizat);
    }

    @Override
    public List<Furnizor> obtineTotiFurnizorii() {
        return repo.getAll();
    }
}
