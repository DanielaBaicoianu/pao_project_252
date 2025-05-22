package Entitati;

import Utile.NivelExperienta;

import java.util.Date;

import java.time.LocalDate;

public class Mecanic {
    private String nume;
    private LocalDate dataNasterii;
    private LocalDate dataAngajarii;
    private NivelExperienta nivel;
    private boolean esteFolosit;

    public Mecanic(String nume, LocalDate dataNasterii, LocalDate dataAngajarii) {
        this.nume = nume;
        this.dataNasterii = dataNasterii;
        this.dataAngajarii = dataAngajarii;
        this.nivel = calculeazaNivelExperienta();
        this.esteFolosit = false;
    }
    private NivelExperienta calculeazaNivelExperienta(){
        LocalDate dataCurenta = LocalDate.now();
        int aniExperienta = dataCurenta.getYear() - dataAngajarii.getYear();
        if (aniExperienta < 10){
            return NivelExperienta.INCEPATOR;
        }
        if(aniExperienta < 20){
            return NivelExperienta.EXPERIMENTAT;
        }
        return NivelExperienta.VETERAN;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(LocalDate dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public LocalDate getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(LocalDate dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    public NivelExperienta getNivel() {
        return nivel;
    }

    public void setNivel(NivelExperienta nivel) {
        this.nivel = nivel;
    }

    public boolean isEsteFolosit() {
        return esteFolosit;
    }

    public void setEsteFolosit(boolean esteFolosit) {
        this.esteFolosit = esteFolosit;
    }

    @Override
    public String toString() {
        return nume + " " + nivel;
    }

}
