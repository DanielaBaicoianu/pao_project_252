package Servicii;

import Entitati.*;
import Util.BrandInstrument;
import Util.CategorieInstrument;
import Util.TipProdus;

import java.util.*;

public class Magazin implements MagazinInterfata {

    private Map<Client, List<Comanda>> clientMap;
    private Set<Client> clientSet;

    private Magazin()
    {
        //clientMap = new HashMap<>();
        genereazaClientMap();
        generateClientSet();
    }

    private static final class SINGLETON{
        private static final Magazin instance = new Magazin();
    }

    public static Magazin getInstance(){return SINGLETON.instance;}

    public void adaugaInstrument(Instrument i) {
        ProdusServiciu.getInstance().adaugaProdus((Produs) i);
    }

    public void adaugaAccesoriu(Accesoriu a) {
        ProdusServiciu.getInstance().adaugaProdus((Produs) a);
    }

    public void generateClientSet()
    {
        clientSet = new HashSet<>(ClientServiciu.getInstance().getTotiClientii());
    }
    // plasarea unei comenzi  actualizeaza clienti si map-ul de comenzi
    public void plaseazaComanda(Comanda c) {
        // adaug clientul in colectia sortata (daca nu este deja prezent)
        if(!clientSet.contains(c.getClient()))
        {
            clientSet.add(c.getClient());
            ClientServiciu.getInstance().adaugaClient(c.getClient().getId(), c.getClient().getNume(), c.getClient().getEmail());
        }
        ComandaServiciu.getInstance().adaugaComanda(c);
        c.getClient().plaseazaComanda();
        ClientServiciu.getInstance().actualizeazaClient(c.getClient());
        genereazaClientMap();
    }

    public void afiseazaComenzi() {
        List<Comanda> comenzi = ComandaServiciu.getInstance().getToateComenzile();
        for (Comanda c : comenzi) {
            System.out.println(c);
        }
    }


    public void afiseazaClienti() {
        List<Client> clienti = ClientServiciu.getInstance().getTotiClientii();
        for (Client client : clienti) {
            System.out.println(client);
        }
    }

    public void afiseazaFacturi() {
        List<Factura> facturi = FacturaServiciu.getInstance().getToateFacturile();
        for (Factura factura : facturi) {
            System.out.println(factura);
        }
    }

    // sortez alfabetic clientii dupa nume
    public List<Client> sorteazaClientiAlfabetic() {
        List<Client> clienti = ClientServiciu.getInstance().getTotiClientii();
        List<Client> lista = new ArrayList<>(clienti);
        lista.sort(Comparator.comparing(Client::getNume));
        return lista;
    }

    //adug promotie la toate elementele din categoria si brandul respectiv
    public void aplicaPromotie(CategorieInstrument categorie, BrandInstrument brand, double discountPercentage) {
        List<Comanda> comenzi = ComandaServiciu.getInstance().getToateComenzile();
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
        return ClientServiciu.getInstance().gasesteClientEmail(email);
    }

    public Comanda cautaComandaDupaId(int id) {
        return ComandaServiciu.getInstance().gasesteComanda(id);
    }

    public List<Instrument> getProduseByCategorie(CategorieInstrument categorie) {
        List<Instrument> instrumente = ProdusServiciu.getInstance().obtineToateInstrumentele();
        List<Instrument> lista = new ArrayList<>();
        for (Instrument i : instrumente) {
            if (i.getCategorie() == categorie) {
                lista.add(i);
            }
        }
        return lista;
    }

    public List<Accesoriu> getAccesoriiCompatibile(String compatibilitate) {
        List<Accesoriu> accesorii = ProdusServiciu.getInstance().obtineToateAccesoriile();
        List<Accesoriu> lista = new ArrayList<>();
        for (Accesoriu a : accesorii) {
            if (a.getCompatibilitate().equalsIgnoreCase(compatibilitate)) {
                lista.add(a);
            }
        }
        return lista;
    }


    // genereaza factura
    public void genereazaFactura(Comanda comanda) {
        FacturaServiciu.getInstance().genereazaFactura(comanda, ComandaServiciu.getInstance().calculeazaTotalComanda(comanda));
    }

    // raport de vanzari pe o anumita perioada de timp
    public double raportVanzariPePerioada(Date start, Date end) {
        double totalVanzari = 0;
        List<Comanda> comenzi = ComandaServiciu.getInstance().getToateComenzile();
        for (Comanda c : comenzi) {
            if (!c.getDataPlasare().before(start) && !c.getDataPlasare().after(end)) {
                totalVanzari += ComandaServiciu.getInstance().calculeazaTotalComanda(c);
            }
        }
        return totalVanzari;
    }

    // filter produse (instrumente si accesorii) dintr-un interval de pret
    public List<Produs> filtreazaProduseDupaPret(double pretMin, double pretMax) {
        List<Produs> produse = ProdusServiciu.getInstance().obtineToateProdusele();
        List<Produs> lista = new ArrayList<>();

        for (Produs p : produse) {
            if (p.getPret() >= pretMin && p.getPret() <= pretMax) {
                lista.add(p);
            }
        }

        return lista;
    }

    public void genereazaClientMap()
    {
        clientMap = new HashMap<>();
        List<Client> clienti = ClientServiciu.getInstance().getTotiClientii();
        List<Comanda> comenzi = ComandaServiciu.getInstance().getToateComenzile();

        for (Client client : clienti) {
            List<Comanda> comenziClient = new ArrayList<>();
            for (Comanda c : comenzi) {
                if (c.getClient().getId() ==client.getId()) {
                    comenziClient.add(c);
                }
            }
            clientMap.put(client, comenziClient);
        }
    }
    // raport clieni fideli  clieni cu un nr de comenzi peste un prag
    public List<Client> raportClientiFideli(int pragComenzi) {
        List<Client> lista = new ArrayList<>();
        for (Map.Entry<Client, List<Comanda>> e : clientMap.entrySet()) {

            if (e.getValue().size() >= pragComenzi) {
                lista.add(e.getKey());
            }
        }
        return lista;
    }

    public Map<BrandInstrument, List<Instrument>> grupeazaInstrumenteDupaBrand() {
        List<Instrument> instrumente = ProdusServiciu.getInstance().obtineToateInstrumentele();
        Map<BrandInstrument, List<Instrument>> map = new HashMap<>();
        for (Instrument i : instrumente) {
            map.putIfAbsent(i.getBrand(), new ArrayList<>());
            map.get(i.getBrand()).add(i);
        }
        return map;
    }

    @Override
    public double calculeazaTotalComanda(Comanda comanda)
    {
        return ComandaServiciu.getInstance().calculeazaTotalComanda(comanda);
    }
}