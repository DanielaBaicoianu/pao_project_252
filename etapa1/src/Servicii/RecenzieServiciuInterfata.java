package Servicii;

import Entitati.Recenzie;
import java.util.List;

public interface RecenzieServiciuInterfata {
    void adaugaRecenzie(Recenzie recenzie);
    Recenzie cautaRecenzie(String numeClient);
    void stergeRecenzie(String numeClient);
    void actualizeazaRecenzie(String numeClient, Recenzie recenzieActualizata);
    List<Recenzie> obtineToateRecenziile();
}
