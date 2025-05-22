package Servicii;

import Entitati.Gara;
import Entitati.Ruta;
import Repozitorii.GariRepo;

import java.util.List;

public class ServiciuRuta {
    public static void adaugaRutaLaOrar(Ruta r, List<Integer> ore){
        List<Gara> destinatii = r.getDestinatii();
        if(ore.size() != destinatii.size() - 2){
            System.out.println("Introduceti ore doar pentru toate statiile intermediare");
        }
        else{
            for (int i = 0; i < ore.size(); i++) {
                boolean rez = destinatii.get(i + 1).verificaOra(ore.get(i));
                if(!rez){
                    System.out.println("Gara " + destinatii.get(i + 1).getNume() + " este complet ocupata la ora " + ore.get(i));
                    return;
                }
            }
            for (int i = 0; i < ore.size(); i++) {
                destinatii.get(i+1).adaugaLaOrar(r, ore.get(i));
            }
            destinatii.getFirst().adaugaLaOrar(r, r.getOraPlecare());
            destinatii.getLast().adaugaLaOrar(r, r.getOraSosire());
            System.out.println("Ruta a fost programata cu succes!");
        }
    }
}
