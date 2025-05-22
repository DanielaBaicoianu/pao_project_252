package Repozitorii;

import Entitati.Factura;

import java.util.ArrayList;
import java.util.List;

public class FacturaRepo {
    private final List<Factura> facturi = new ArrayList<>();

    public void insert(Factura factura) {
        facturi.add(factura);
    }

    public Factura get(int id) {
        for (Factura f : facturi) {
            if (f.getId() == id) {
                return new Factura(f); // deep copy
            }
        }
        return null;
    }

    public boolean delete(int id) {
        return facturi.removeIf(f -> f.getId() == id);
    }

    public boolean update(Factura facturaNoua) {
        for (int i = 0; i < facturi.size(); i++) {
            if (facturi.get(i).getId() == facturaNoua.getId()) {
                facturi.set(i, facturaNoua);
                return true;
            }
        }
        return false;
    }

    public List<Factura> getAll() {
        List<Factura> copie = new ArrayList<>();
        for (Factura f : facturi) {
            copie.add(new Factura(f)); // deep copy
        }
        return copie;
    }
}
