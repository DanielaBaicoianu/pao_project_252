package Servicii;

import Entitati.Client;
import java.util.List;

public interface ClientServiciuInterfata {
    void adaugaClient(int id, String nume, String email);
    void plaseazaComanda(int id);
    boolean stergeClient(int id);
    boolean actualizeazaClient(Client client);
    Client gasesteClient(int id);
    List<Client> getTotiClientii();
    public Client gasesteClientEmail(String email);
}
