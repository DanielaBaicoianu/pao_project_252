package src.Entities;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Calendar {
    private int startHour;
    private int endHour;
    private ArrayList<Integer> freeDaysThisYear;
    private HashMap<LocalDateTime, Integer> appointedDates;

    public Calendar(int startHour, int endHour, ArrayList<Integer> freeDaysThisYear,
                    HashMap<LocalDateTime, Integer> appointedDates) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.freeDaysThisYear = freeDaysThisYear;
        this.appointedDates = appointedDates;
    }

    public void makeAppointment(LocalDateTime date, int durationMinutes) {
        this.appointedDates.put(date.truncatedTo(ChronoUnit.MINUTES), durationMinutes);
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public ArrayList<Integer> getFreeDaysThisYear() {
        return freeDaysThisYear;
    }

    public HashMap<LocalDateTime, Integer> getAppointedDates() {
        return appointedDates;
    }

    public boolean dateAvailable(LocalDateTime date, int durationMinutes) {
        int dayOfYear = date.getDayOfYear();
        if (freeDaysThisYear.contains(dayOfYear)) {
            return false;
        }

        DayOfWeek day = date.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return false;
        }

        int hour = date.getHour();
        int minute = date.getMinute();
        if (hour < startHour || (hour == startHour && minute < 0)) {
            return false;
        }
        if (hour >= endHour || (hour == endHour && minute > 0)) {
            return false;
        }

        LocalDateTime slotEnd = date.plusMinutes(durationMinutes);
        if (slotEnd.getHour() > endHour || (slotEnd.getHour() == endHour && slotEnd.getMinute() > 0)) {
            return false;
        }

        TreeMap<LocalDateTime, Integer> sortedAppointments = new TreeMap<>(appointedDates);

        for (Map.Entry<LocalDateTime, Integer> entry : sortedAppointments.entrySet()) {
            LocalDateTime apptStart = entry.getKey();
            LocalDateTime apptEnd = apptStart.plusMinutes(entry.getValue());

            boolean overlaps = date.isBefore(apptEnd) && slotEnd.isAfter(apptStart);
            if (overlaps) {
                return false;
            }
        }

        return true;
    }
}
