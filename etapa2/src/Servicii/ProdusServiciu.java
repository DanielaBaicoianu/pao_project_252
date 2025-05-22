package Servicii;

import Entitati.Accesoriu;
import Entitati.Instrument;
import Entitati.Produs;
import Repositories.ProdusRepository;
import java.util.List;
import java.util.stream.Collectors;

public class ProdusServiciu implements ProdusServiciuInterfata {
    private final ProdusRepository produsRepository;

    private ProdusServiciu() {
        this.produsRepository = ProdusRepository.getInstance();
    }

    private static final class SINGLETON {
        private static final ProdusServiciu instance = new ProdusServiciu();
    }

    public static ProdusServiciu getInstance() {
        return SINGLETON.instance;
    }

    @Override
    public void adaugaProdus(Produs produs) {
        produsRepository.create(produs);
    }

    @Override
    public Produs cautaProdus(String nume) {
        return produsRepository.findByNume(nume);
    }

    @Override
    public void stergeProdus(String nume) {
        Produs produs = produsRepository.findByNume(nume);
        if (produs != null) {
            produsRepository.delete(produs.getId());
        }
    }

    @Override
    public void actualizeazaProdus(String nume, Produs produsActualizat) {
        Produs produs = produsRepository.findByNume(nume);
        if (produs != null) {
            produsActualizat.setId(produs.getId());
            produsRepository.update(produsActualizat);
        }
    }

    @Override
    public List<Produs> obtineToateProdusele() {
        return produsRepository.readAll();
    }

    @Override
    public List<Instrument> obtineToateInstrumentele() {
        return produsRepository.readAll().stream()
                .filter(p -> p instanceof Instrument)
                .map(p -> (Instrument) p)
                .collect(Collectors.toList());
    }

    @Override
    public List<Accesoriu> obtineToateAccesoriile() {
        return produsRepository.readAll().stream()
                .filter(p -> p instanceof Accesoriu)
                .map(p -> (Accesoriu) p)
                .collect(Collectors.toList());
    }
}
