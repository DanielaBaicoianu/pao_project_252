package src.Repository;

import src.Entities.Appointment;
import src.Entities.MedFile;

import java.util.ArrayList;
import java.util.Date;

public class MedFileRepo {
    private ArrayList<MedFile> medFiles;

    public MedFileRepo() {
        this.medFiles = new ArrayList<>();
        this.fetchMedFiles();
    }

    private void fetchMedFiles() {
        AppointmentRepo appointmentRepo = new AppointmentRepo();
        ArrayList<Appointment> appointments = appointmentRepo.getAppointments();
        ArrayList<Appointment> appointments1 = new ArrayList<>();
        ArrayList<Appointment> appointments2 = new ArrayList<>();
        ArrayList<Appointment> appointments3 = new ArrayList<>();

        appointments1.add(appointments.get(0));

        appointments2.add(appointments.get(1));

        appointments3.add(appointments.get(2));

        MedFile medFile1 = new MedFile(appointments1, new Date());
        MedFile medFile2 = new MedFile(appointments2, new Date());
        MedFile medFile3 = new MedFile(appointments3, new Date());

        medFiles.add(medFile1);
        medFiles.add(medFile2);
        medFiles.add(medFile3);
    }

    public ArrayList<MedFile> getMedFiles() {
        return medFiles;
    }
}

