
package src.Repository;

import src.Entities.Room;
import src.Utils.RoomType;

import java.util.ArrayList;

public class RoomRepo {
    private ArrayList<Room> rooms;

    public RoomRepo() {
        this.rooms = new ArrayList<>();
        this.fetchRooms();
    }

    private void fetchRooms() {
        Room room1 = new Room(RoomType.CONSULTATION, 1, 101);
        Room room2 = new Room(RoomType.SURGERY, 2, 201);
        Room room3 = new Room(RoomType.EXAMINATION, 3, 301);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
    