package Servicii;

import Entitati.*;
import Repositories.*;
import Util.BrandInstrument;
import Util.CategorieInstrument;

import java.util.*;

public class Magazin implements MagazinInterfata {

    private ProdusRepo produsRepo;
    private ClientRepo clientRepo;
    private ComandaRepo comandaRepo;
    private FacturaRepo facturaRepo;
    private RecenzieRepo recenzieRepo;
    // maine
    public Magazin() {
        produsRepo = new ProdusRepo();
    }

    public void adaugaInstrument(Instrument i) {
        instrumente.add(i);
    }

    public void adaugaAccesoriu(Accesoriu a) {
        accesorii.add(a);
    }

    // plasarea unei comenzi – actualizeaza clienti si map-ul de comenzi
    public void plaseazaComanda(Comanda c) {
        comenzi.add(c);
        comenziMap.put(c.getId(), c);
        // adaug clientul in colectia sortata (daca nu este deja prezent)
        clienti.add(c.getClient());
        c.getClient().plaseazaComanda();
    }

    public void afiseazaComenzi() {
        for (Comanda c : comenzi) {
            System.out.println(c);
        }
    }


    public void afiseazaClienti() {
        for (Client client : clienti) {
            System.out.println(client);
        }
    }

    public void afiseazaComenziMap() {
        for (Integer key : comenziMap.keySet()) {
            System.out.println("ID: " + key + " -> " + comenziMap.get(key));
        }
    }

    // sortez alfabetic clientii dupa nume
    public List<Client> sorteazaClientiAlfabetic() {
        List<Client> lista = new ArrayList<>(clienti);
        lista.sort(Comparator.comparing(Client::getNume));
        return lista;
    }

    //adug promotie la toate elementele din categoria si brandul respectiv
    public void aplicaPromotie(CategorieInstrument categorie, BrandInstrument brand, double discountPercentage) {
        for (Comanda c : comenzi) {
            for (Produs p : c.getProduse()) {
                if (p instanceof Instrument) {
                    Instrument inst = (Instrument) p;
                    if (inst.getCategorie() == categorie && inst.getBrand() == brand) {
                        double pretNou = inst.getPret() * (1 - discountPercentage / 100);
                        inst.setPret(pretNou);
                    }
                }
            }
        }
    }


    public Client cautaClientDupaEmail(String email) {
        for (Client client : clienti) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client;
            }
        }
        return null;
    }

    public Comanda cautaComandaDupaId(int id) {
        return comenziMap.get(id);
    }

    public double calculeazaTotalComanda(Comanda comanda) {
        double total = 0;
        for (Produs p : comanda.getProduse()) {
            total += p.getPret();
        }
        return total;
    }

    public List<Instrument> getProduseByCategorie(CategorieInstrument categorie) {
        List<Instrument> lista = new ArrayList<>();
        for (Instrument i : instrumente) {
            if (i.getCategorie() == categorie) {
                lista.add(i);
            }
        }
        return lista;
    }

    public List<Accesoriu> getAccesoriiCompatibile(String compatibilitate) {
        List<Accesoriu> lista = new ArrayList<>();
        for (Accesoriu a : accesorii) {
            if (a.getCompatibilitate().equalsIgnoreCase(compatibilitate)) {
                lista.add(a);
            }
        }
        return lista;
    }


    // genereaza factura
    public Factura genereazaFactura(Comanda comanda) {
        double total = calculeazaTotalComanda(comanda);
        return new Factura(comanda, total);
    }

    // raport de vanzari pe o anumita perioada de timp
    public double raportVanzariPePerioada(Date start, Date end) {
        double totalVanzari = 0;
        for (Comanda c : comenzi) {
            if (!c.getDataPlasare().before(start) && !c.getDataPlasare().after(end)) {
                totalVanzari += calculeazaTotalComanda(c);
            }
        }
        return totalVanzari;
    }

    // filter produse (instrumente si accesorii) dintr-un interval de pret
    public List<Produs> filtreazaProduseDupaPret(double pretMin, double pretMax) {
        List<Produs> lista = new ArrayList<>();
        for (Instrument i : instrumente) {
            if (i.getPret() >= pretMin && i.getPret() <= pretMax) {
                lista.add(i);
            }
        }
        for (Accesoriu a : accesorii) {
            if (a.getPret() >= pretMin && a.getPret() <= pretMax) {
                lista.add(a);
            }
        }
        return lista;
    }

    // raport clienți fideli – clienți cu un nr de comenzi peste un prag
    public List<Client> raportClientiFideli(int pragComenzi) {
        List<Client> lista = new ArrayList<>();
        for (Client client : clienti) {
            int numarComenzi = 0;
            for (Comanda c : comenzi) {
                if (c.getClient().equals(client)) {
                    numarComenzi++;
                }
            }
            if (numarComenzi >= pragComenzi) {
                lista.add(client);
            }
        }
        return lista;
    }

    public Map<BrandInstrument, List<Instrument>> grupeazaInstrumenteDupaBrand() {
        Map<BrandInstrument, List<Instrument>> map = new HashMap<>();
        for (Instrument i : instrumente) {
            map.putIfAbsent(i.getBrand(), new ArrayList<>());
            map.get(i.getBrand()).add(i);
        }
        return map;
    }
}