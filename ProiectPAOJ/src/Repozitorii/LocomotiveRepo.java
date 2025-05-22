package Repozitorii;

import Entitati.Gara;
import Entitati.Locomotiva;
import Utile.Stare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LocomotiveRepo {
    private static List<Locomotiva> locomotive;

    public static List<Locomotiva> getLocomotive(){
        return locomotive;
    }

    public static void setLocomotive(List<Locomotiva> locomotive){
        LocomotiveRepo.locomotive = locomotive;
    }

    public static void addLocomotiva(Locomotiva locomotiva){
        locomotive.add(locomotiva);
    }
    public static Locomotiva getLocomotiva(int index){
        return locomotive.get(index);
    }
    public static void afiseaza(){
        int contor = 1;
        for (Locomotiva locomotiva : locomotive) {
            System.out.println(locomotiva);
        }
    }
    public static void adauga() throws IOException {
        System.out.println("INTRODUCETI NUMELE, CAPACITATEA SI VITEZA MEDIE SEPARATE PRINTR-UN SINGUR SPATIU");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String[] date = cititor.readLine().split(" ");
        Locomotiva l = new Locomotiva(date[0], Integer.parseInt(date[1]), Integer.parseInt(date[2]), Stare.NOUA);
        locomotive.add(l);
    }
}
