package Utile;

import Repozitorii.MecaniciRepo;
import Repozitorii.OperatoriRepo;
import Repozitorii.RuteRepo;

public class Initializari {
    public static void initializeazaTot(){
        MecaniciRepo.initializeMecanici();
        OperatoriRepo.initializeazaOperatori();
        RuteRepo.initializeazaRute();
    }
}
