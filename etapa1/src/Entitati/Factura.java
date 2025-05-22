package Entitati;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Factura {
    private static int counter = 1;
    private int id;
    private Comanda comanda;
    private Date dataFactura;
    private double total;

    public Factura(Comanda comanda, double total) {
        this.id = counter++;
        this.comanda = comanda;
        this.dataFactura = new Date();
        this.total = total;
    }

    public Factura(Factura other) {
        this.id = other.id;
        this.comanda = new Comanda(other.comanda);
        this.dataFactura = new Date(other.dataFactura.getTime());
        this.total = other.total;
    }

    public int getId() {
        return id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Date getDataFactura() {
        return dataFactura;
    }

    public void setDataFactura(Date dataFactura) {
        this.dataFactura = dataFactura;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return "Factura " + id + " pentru Comanda " + comanda.getId() + " - Total: " + total + " RON (data: " + sdf.format(dataFactura) + ")";
    }
}
