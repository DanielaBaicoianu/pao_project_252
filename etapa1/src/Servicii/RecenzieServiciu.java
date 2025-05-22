package Servicii;

import Entitati.Recenzie;
import Repo.RecenzieRepo;
import java.util.List;

public class RecenzieServiciu implements RecenzieServiciuInterfata {
    private RecenzieRepo repo;

    public RecenzieServiciu(RecenzieRepo repo) {
        this.repo = repo;
    }

    @Override
    public void adaugaRecenzie(Recenzie recenzie) {
        repo.insert(recenzie);
    }

    @Override
    public Recenzie cautaRecenzie(String numeClient) {
        return repo.get(numeClient);
    }

    @Override
    public void stergeRecenzie(String numeClient) {
        repo.delete(numeClient);
    }

    @Override
    public void actualizeazaRecenzie(String numeClient, Recenzie recenzieActualizata) {
        repo.update(numeClient, recenzieActualizata);
    }

    @Override
    public List<Recenzie> obtineToateRecenziile() {
        return repo.getAll();
    }
}
