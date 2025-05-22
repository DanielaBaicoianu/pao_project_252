package Utile;

import Entitati.*;
import Exceptii.ExceptieGaraLipsa;
import Repozitorii.*;
import Servicii.ServiciuClient;
import Servicii.ServiciuRuta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    public static void arataOptiuni() throws IOException {
        System.out.println("INTRODUCETI NUMELE UNEIA DINTRE URMATOARELE OPTIUNI:\nAFISARE | PROGRAMARE | CAUTARE RUTA | ADAUGARE | IESIRE\n");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String optiune = cititor.readLine().toUpperCase();
        switch (optiune) {
            case "AFISARE":
                afisariDate();
                arataOptiuni();
                break;
            case "PROGRAMARE":
                programeazaRuta();
                arataOptiuni();
                break;
            case "CAUTARE RUTA":
                cautaRuta();
                arataOptiuni();
                break;
            case "ADAUGARE":
                adaugaDate();
                arataOptiuni();
                break;
            case "IESIRE":
                break;
            default:
                System.out.println("OPTIUNE INVALIDA, INCERCATI DIN NOU!\n");
                arataOptiuni();
                break;
        }

    }
    public static void afisariDate() throws IOException {
        System.out.println("ALEGETI CE VRETI SA AFISATI:\nGARI | LOCOMOTIVE | MECANICI | OPERATORI | RUTE | VAGOANE | PROGRAM GARA\n");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String optiune = cititor.readLine().toUpperCase();
        switch (optiune) {
            case "GARI":
                GariRepo.afiseaza();
                break;
            case "LOCOMOTIVE":
                LocomotiveRepo.afiseaza();
                break;
            case "MECANICI":
                MecaniciRepo.afiseaza();
                break;
            case "OPERATORI":
                OperatoriRepo.afiseaza();
                break;
            case "RUTE":
                RuteRepo.afiseaza();
                break;
            case "VAGOANE":
                VagoaneRepo.afiseaza();
                break;
            case "PROGRAM GARA":
                afisariProgramGara();
                break;
            default:
                System.out.println("OPTIUNE INVALIDA, INCERCATI DIN NOU!\n");
                afisariDate();
                break;
        }
    }
    public static void afisariProgramGara() throws IOException {
        GariRepo.afiseaza();
        System.out.println("ALEGETI UNA DINTRE GARI PENTRU A AFISA ORARUL\n");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String optiune = cititor.readLine().toUpperCase();
        GariRepo.getGara(optiune).afiseazaOrar();
    }
    public static void cautaRuta() throws IOException {
        System.out.println("INTRODUCETI NUMELE GARILOR DE PLECARE SI DE SOSIRE SEPARATE PRINTR-UN SINGUR SPATIU\n");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String optiune = cititor.readLine();
        String garaPlecare = optiune.split(" ")[0];
        String garaSosire = optiune.split(" ")[1];
        if(GariRepo.getGara(garaPlecare) != null && GariRepo.getGara(garaSosire) != null) {
            ServiciuClient.gasesteRuta(garaPlecare, garaSosire);
        }
        else{
            System.out.println("OPTIUNE INVALIDA, INCERCATI DIN NOU!\n");
            cautaRuta();
        }
    }
    public static void programeazaRuta() throws IOException {
        System.out.println("INTRODUCETI GARILE PRIN CARE DORITI SA TREACA RUTA, SEPARATE PRINTR-UN SINGUR SPATIU\n");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String optiuni = cititor.readLine();
        List<String> numeGari = Arrays.asList(optiuni.split(" "));
        List<Gara> gari = new ArrayList<>();
        for (String nume : numeGari) {
            if (GariRepo.getGara(nume) != null) {
                gari.add(GariRepo.getGara(nume));
            }
            else
                throw new ExceptieGaraLipsa();
        }
        System.out.println("INTRODUCETI ORELE PENTRU PROGRAMAREA RUTEI. FORMATUL TREBUIE SA FIE CU 24 DE ORE IAR ORELE SA FIE NUMERE INTREGI SEPARATE PRIN SPATIU (EX. 11 13 15 17)\n");
        optiuni = cititor.readLine();
        List<Integer> ore = new ArrayList<>();
        for (String numar : optiuni.split(" ")) {
            ore.add(Integer.parseInt(numar));
        }
        Ruta r = new Ruta(gari, ore.get(0), ore.get(ore.size() - 1));
        ore.remove(0);
        ore.remove(ore.size() - 1);
        ServiciuRuta.adaugaRutaLaOrar(r, ore);
        System.out.println("SELECTATI UN TREN DISPONIBIL (INTRODUCETI NUMARUL DIN FATA ACESTUIA):\n");
        int contor = 0;
        List <Tren> trenuriDisponibile = OperatoriRepo.getTrenuriDisponibile();
        for(Tren t : trenuriDisponibile) {
            System.out.println((contor++) + ". " + t);
        }
        optiuni = cititor.readLine();
        r.setTren(trenuriDisponibile.get(Integer.parseInt(optiuni)));
        RuteRepo.addRuta(r);
    }
    public static void adaugaDate() throws IOException {
        System.out.println("ALEGETI CE VRETI SA ADAUGATI IN BAZA DE DATE A APLICATIEI\nGARI | LOCOMOTIVE | MECANICI | OPERATORI | VAGOANE | TRENURI\n");
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String optiune = cititor.readLine().toUpperCase();
        switch (optiune) {
            case "GARI":
                GariRepo.adauga();
                break;
            case "LOCOMOTIVE":
                LocomotiveRepo.adauga();
                break;
            case "MECANICI":
                MecaniciRepo.adauga();
                break;
            case "OPERATORI":
                OperatoriRepo.adauga();
                break;
            case "VAGOANE":
                VagoaneRepo.adauga();
                break;
            case "TRENURI":
                formeazaTren();
                break;
            default:
                System.out.println("OPTIUNE INVALIDA, INCERCATI DIN NOU!\n");
                adaugaDate();
                break;
        }
    }
    public static void formeazaTren() throws IOException {
        System.out.println("SELECTATI UN OPERATOR\n");
        OperatoriRepo.afiseaza();
        BufferedReader cititor = new BufferedReader(new InputStreamReader(System.in));
        String optiune = cititor.readLine();
        if(OperatoriRepo.getOperator(optiune) == null)
            throw new Exceptii.ExceptieInput();
        Operator op = OperatoriRepo.getOperator(optiune);
        System.out.println("SELECTATI O LOCOMOTIVA (INTRODUCETI NUMARUL CORESPUNZATOR)\n");
        int contor = 0;
        for(Locomotiva l : LocomotiveRepo.getLocomotive()){
            if(!l.isEsteFolosita())
                System.out.println((contor++) + ". " + l);
        }
        optiune = cititor.readLine();
        Locomotiva l = LocomotiveRepo.getLocomotiva(Integer.parseInt(optiune));
        System.out.println("SELECTATI UNUL SAU MAI MULTE VAGOANE DIN LISTA (INTRODUCETI NUMERELE CORESPUNZATOARE SEPARATE PRINTR-UN SINGUR SPATIU)\n");
        contor = 0;
        for(Vagon v : VagoaneRepo.getVagoane()){
            if(!v.isEsteFolosit())
                System.out.println((contor++) + ". " + v);
        }
        optiune = cititor.readLine();
        List<Vagon> vlist = new ArrayList<>();
        for(String index : optiune.split(" ")){
            vlist.add(VagoaneRepo.getVagoane().get(Integer.parseInt(index)));
        }
        System.out.println("SELECTATI UN MECANIC(INTRODUCETI NUMELE SI PRENUMELE)\n");
        for(Mecanic m : MecaniciRepo.getMecanici()){
            if(!m.isEsteFolosit())
                System.out.println(m);
        }
        optiune = cititor.readLine();
        if(MecaniciRepo.getMecanic(optiune) == null)
            throw new Exceptii.ExceptieInput();
        Mecanic m = MecaniciRepo.getMecanic(optiune);
        op.formeazaTren(l, vlist, m);
        System.out.println("TRENUL A FOST FORMAT CU SUCCES!\n");
    }
}
