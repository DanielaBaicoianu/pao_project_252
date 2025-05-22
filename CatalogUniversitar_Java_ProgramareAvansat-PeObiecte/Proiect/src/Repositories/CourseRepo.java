package Repositories;

import Entitati.Course;

import java.util.*;

public final class CourseRepo {
    private final Map<String, Course> courses = new TreeMap<>();
    private static CourseRepo instance;
    private CourseRepo() {}

    public static CourseRepo getInstance() {
        if (instance == null) instance = new CourseRepo();
        return instance;
    }

    public void add(Course c) {
        courses.put(c.getCodMaterie(), c);
    }
    public Course get(String cod) {
        return courses.get(cod);
    }
    public Collection<Course> getAll() {
        return courses.values();
    }
    public boolean exists(String cod) {
        return courses.containsKey(cod);
    }
}
