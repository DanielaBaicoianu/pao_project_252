package Utile;

import Entitati.*;
import Repozitorii.GariRepo;
import Repozitorii.LocomotiveRepo;
import Repozitorii.VagoaneRepo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtileFisier {
    public static void citesteGari(String numeFisier) {
        Map<String, Gara> gari = new HashMap<>();
        File file = new File(numeFisier);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nume = parts[0].trim();
                    int nrLinii = Integer.parseInt(parts[1].trim());
                    gari.put(nume,new Gara(nrLinii, nume));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        GariRepo.setGari(gari);
    }
    public static void citesteLocomotive(String numeFisier) {
        List<Locomotiva> locomotive = new ArrayList<>();
        File file = new File(numeFisier);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String numeModel = parts[0].trim();
                    int capacitate = Integer.parseInt(parts[1].trim());
                    int vitezaMedie = Integer.parseInt(parts[2].trim());
                    Stare stare = Stare.valueOf(parts[3].trim());
                    locomotive.add(new Locomotiva(numeModel, capacitate, vitezaMedie, stare));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocomotiveRepo.setLocomotive(locomotive);
    }
    public static void citesteVagoane(String numeFisier) {
        List<Vagon> vagoane = new ArrayList<>();
        File file = new File(numeFisier);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int capacitate = Integer.parseInt(parts[0].trim());
                    int greutate = Integer.parseInt(parts[1].trim());
                    int locuriOcupate = Integer.parseInt(parts[2].trim());
                    vagoane.add(new VagonCalatori(capacitate, greutate, locuriOcupate));
                }
                if (parts.length == 2) {
                    String tipMarfa = parts[0].trim();
                    int greutate = Integer.parseInt(parts[1].trim());
                    vagoane.add(new VagonMarfa(tipMarfa, greutate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        VagoaneRepo.setVagoane(vagoane);
    }
}
