package Servicii;

import Entitati.Comanda;
import Entitati.Factura;

import java.util.Date;
import java.util.List;

public interface FacturaServiciuInterfata {
    void genereazaFactura(Comanda comanda, double total);
    Factura gasesteFactura(int id);
    boolean stergeFactura(int id);
    boolean actualizeazaFactura(Factura factura);
    List<Factura> getToateFacturile();
    List<Factura> filtreazaDupaData(Date deLa, Date panaLa);
    List<Factura> filtreazaDupaTotal(double min, double max);
    void exportaFacturiInPDF(List<Factura> facturi, String numeFisier);

}
