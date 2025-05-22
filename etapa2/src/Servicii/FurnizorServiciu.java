package Servicii;

import Entitati.Furnizor;
import Repositories.FurnizorRepository;
import java.util.List;

public class FurnizorServiciu implements FurnizorServiciuInterfata {
    private final FurnizorRepository furnizorRepository;

    private FurnizorServiciu() {
        this.furnizorRepository = FurnizorRepository.getInstance();
    }

    private static final class SINGLETON {
        private static final FurnizorServiciu instance = new FurnizorServiciu();
    }

    public static FurnizorServiciu getInstance() {
        return SINGLETON.instance;
    }

    @Override
    public void adaugaFurnizor(Furnizor furnizor) {
        furnizorRepository.create(furnizor);
    }

    @Override
    public Furnizor cautaFurnizor(String nume) {
        List<Furnizor> furnizori = furnizorRepository.readAll();
        for (Furnizor f : furnizori) {
            if (f.getNume().equals(nume)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public void stergeFurnizor(String nume) {
        Furnizor furnizor = cautaFurnizor(nume);
        if (furnizor != null) {
            furnizorRepository.delete(furnizor.getId());
        }
    }

    @Override
    public void actualizeazaFurnizor(String nume, Furnizor furnizorActualizat) {
        Furnizor furnizor = cautaFurnizor(nume);
        if (furnizor != null) {
            furnizorActualizat.setId(furnizor.getId());
            furnizorRepository.update(furnizorActualizat);
        }
    }

    @Override
    public List<Furnizor> obtineTotiFurnizorii() {
        return furnizorRepository.readAll();
    }
}
