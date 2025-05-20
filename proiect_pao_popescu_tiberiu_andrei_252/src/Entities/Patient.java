package src.Entities;

import java.util.Date;

public class Patient extends Person {
    private boolean insured;
    MedFile medFile;

    public Patient(String firstName, String lastName, int age, Date birth, String phoneNumber, String email,
                   String address, boolean insured, MedFile medFile) {
        super(firstName, lastName, age, birth, phoneNumber, email, address);
        this.insured = insured;
        this.medFile = medFile;
    }

    public MedFile getMedFile() {
        return this.medFile;
    }

    public void addAppointment(Appointment appointment) {
        medFile.addAppointment(appointment);
    }
}
