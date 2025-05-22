package Entitati;

public class Accesoriu extends Produs {
    private final String compatibilitate;

    public Accesoriu(String nume, double pret, String compatibilitate) {
        super(nume, pret);
        this.compatibilitate = compatibilitate;
    }

    public String getCompatibilitate() {
        return compatibilitate;
    }

    @Override
    public String toString() {
        return super.toString() + " (Compatibil cu: " + compatibilitate + ")";
    }
}