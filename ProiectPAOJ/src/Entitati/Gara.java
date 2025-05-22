package Entitati;

import java.util.Arrays;

public class Gara {
    private String nume;
    private int nrLinii;
    private Ruta[][] orarLinii;

    public Gara(int nrLinii, String nume) {
        this.nrLinii = nrLinii;
        this.nume = nume;
        this.orarLinii = new Ruta[nrLinii][24];
        for(int i = 0; i < nrLinii; i++){
            orarLinii[i] = new Ruta[24];
            for(int j = 0; j < 24; j++){
                orarLinii[i][j] = null;
            }
        }
    }

    public String getNume() {
        return nume;
    }

    public int getNrLinii() {
        return nrLinii;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setNrLinii(int nrLinii) {
        this.nrLinii = nrLinii;
    }
    public void adaugaLaOrar(Ruta ruta, int ora) {
         for(int i = 0; i < nrLinii; i++){
             if(orarLinii[i][ora] == null){
                 orarLinii[i][ora] = ruta;
                 break;
             }
         }
    }
    public boolean verificaOra(int ora){
        for(int i = 0; i < nrLinii; i++){
            if(orarLinii[i][ora] == null){
                return true;
            }
        }
        return false;
    }
    public void afiseazaOrar(){
        System.out.print("Ore:        ");
        for(int i = 0; i < 24; i++){
            System.out.print(i + ":00" + "    ");
            if (i < 10){
                System.out.print(" ");
            }
        }
        System.out.println();
        for(int i = 0; i < nrLinii; i++){
            System.out.print("Linia " + (i + 1) + ": " );
            for(int j = 0; j < 24; j++){
                if(orarLinii[i][j] == null){
                    System.out.print("    _    ");
                }
                else{
                    System.out.print("  OCUPAT  ");
                }
            }
            System.out.println();
        }
    }
    @Override
    public String toString() {
        return nume + " -> " + nrLinii + " linii";
    }
}
