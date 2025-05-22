package Servicii;

import Entitati.Produs;
import java.util.List;

public interface ProdusServiciuInterfata {
    void adaugaProdus(Produs produs);
    Produs cautaProdus(String nume);
    void stergeProdus(String nume);
    void actualizeazaProdus(String nume, Produs produsActualizat);
    List<Produs> obtineToateProdusele();
}
