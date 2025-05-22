package Servicii;

import Entitati.Furnizor;
import java.util.List;

public interface FurnizorServiciuInterfata {
    void adaugaFurnizor(Furnizor furnizor);
    Furnizor cautaFurnizor(String nume);
    void stergeFurnizor(String nume);
    void actualizeazaFurnizor(String nume, Furnizor furnizorActualizat);
    List<Furnizor> obtineTotiFurnizorii();
}
