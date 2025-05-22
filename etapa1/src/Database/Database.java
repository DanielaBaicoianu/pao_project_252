package Database;

import Entitati.Client;
import Entitati.Comanda;
import Entitati.Produs;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class Database {
    public List<Produs> produse;
    public List<Comanda> comenzi;

    public SortedSet<Client> clienti;
    public Map<Integer, Comanda> comenziMap;
}
