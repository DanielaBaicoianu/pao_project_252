package Repo;

import Entitati.Recenzie;
import java.util.ArrayList;
import java.util.List;

public class RecenzieRepo {
    private List<Recenzie> recenzii;

    public RecenzieRepo() {
        this.recenzii = new ArrayList<>();
    }

    public void insert(Recenzie recenzie) {
        recenzii.add(recenzie);
    }

    public Recenzie get(String numeClient) {
        for (Recenzie recenzie : recenzii) {
            if (recenzie.getNumeClient().equals(numeClient)) {
                return recenzie;
            }
        }
        return null;
    }

    public void delete(String numeClient) {
        recenzii.removeIf(recenzie -> recenzie.getNumeClient().equals(numeClient));
    }

    public void update(String numeClient, Recenzie recenzieActualizata) {
        for (int i = 0; i < recenzii.size(); i++) {
            if (recenzii.get(i).getNumeClient().equals(numeClient)) {
                recenzii.set(i, recenzieActualizata);
                break;
            }
        }
    }

    public List<Recenzie> getAll() {
        List<Recenzie> copiaRecenzii = new ArrayList<>();
        for (Recenzie recenzie : recenzii) {
            copiaRecenzii.add(new Recenzie(recenzie.getNumeClient(), recenzie.getComentariu(), recenzie.getRating()));
        }
        return copiaRecenzii;
    }
}
