package src.Entities;

import src.Utils.AppointmentType;
import src.Utils.AppointmentUtils;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private Medic medic;
    private LocalDateTime date;
    private int durationMinutes;
    private AppointmentType type;
    private Disease disease;
    private double cost;
    Clinique clinique;
    int roomNumber;

    public Appointment(Medic medic, LocalDateTime date, int durationMinutes, AppointmentType type, Disease disease, double cost,
                       Clinique clinique, int roomNumber) {
        this.id = AppointmentUtils.currentId;
        AppointmentUtils.currentId++;
        this.medic = medic;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.type = type;
        this.disease = disease;
        this.cost = cost;
        this.clinique = clinique;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "medic=" + medic +
                ", id=" + id +
                ", date=" + date +
                ", disease=" + disease +
                ", cost=" + cost +
                ", clinique=" + clinique +
                ", roomNumber=" + roomNumber +
                ", durationMinutes=" + durationMinutes +
                ", type=" + type +
                '}';
    }

    public Medic getMedic() {
        return medic;
    }

    public int getId() {
        return id;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDate(LocalDateTime newDate) {
        this.date = newDate;
    }
}
