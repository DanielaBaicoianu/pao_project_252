package Repozitorii;

import Entitati.Locomotiva;
import Entitati.Mecanic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import Utile.SortMecanici;
import Utile.Stare;

public class MecaniciRepo {
    private static List<Mecanic> mecanici;
    public static void initializeMecanici(){
        mecanici = new ArrayList<Mecanic>();
        Mecanic m1 = new Mecanic("Georgescu Gigel", LocalDate.parse("1967-05-20"), LocalDate.parse("2000-05-01"));
        Mecanic m2 = new Mecanic("Almajaru Horia", LocalDate.parse("1980-01-12"), LocalDate.parse("2016-12-02"));
        Mecanic m3 = new Mecanic("Exemplescu Alexandru", LocalDate.parse("1977-01-11"), LocalDate.parse("2016-12-02"));
        Mecanic m4 = new Mecanic("Popescu Ionel", LocalDate.parse("1966-05-20"), LocalDate.parse("2010-05-01"));
        mecanici.add(m1);
        mecanici.add(m2);
        mecanici.add(m3);
        mecanici.add(m4);
        Comparator comparatorCustom = new SortMecanici();
        Collections.sort(mecanici, comparatorCustom);
    }
    public static List<Mecanic> getMecanici(){
        return mecanici;
    }

    public static void setMecanici(List<Mecanic> mecanici){
        MecaniciRepo.mecanici = mecanici;
    }

    public static void addMecanic(Mecanic mecanic){
        mecanici.add(mecanic);
    }

    public static Mecanic getMecanic(String nume){
        for (Mecanic op : mecanici) {
            if(op.getNume().equals(nume)){
                return op;
            }
        }
        return null;
    }
    public static void afiseaza(){
        for (Mecanic m : mecanici) {
            System.out.println(m);
        }
    }
    public static void adauga() throws IOException {
        System.out.println("INTRODUCETI NUMELE, DATA NASTERII SI DATA ANGAJARII SEPARATE PRINTR-UN SINGUR SPATIU");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String[] date = cititor.readLine().split(" ");
        Mecanic m = new Mecanic(date[0], LocalDate.parse(date[1]), LocalDate.parse(date[2]));
        mecanici.add(m);
    }
}
