package Servicii;

import Entitati.Comanda;
import Entitati.Factura;
import Repozitorii.FacturaRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturaServiciu implements FacturaServiciuInterfata {
    private final FacturaRepo facturaRepo;

    public FacturaServiciu(FacturaRepo facturaRepo) {
        this.facturaRepo = facturaRepo;
    }

    @Override
    public void genereazaFactura(Comanda comanda, double total) {
        Factura factura = new Factura(comanda, total);
        facturaRepo.insert(factura);
    }

    @Override
    public Factura gasesteFactura(int id) {
        return facturaRepo.get(id);
    }

    @Override
    public boolean stergeFactura(int id) {
        return facturaRepo.delete(id);
    }

    @Override
    public boolean actualizeazaFactura(Factura factura) {
        return facturaRepo.update(factura);
    }

    @Override
    public List<Factura> getToateFacturile() {
        return facturaRepo.getAll();
    }
    @Override
    public List<Factura> filtreazaDupaData(Date deLa, Date panaLa) {
        List<Factura> rezultat = new ArrayList<>();
        for (Factura f : facturaRepo.getAll()) {
            if (!f.getDataFactura().before(deLa) && !f.getDataFactura().after(panaLa)) {
                rezultat.add(f);
            }
        }
        return rezultat;
    }

    @Override
    public List<Factura> filtreazaDupaTotal(double min, double max) {
        List<Factura> rezultat = new ArrayList<>();
        for (Factura f : facturaRepo.getAll()) {
            if (f.getTotal() >= min && f.getTotal() <= max) {
                rezultat.add(f);
            }
        }
        return rezultat;
    }

    @Override
    public void exportaFacturiInPDF(List<Factura> facturi, String numeFisier) {
        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new java.io.FileOutputStream(numeFisier));
            document.open();
            for (Factura f : facturi) {
                document.add(new com.itextpdf.text.Paragraph(f.toString()));
                document.add(new com.itextpdf.text.Paragraph("--------------------------------------------------"));
            }
            document.close();
        } catch (Exception e) {
            System.out.println("Eroare la generarea PDF-ului: " + e.getMessage());
        }
    }

}
