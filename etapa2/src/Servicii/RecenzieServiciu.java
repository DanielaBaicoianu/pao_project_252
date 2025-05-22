package Servicii;

import Entitati.Recenzie;
import Repositories.RecenzieRepository;
import java.util.List;

public class RecenzieServiciu implements RecenzieServiciuInterfata {
    private final RecenzieRepository recenzieRepository;

    private RecenzieServiciu() {
        this.recenzieRepository = RecenzieRepository.getInstance();
    }

    private static final class SINGLETON {
        private static final RecenzieServiciu instance = new RecenzieServiciu();
    }

    public static RecenzieServiciu getInstance() {
        return SINGLETON.instance;
    }

    @Override
    public void adaugaRecenzie(Recenzie recenzie) {
        recenzieRepository.create(recenzie);
    }

    @Override
    public Recenzie cautaRecenzie(String numeClient) {
        List<Recenzie> recenzii = recenzieRepository.readAll();
        for (Recenzie r : recenzii) {
            if (r.getClient().getNume().equals(numeClient)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public void stergeRecenzie(String numeClient) {
        Recenzie recenzie = cautaRecenzie(numeClient);
        if (recenzie != null) {
            recenzieRepository.delete(recenzie.getId());
        }
    }

    @Override
    public void actualizeazaRecenzie(String numeClient, Recenzie recenzieActualizata) {
        Recenzie recenzie = cautaRecenzie(numeClient);
        if (recenzie != null) {
            recenzieActualizata.setId(recenzie.getId());
            recenzieRepository.update(recenzieActualizata);
        }
    }

    @Override
    public List<Recenzie> obtineToateRecenziile() {
        return recenzieRepository.readAll();
    }
}
