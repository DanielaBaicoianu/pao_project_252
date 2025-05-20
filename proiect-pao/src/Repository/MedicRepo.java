
package src.Repository;

import src.Entities.Calendar;
import src.Entities.Medic;
import src.Utils.Specialization;

import java.util.ArrayList;
import java.util.Date;

public class MedicRepo {
    private ArrayList<Medic> medics;

    public MedicRepo() {
        this.medics = new ArrayList<>();
        this.fetchMedics();
    }

    private void fetchMedics() {
        CalendarRepo calendarRepo = new CalendarRepo();
        ArrayList<Calendar> calendars = calendarRepo.getCalendars();

        Medic medic1 = new Medic("John", "Doe", 35, new Date(), "0734567890", "johndoe@example.com", "123 Main St", Specialization.CARDIOLOGY, 10, calendars.get(0));
        Medic medic2 = new Medic("Jane", "Smith", 40, new Date(), "0787654321", "janesmith@example.com", "456 Elm St", Specialization.DERMATOLOGY, 12, calendars.get(1));
        Medic medic3 = new Medic("Alice", "Johnson", 30, new Date(), "0722334455", "alicejohnson@example.com", "789 Oak St", Specialization.PEDIATRICS, 5, calendars.get(2));

        medics.add(medic1);
        medics.add(medic2);
        medics.add(medic3);
    }

    public ArrayList<Medic> getMedics() {
        return medics;
    }

    public void addMedic(Medic medic) {
        medics.add(medic);
    }
}
    