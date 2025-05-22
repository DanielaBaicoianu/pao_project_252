package Servicii;

import Entitati.Client;
import Entitati.Comanda;
import Entitati.Produs;
import Repositories.ComandaRepository;

import java.util.List;

public class ComandaServiciu implements ComandaServiciuInterfata {
    private final ComandaRepository comandaRepository;

    private ComandaServiciu() {
        this.comandaRepository = ComandaRepository.getInstance();
    }

    private static final class SINGLETON {
        private static final ComandaServiciu instance = new ComandaServiciu();
    }

    public static ComandaServiciu getInstance() {
        return SINGLETON.instance;
    }

    @Override
    public void adaugaComanda(Client client) {
        Comanda comanda = new Comanda(client);
        comandaRepository.create(comanda);
        client.plaseazaComanda(); // actualizeaza categoria automat
    }

    @Override
    public void adaugaComanda(Comanda comanda) {
        comandaRepository.create(comanda);
    }

    @Override
    public void adaugaProdusLaComanda(int idComanda, Produs produs) {
        Comanda c = comandaRepository.read(idComanda);
        if (c != null) {
            c.adaugaProdus(produs);
            comandaRepository.update(c);
        }
    }

    @Override
    public void proceseazaComanda(int idComanda) {
        Comanda c = comandaRepository.read(idComanda);
        if (c != null) {
            c.proceseazaComanda();
            comandaRepository.update(c);
        }
    }

    @Override
    public Comanda gasesteComanda(int id) {
        return comandaRepository.read(id);
    }

    @Override
    public boolean stergeComanda(int id) {
        try {
            comandaRepository.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizeazaComanda(Comanda comanda) {
        try {
            comandaRepository.update(comanda);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Comanda> getToateComenzile() {
        return comandaRepository.readAll();
    }

    public double calculeazaTotalComanda(Comanda comanda) {
        double total = 0;
        for (Produs p : comanda.getProduse()) {
            total += p.getPret();
        }
        return total;
    }
}
