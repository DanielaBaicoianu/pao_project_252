package Repozitorii;

import Entitati.Gara;
import Entitati.Operator;
import Entitati.Tren;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OperatoriRepo {
    private static List<Operator> operatori;
    public static void initializeazaOperatori(){
        operatori = new ArrayList<Operator>();
        Operator op1 = new Operator("CFRCalatori", "Romania");
        Operator op2 = new Operator("AstraTrans", "Romania");
        Operator op3 = new Operator("DBCargo", "Germania");
        op1.permiteToate();
        op2.permiteToate();
        Map<String, Gara> gari = GariRepo.getGari();
        op3.permiteGara(gari.get("BucurestiNord"));
        op3.permiteGara(gari.get("RosioriNord"));
        op3.permiteGara(gari.get("Craiova"));
        op3.permiteGara(gari.get("Timisoara"));
        operatori.add(op1);
        operatori.add(op2);
        operatori.add(op3);

    }
    public static List<Operator> getOperatori(){
        return operatori;
    }

    public static void setOperatori(List<Operator> operatori){
        OperatoriRepo.operatori = operatori;
    }

    public static void addOperator(Operator operator){
        operatori.add(operator);
    }

    public static Operator getOperator(String nume){
        for (Operator op : operatori) {
            if(op.getNume().equals(nume)){
                return op;
            }
        }
        return null;
    }
    public static void afiseaza(){
        for (Operator op : operatori) {
            System.out.println(op + " are permisiunea de a opera trenuri in garile");
            op.listGariPermise();
        }
    }
    public static void adauga() throws IOException {
        System.out.println("INTRODUCETI NUMELE SI TARA DE PROVENIENTA SEPARATE PRINTR-UN SINGUR SPATIU");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String[] date = cititor.readLine().split(" ");
        Operator op = new Operator(date[0], date[1]);
        operatori.add(op);
    }
    public static List<Tren> getTrenuriDisponibile(){
        List<Tren> trenuri = new ArrayList<>();
        for (Operator op : operatori) {
            for(Tren t : op.getTrenuri()){
                if(!t.isEsteFolosit()){
                    trenuri.add(t);
                }
            }
        }
        return trenuri;
    }
}
