package src.Entities;

import java.util.ArrayList;

public class Clinique {
    private String name;
    private String address;
    ArrayList<Room> rooms;

    public Clinique(String name, String address, ArrayList<Room> rooms) {
        this.name = name;
        this.address = address;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return name + ", " + address;
    }
}
