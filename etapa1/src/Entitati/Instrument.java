package Entitati;

import Util.BrandInstrument;
import Util.CategorieInstrument;

public class Instrument extends Produs {
    private CategorieInstrument categorie;
    private BrandInstrument brand;

    public Instrument(String nume, double pret, CategorieInstrument categorie, BrandInstrument brand) {
        super(nume, pret);
        this.categorie = categorie;
        this.brand = brand;
    }

    public CategorieInstrument getCategorie() {
        return categorie;
    }

    public BrandInstrument getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + categorie + ", " + brand + ")";
    }
}