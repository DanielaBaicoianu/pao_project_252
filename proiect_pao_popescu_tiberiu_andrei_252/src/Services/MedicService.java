package src.Services;

import src.Entities.Appointment;
import src.Entities.Medic;
import src.Entities.Patient;
import src.Repository.PatientRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MedicService {
    public boolean checkAvailability(Medic medic, int durationMinutes, LocalDateTime date);
    public ArrayList<Patient> getAllPatients(Medic medic, PatientRepo patientRepo);
    public ArrayList<Appointment> getAllAppointments(Medic medic, PatientRepo patientRepo);
    public ArrayList<Medic> getAllMedics();
    public LocalDateTime getFirstAvailableTimeFrame(Medic medic, int durationMinutes);
}
