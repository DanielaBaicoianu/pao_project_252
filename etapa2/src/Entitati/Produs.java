package Entitati;

import Util.TipProdus;

public class Produs {
    private int id;
    private String nume;
    private double pret;
    private TipProdus tip_produs;

    public Produs(String nume, double pret) {
        this.nume = nume;
        this.pret = pret;
    }

    public Produs(Produs other) {
        this.id = other.id;
        this.nume = other.nume;
        this.pret = other.pret;
        this.tip_produs = other.tip_produs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public TipProdus getTip_produs() {
        return tip_produs;
    }

    public void setTip_produs(TipProdus tip_produs) {
        this.tip_produs = tip_produs;
    }

    @Override
    public String toString() {
        return nume + " - " + pret + " RON";
    }
}
