package Entitati;

import Util.TipProdus;

public class Accesoriu extends Produs {
    private final String compatibilitate;

    public Accesoriu(String nume, double pret, String compatibilitate) {
        super(nume, pret);
        this.compatibilitate = compatibilitate;
        this.setTip_produs(TipProdus.ACCESORIU);
    }

    public Accesoriu(Accesoriu other)
    {
        super(other.getNume(), other.getPret());
        this.compatibilitate = other.getCompatibilitate();
        this.setTip_produs(TipProdus.ACCESORIU);
    }

    public String getCompatibilitate() {
        return compatibilitate;
    }

    @Override
    public String toString() {
        return super.toString() + " (Compatibil cu: " + compatibilitate + ")";
    }
}