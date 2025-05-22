package Servicii;

import Entitati.Comanda;
import Entitati.Client;
import Entitati.Produs;

import java.util.List;

public interface ComandaServiciuInterfata {
    void adaugaComanda(Client client);
    void adaugaProdusLaComanda(int idComanda, Produs produs);
    void proceseazaComanda(int idComanda);
    Comanda gasesteComanda(int id);
    boolean stergeComanda(int id);
    boolean actualizeazaComanda(Comanda comanda);
    List<Comanda> getToateComenzile();
    public double calculeazaTotalComanda(Comanda comanda);
    public void adaugaComanda(Comanda comanda);
}
