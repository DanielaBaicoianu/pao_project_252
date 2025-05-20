
package src.Repository;

import src.Entities.Clinique;
import src.Entities.Room;

import java.util.ArrayList;

public class CliniqueRepo {
    private ArrayList<Clinique> clinics;

    public CliniqueRepo() {
        this.clinics = new ArrayList<>();
        this.fetchCliniques();
    }

    private void fetchCliniques() {
        RoomRepo roomRepo = new RoomRepo();
        ArrayList<Room> rooms = roomRepo.getRooms();

        ArrayList<Room> rooms1 = new ArrayList<>();
        rooms1.add(rooms.get(0));
        rooms1.add(rooms.get(2));

        ArrayList<Room> rooms2 = new ArrayList<>();
        rooms2.add(rooms.get(1));
        rooms2.add(rooms.get(2));

        ArrayList<Room> rooms3 = new ArrayList<>();
        rooms3.add(rooms.get(0));
        rooms3.add(rooms.get(1));

        Clinique clinique1 = new Clinique("City Hospital", "123 Iuliu Maniu", rooms1);
        Clinique clinique2 = new Clinique("Wellness Center", "456 Magheru", rooms2);
        Clinique clinique3 = new Clinique("Health Clinic", "789 Victoriei", rooms3);

        clinics.add(clinique1);
        clinics.add(clinique2);
        clinics.add(clinique3);
    }

    public ArrayList<Clinique> getClinics() {
        return clinics;
    }
}
    