import src.Entities.*;
import src.Repository.DiseaseRepo;
import src.Repository.MedicRepo;
import src.Repository.PatientRepo;
import src.Services.*;
import src.Utils.AppointmentType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        CliniqueService cliniqueService = new CliniqueServiceImpl();
        for(Clinique clinique : cliniqueService.getAllCliniques()) {
            System.out.println(clinique);
        }
        MedicService medicService = new MedicServiceImpl();
        MedicRepo medicRepo = new MedicRepo();
        PatientRepo patientRepo = new PatientRepo();
        Medic medic = medicRepo.getMedics().get(1);
        if(medicService.checkAvailability(medic, 45, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(2).minusHours(1))) {
            System.out.println("Medic " + medic + " is available.");
        }
        else {
            System.out.println("Medic " + medic + " is not available.");
        }
        System.out.println("All medics:");
        for(Medic m : medicService.getAllMedics()) {
            System.out.println(m);
        }
        System.out.println("All appointments for medic " + medic);
        for(Appointment appointment : medicService.getAllAppointments(medic, patientRepo)) {
            System.out.println(appointment);
        }
        System.out.println("All patients for medic " + medic );
        for(Patient patient : medicService.getAllPatients(medic, patientRepo)) {
            System.out.println(patient);
        }
        System.out.println("First available timeframe for " + medic);
        System.out.println(medicService.getFirstAvailableTimeFrame(medic, 70));
        PatientService patientService = new PatientServiceImpl();
        DiseaseRepo diseaseRepo = new DiseaseRepo();
        Disease disease = diseaseRepo.getDiseases().get(0);
        Patient patient = patientRepo.getPatients().get(1);
        System.out.println(patientService.makeAppointment(medic, patient, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                45, AppointmentType.CONSULTATION, disease, 100.00, cliniqueService.getAllCliniques().get(1), 10));
        System.out.println(patientService.modifyAppointment(patient, 1, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)));
        System.out.println(patientService.getAllAppointments(patient));
        patientService.cancelAppointment(patient, 1);
        System.out.println(patientService.getAllAppointments(patient));
    }
}