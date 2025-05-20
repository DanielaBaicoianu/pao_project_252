package src.Services;

import src.Entities.*;
import src.Repository.PatientRepo;
import src.Utils.AppointmentType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PatientService {
    public Appointment makeAppointment(Medic medic, Patient patient, LocalDateTime date, int durationMinutes,
                                AppointmentType type, Disease disease, double cost, Clinique clinique, int roomNumber);
    public Appointment modifyAppointment(Patient patient, int appointmentId, LocalDateTime newDate);
    public void cancelAppointment(Patient patient, int appointmentId);
    public ArrayList<Appointment> getAllAppointments(Patient patient);
}
