package Entitati;

import java.util.ArrayList;
import java.util.List;

public class Furnizor {
    private String nume;
    private String contact;
    private List<Produs> produseFurnizate;

    public Furnizor(String nume, String contact) {
        this.nume = nume;
        this.contact = contact;
        this.produseFurnizate = new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Produs> getProduseFurnizate() {
        return produseFurnizate;
    }

    public void setProduseFurnizate(List<Produs> produseFurnizate) {
        this.produseFurnizate = produseFurnizate;
    }

    public void adaugaProdus(Produs p) {
        produseFurnizate.add(p);
    }

    @Override
    public String toString() {
        return "Furnizor: " + nume + ", Contact: " + contact;
    }
}
