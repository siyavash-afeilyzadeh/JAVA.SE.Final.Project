package black.model.entity;

import black.model.entity.enums.RoomClass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class Room {
    private int id;
    private int roomNumber;
    private double price;
    private int capacity;
    private RoomClass roomClass;

    public String getDisplayRoom(){
        return "Room No." + roomNumber + "/ " + roomClass;
    }
}
