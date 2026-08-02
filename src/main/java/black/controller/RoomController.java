package black.controller;

import black.model.bl.RoomBL;
import black.model.entity.Room;
import black.model.entity.enums.RoomClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class RoomController {
    private RoomBL roomBL = new RoomBL();

    public void save(int roomNumber, BigDecimal pricePerNight, int roomCapacity, RoomClass roomClass) {
        log.debug("Room Controller Save");
        try {
            Room room = Room
                    .builder()
                    .roomNumber(roomNumber)
                    .pricePerNight(pricePerNight)
                    .roomCapacity(roomCapacity)
                    .roomClass(roomClass)
                    .build();

            roomBL.save(room);
            log.info("Room saved:" + room.getDisplayRoom());
        } catch (Exception e) {
            log.error("Failed to save room: " + e.getMessage());
        }
    }

    public void update(int roomNumber, BigDecimal pricePerNight, int roomCapacity, RoomClass roomClass) {
        log.debug("Room Controller Update");
        try {
            Room room = Room
                    .builder()
                    .roomNumber(roomNumber)
                    .pricePerNight(pricePerNight)
                    .roomCapacity(roomCapacity)
                    .roomClass(roomClass)
                    .build();

            roomBL.update(room);
            log.info("Room updated:" + room.getDisplayRoom());
        } catch (Exception e) {
            log.error("Failed to update room: " + e.getMessage());
        }
    }

    public void delete(int id) {
        log.debug("Room Controller Delete");
        try {
            roomBL.delete(id);
            log.info("Room deleted");
        } catch (Exception e) {
            log.error("Failed to delete room: " + e.getMessage());
        }
    }

    public Room findByID(int id) {
        log.debug("Find Room by ID");
        try{
            Room room = roomBL.findByID(id);
            log.info("Room " + id + " is found.");
            return room;
        } catch (Exception e){
            log.error("Room not found" + e.getMessage());
            return null;
        }

    }

    public Room findByRoomNumber(int roomNumber) {
        log.debug("Find Room by Room Number");
        try{
            Room room = roomBL.findByRoomNumber(roomNumber);
            log.info("Room Number " + roomNumber + " is found.");
            return room;
        } catch (Exception e) {
            log.error("Room not found" + e.getMessage());
            return null;
        }
    }
}
