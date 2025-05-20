package src.Entities;

import src.Utils.RoomType;

public class Room {
    private RoomType type;
    private int floor;
    private Integer roomNumber;

    public Room(RoomType type, int floor, int roomNumber) {
        this.type = type;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }
}
