package src.Services;

import src.Entities.Clinique;
import src.Repository.CliniqueRepo;

import java.util.ArrayList;

public class CliniqueServiceImpl implements CliniqueService{
    public ArrayList<Clinique> getAllCliniques() {
        CliniqueRepo cliniqueRepo = new CliniqueRepo();
        return cliniqueRepo.getClinics();
    }
}
