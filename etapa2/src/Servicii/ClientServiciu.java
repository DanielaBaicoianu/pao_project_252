package Servicii;

import Entitati.Client;
import Repositories.ClientRepository;

import java.util.List;

public class ClientServiciu implements ClientServiciuInterfata {
    private final ClientRepository clientRepository;

    private ClientServiciu() {
        this.clientRepository = ClientRepository.getInstance();
    }

    private static final class SINGLETON {
        private static final ClientServiciu instance = new ClientServiciu();
    }

    public static ClientServiciu getInstance() {
        return SINGLETON.instance;
    }

    @Override
    public void adaugaClient(int id, String nume, String email) {
        Client client = new Client(id, nume, email);
        clientRepository.create(client);
    }

    @Override
    public void plaseazaComanda(int id) {
        Client client = clientRepository.read(id);
        if (client != null) {
            client.plaseazaComanda();
            clientRepository.update(client);
        } else {
            System.out.println("Clientul cu id-ul " + id + " nu a fost gasit.");
        }
    }

    @Override
    public boolean stergeClient(int id) {
        try {
            clientRepository.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizeazaClient(Client client) {
        try {
            clientRepository.update(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Client gasesteClient(int id) {
        return clientRepository.read(id);
    }

    @Override
    public List<Client> getTotiClientii() {
        return clientRepository.readAll();
    }

    @Override
    public Client gasesteClientEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
