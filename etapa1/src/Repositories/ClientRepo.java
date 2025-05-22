package Repozitorii;

import Entitati.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientRepo {
    private final List<Client> clienti = new ArrayList<>();

    public void insert(Client client) {
        clienti.add(client);
    }

    public Client get(int id) {
        for (Client client : clienti) {
            if (client.getId() == id) {
                return new Client(client); // deep copy
            }
        }
        return null;
    }

    public boolean delete(int id) {
        for (Client client : clienti) {
            if (client.getId() == id) {
                clienti.remove(client);
                return true;
            }
        }
        return false;
    }

    public boolean update(Client updatedClient) {
        for (int i = 0; i < clienti.size(); i++) {
            if (clienti.get(i).getId() == updatedClient.getId()) {
                clienti.set(i, updatedClient);
                return true;
            }
        }
        return false;
    }

    public List<Client> getAll() {
        List<Client> copie = new ArrayList<>();
        for (Client client : clienti) {
            copie.add(new Client(client)); // deep copy
        }
        return copie;
    }
}
