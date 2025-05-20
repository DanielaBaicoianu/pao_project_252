package src.Repository;

import src.Entities.*;
import src.Utils.AppointmentType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class AppointmentRepo {
    private ArrayList<Appointment> appointments;

    public AppointmentRepo() {
        this.appointments = new ArrayList<>();
        this.fetchAppointments();
    }

    private void fetchAppointments() {
        MedicRepo medicRepo = new MedicRepo();
        DiseaseRepo diseaseRepo = new DiseaseRepo();
        CliniqueRepo cliniqueRepo = new CliniqueRepo();
        RoomRepo roomRepo = new RoomRepo();

        ArrayList<Medic> medics = medicRepo.getMedics();
        ArrayList<Disease> diseases = diseaseRepo.getDiseases();
        ArrayList<Clinique> clinics = cliniqueRepo.getClinics();
        ArrayList<Room> rooms = roomRepo.getRooms();

        Medic medic1 = medics.get(0);
        Medic medic2 = medics.get(1);
        Disease disease1 = diseases.get(0);
        Disease disease2 = diseases.get(1);
        Clinique clinique1 = clinics.get(0);
        Room room1 = rooms.get(0);
        Room room2 = rooms.get(1);

        Appointment appointment1 = new Appointment(medic1, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                30, AppointmentType.CONSULTATION, disease1, 100.0, clinique1, room1.getRoomNumber());
        Appointment appointment2 = new Appointment(medic2, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                45, AppointmentType.CHECKUP, disease2, 150.0, clinique1, room2.getRoomNumber());
        Appointment appointment3 = new Appointment(medic1, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                60, AppointmentType.SURGERY, disease1, 500.0, clinique1, room1.getRoomNumber());

        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}
