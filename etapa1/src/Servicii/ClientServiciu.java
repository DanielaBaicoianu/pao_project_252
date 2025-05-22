package Servicii;

import Entitati.Client;
import Repozitorii.ClientRepo;

import java.util.List;

public class ClientServiciu implements ClientServiciuInterfata {
    private final ClientRepo clientRepo;

    public ClientServiciu(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public void adaugaClient(int id, String nume, String email) {
        Client client = new Client(id, nume, email);
        clientRepo.insert(client);
    }

    @Override
    public void plaseazaComanda(int id) {
        Client client = clientRepo.get(id);
        if (client != null) {
            client.plaseazaComanda();
            clientRepo.update(client);
        } else {
            System.out.println("Clientul cu id-ul " + id + " nu a fost gasit.");
        }
    }

    @Override
    public boolean stergeClient(int id) {
        return clientRepo.delete(id);
    }

    @Override
    public boolean actualizeazaClient(Client client) {
        return clientRepo.update(client);
    }

    @Override
    public Client gasesteClient(int id) {
        return clientRepo.get(id);
    }

    @Override
    public List<Client> getTotiClientii() {
        return clientRepo.getAll();
    }
}
