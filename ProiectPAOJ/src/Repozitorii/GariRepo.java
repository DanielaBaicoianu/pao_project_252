package Repozitorii;
import Entitati.Gara;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GariRepo {
    private static Map<String, Gara> gari;

    public static Map<String, Gara> getGari(){
        return gari;
    }

    public static void setGari(Map<String, Gara> gari){
        GariRepo.gari = gari;
    }

    public static Gara getGara(String nume){
        return gari.get(nume);
    }
    public static List<Gara> getGariList(){
        return new ArrayList<Gara>(gari.values());
    }
    public static void afiseaza(){
        for (Gara g : gari.values()) {
            System.out.println(g);
        }
    }
    public static void adauga() throws IOException {
        System.out.println("INTRODUCETI NUMELE SI NUMARUL DE LINII, SEPARATE PRINTR-UN SINGUR SPATIU");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String[] date = cititor.readLine().split(" ");
        Gara g = new Gara(Integer.parseInt(date[1]), date[0]);
        gari.put(date[0], g);
    }

}
