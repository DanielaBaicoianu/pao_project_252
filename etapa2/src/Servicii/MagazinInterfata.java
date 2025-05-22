package Servicii;

import Entitati.Accesoriu;
import Entitati.Client;
import Entitati.Comanda;
import Entitati.Instrument;
import Entitati.Produs;
import Util.BrandInstrument;
import Util.CategorieInstrument;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MagazinInterfata {
    void adaugaInstrument(Instrument var1);

    void adaugaAccesoriu(Accesoriu var1);

    void plaseazaComanda(Comanda var1);

    void afiseazaComenzi();

    void afiseazaClienti();

    List<Client> sorteazaClientiAlfabetic();

    void aplicaPromotie(CategorieInstrument var1, BrandInstrument var2, double var3);

    Client cautaClientDupaEmail(String var1);

    Comanda cautaComandaDupaId(int var1);

    List<Instrument> getProduseByCategorie(CategorieInstrument var1);

    List<Accesoriu> getAccesoriiCompatibile(String var1);

    void genereazaFactura(Comanda var1);

    double raportVanzariPePerioada(Date var1, Date var2);

    List<Produs> filtreazaProduseDupaPret(double var1, double var3);

    List<Client> raportClientiFideli(int var1);

    Map<BrandInstrument, List<Instrument>> grupeazaInstrumenteDupaBrand();

    public double calculeazaTotalComanda(Comanda comanda);
}
