package Repozitorii;

import Entitati.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class VagoaneRepo {
    private static List<Vagon> vagoane;

    public static List<Vagon> getVagoane(){
        return vagoane;
    }

    public static void setVagoane(List<Vagon> vagoane){
        VagoaneRepo.vagoane = vagoane;
    }

    public static void addVagon(Vagon vagon){
        vagoane.add(vagon);
    }
    public static Vagon getVagon(int index){
        return vagoane.get(index);
    }
    public static void afiseaza(){
        for (Vagon v : vagoane) {
            System.out.println(v);
        }
    }
    public static void adauga() throws IOException {
        System.out.println("INTRODUCETI GREUTATEA SI TIPUL DE MARFA SAU GREUTATEA, CAPACITATEA SI NUMARUL DE LOCURI OCUPATE (DACA DORITI VAGON MARFAR/DE CALATORI) SEPARATE PRINTR-UN SINGUR SPATIU");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String[] date = cititor.readLine().split(" ");
        Vagon v;
        if(date.length == 2)
            v = new VagonMarfa(date[1], Integer.parseInt(date[0]));
        else
            v = new VagonCalatori(Integer.parseInt(date[1]), Integer.parseInt(date[0]), Integer.parseInt(date[2]));
        vagoane.add(v);
    }
}
