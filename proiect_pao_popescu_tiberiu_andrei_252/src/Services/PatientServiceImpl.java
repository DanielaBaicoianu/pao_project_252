package src.Services;

import src.Entities.*;
import src.Repository.AppointmentRepo;
import src.Repository.PatientRepo;
import src.Utils.AppointmentType;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PatientServiceImpl implements PatientService{

    @Override
    public Appointment makeAppointment(Medic medic, Patient patient, LocalDateTime date, int durationMinutes, AppointmentType type, Disease disease, double cost, Clinique clinique, int roomNumber) {
        AppointmentService appointmentService = new AppointmentServiceImpl();
        Appointment appointment = appointmentService.createAppointmentWithMedic(medic, date, durationMinutes, type,
                disease, cost, clinique, roomNumber);
        if(appointment != null) {
            patient.addAppointment(appointment);
            medic.makeAppointment(date, durationMinutes);
            return appointment;
        }
        else {
            return null;
        }
    }

    @Override
    public Appointment modifyAppointment(Patient patient, int appointmentId, LocalDateTime newDate) {
        ArrayList<Appointment> appointments = patient.getMedFile().getAppointmentHistory();
        for(Appointment currentAppointment : appointments) {
            if(appointmentId == currentAppointment.getId()) {
                MedicService medicService = new MedicServiceImpl();
                if(medicService.checkAvailability(currentAppointment.getMedic(),
                        currentAppointment.getDurationMinutes(), newDate)) {
                    currentAppointment.setDate(newDate);
                    return currentAppointment;
                }
            }
        }
        return null;
    }

    @Override
    public void cancelAppointment(Patient patient, int appointmentId) {
        MedFile medFile = patient.getMedFile();
        ArrayList<Appointment> appointmentHistory = medFile.getAppointmentHistory();
        for(Appointment appointment : appointmentHistory) {
            if(appointment.getId() == appointmentId) {
                appointmentHistory.remove(appointment);
                return;
            }
        }
    }

    @Override
    public ArrayList<Appointment> getAllAppointments(Patient patient) {
        MedFile medFile = patient.getMedFile();
        return medFile.getAppointmentHistory();
    }
}
