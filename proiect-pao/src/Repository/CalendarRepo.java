
package src.Repository;

import src.Entities.Calendar;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CalendarRepo {
    private ArrayList<Calendar> calendars;

    public CalendarRepo() {
        this.calendars = new ArrayList<>();
        this.fetchCalendars();
    }

    private void fetchCalendars() {
        ArrayList<Integer> freeDays1 = new ArrayList<>();
        freeDays1.add(1);

        ArrayList<Integer> freeDays2 = new ArrayList<>();
        freeDays2.add(2);

        ArrayList<Integer> freeDays3 = new ArrayList<>();
        freeDays3.add(3);

        HashMap<LocalDateTime, Integer> appointedDates1 = new HashMap<>();
        appointedDates1.put(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), 45);

        HashMap<LocalDateTime, Integer> appointedDates2 = new HashMap<>();
        appointedDates2.put(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), 60);

        HashMap<LocalDateTime, Integer> appointedDates3 = new HashMap<>();
        appointedDates3.put(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), 75);

        Calendar calendar1 = new Calendar(8, 16, freeDays1, appointedDates1);
        Calendar calendar2 = new Calendar(9, 17, freeDays2, appointedDates2);
        Calendar calendar3 = new Calendar(10, 18, freeDays3, appointedDates3);

        calendars.add(calendar1);
        calendars.add(calendar2);
        calendars.add(calendar3);
    }

    public ArrayList<Calendar> getCalendars() {
        return calendars;
    }
}
    