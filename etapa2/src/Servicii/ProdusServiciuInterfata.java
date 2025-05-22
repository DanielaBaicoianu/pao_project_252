package Servicii;

import Entitati.Accesoriu;
import Entitati.Instrument;
import Entitati.Produs;
import java.util.List;

public interface ProdusServiciuInterfata {
    void adaugaProdus(Produs produs);
    Produs cautaProdus(String nume);
    void stergeProdus(String nume);
    void actualizeazaProdus(String nume, Produs produsActualizat);
    List<Produs> obtineToateProdusele();
    public List<Instrument> obtineToateInstrumentele();
    public List<Accesoriu> obtineToateAccesoriile();
}
