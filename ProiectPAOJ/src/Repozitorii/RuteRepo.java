package Repozitorii;

import Entitati.Gara;
import Entitati.Ruta;
import Entitati.Tren;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RuteRepo {
    private static List<Ruta> rute;
    public static void initializeazaRute(){
        rute = new ArrayList<Ruta>();
        Map<String, Gara> gari = GariRepo.getGari();
        Ruta r1 = new Ruta(List.of(new Gara[]{gari.get("BucurestiNord"), gari.get("BucurestiBasarab"), gari.get("Videle")}), 11, 15);
        Ruta r2 = new Ruta(List.of(new Gara[]{gari.get("Craiova"), gari.get("Caracal"), gari.get("DraganestiOlt"), gari.get("RosioriNord"), gari.get("Videle"), gari.get("BucurestiNord")}), 17, 22);
        rute.add(r1);
    }
    public static List<Ruta> getRute(){
        return rute;
    }
    public static void setRute(List<Ruta> rute){
        RuteRepo.rute = rute;
    }

    public static void addRuta(Ruta ruta){
        rute.add(ruta);
    }
    public static void adaugaTren(int index, Tren t){
        rute.get(index).setTren(t);
    }

    public static void afiseaza(){
        for (Ruta r : rute) {
            System.out.println(r);
        }
    }
}
