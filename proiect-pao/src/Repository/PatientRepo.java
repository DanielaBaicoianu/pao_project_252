
package src.Repository;

import src.Entities.MedFile;
import src.Entities.Patient;

import java.util.ArrayList;
import java.util.Date;

public class PatientRepo {
    private ArrayList<Patient> patients;

    public PatientRepo() {
        this.patients = new ArrayList<>();
        this.fetchPatients();
    }

    private void fetchPatients() {
        MedFileRepo medFileRepo = new MedFileRepo();
        ArrayList<MedFile> medFiles = medFileRepo.getMedFiles();
        Patient patient1 = new Patient("John", "Doe", 30, new Date(), "123-456-7890", "john@example.com",
                "123 Main St", true, medFiles.get(0));
        Patient patient2 = new Patient("Jane", "Smith", 25, new Date(), "987-654-3210", "jane@example.com",
                "456 Elm St", false, medFiles.get(1));
        Patient patient3 = new Patient("Alice", "Johnson", 40, new Date(), "555-123-4567", "alice@example.com",
                "789 Pine St", true, medFiles.get(2));

        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }
}
    