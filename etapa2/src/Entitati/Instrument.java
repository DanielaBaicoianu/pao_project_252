package Entitati;

import Util.BrandInstrument;
import Util.CategorieInstrument;
import Util.TipProdus;

public class Instrument extends Produs {
    private CategorieInstrument categorie;
    private BrandInstrument brand;

    public Instrument(String nume, double pret, CategorieInstrument categorie, BrandInstrument brand) {
        super(nume, pret);
        this.categorie = categorie;
        this.brand = brand;
        this.setTip_produs(TipProdus.INSTRUMENT);
    }

    public Instrument(Instrument other)
    {
        super(other.getNume(), other.getPret());
        this.categorie = other.getCategorie();
        this.brand = other.getBrand();
        this.setTip_produs(TipProdus.INSTRUMENT);
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