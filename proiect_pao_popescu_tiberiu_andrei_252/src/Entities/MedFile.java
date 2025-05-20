package src.Entities;

import java.util.ArrayList;
import java.util.Date;

public class MedFile {
    private ArrayList<Appointment> appointmentHistory;
    private Date registrationDate;

    public MedFile(ArrayList<Appointment> appointmentHistory, Date registrationDate) {
        this.appointmentHistory = appointmentHistory;
        this.registrationDate = registrationDate;
    }

    public ArrayList<Appointment> getAppointmentHistory() {
        return this.appointmentHistory;
    }

    public void addAppointment(Appointment appointment) {
        appointmentHistory.add(appointment);
    }
}
