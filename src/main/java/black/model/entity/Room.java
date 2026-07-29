package black.model.entity;

import black.model.entity.enums.RoomClass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Room {
    private int id;
    private int roomNumber;
    private BigDecimal pricePerNight;
    private int roomCapacity;
    private RoomClass roomClass;

    public String getDisplayRoom(){
        return "Room No." + roomNumber + "/ " + roomClass;
    }

    public Room(int id, int roomNumber, RoomClass roomClass){
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomClass = roomClass;
    }
}
