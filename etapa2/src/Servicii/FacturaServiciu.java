package Servicii;

import Entitati.Comanda;
import Entitati.Factura;
import Repositories.FacturaRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturaServiciu implements FacturaServiciuInterfata {
    private static FacturaServiciu instance;
    private final FacturaRepository facturaRepository;

    private FacturaServiciu() {
        this.facturaRepository = FacturaRepository.getInstance();
    }

    public static synchronized FacturaServiciu getInstance() {
        if (instance == null) {
            instance = new FacturaServiciu();
        }
        return instance;
    }

    @Override
    public void genereazaFactura(Comanda comanda, double total) {
        Factura factura = new Factura(comanda, total);
        facturaRepository.create(factura);
    }

    @Override
    public Factura gasesteFactura(int id) {
        return facturaRepository.read(id);
    }

    @Override
    public boolean stergeFactura(int id) {
        try {
            facturaRepository.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizeazaFactura(Factura factura) {
        try {
            facturaRepository.update(factura);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Factura> getToateFacturile() {
        return facturaRepository.readAll();
    }

    @Override
    public List<Factura> filtreazaDupaData(Date deLa, Date panaLa) {
        List<Factura> rezultat = new ArrayList<>();
        for (Factura f : facturaRepository.readAll()) {
            if (!f.getDataFactura().before(deLa) && !f.getDataFactura().after(panaLa)) {
                rezultat.add(f);
            }
        }
        return rezultat;
    }

    @Override
    public List<Factura> filtreazaDupaTotal(double min, double max) {
        List<Factura> rezultat = new ArrayList<>();
        for (Factura f : facturaRepository.readAll()) {
            if (f.getTotal() >= min && f.getTotal() <= max) {
                rezultat.add(f);
            }
        }
        return rezultat;
    }
}
