package Repositories;

import Entitati.CourseOffering;

import java.util.Map;
import java.util.TreeMap;

public final class CourseOfferingRepo {
    private final Map<Integer, CourseOffering> offerings = new TreeMap<>();
    private static CourseOfferingRepo instance;
    private CourseOfferingRepo() {}

    public static CourseOfferingRepo getInstance() {
        if (instance == null) instance = new CourseOfferingRepo();
        return instance;
    }

    public void add(CourseOffering o) {
        offerings.put(o.getIdOferta(), o);
    }
    public CourseOffering get(int id) {
        return offerings.get(id);
    }
    public Map<Integer, CourseOffering> all() {
        return offerings;
    }
}
