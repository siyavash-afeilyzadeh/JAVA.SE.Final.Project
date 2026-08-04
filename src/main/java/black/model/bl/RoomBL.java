package black.model.bl;

import black.model.da.RoomDA;
import black.model.entity.Room;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomBL {
    //--------------Validation Methods--------------
    public void validateRoomNumber(Room room, RoomDA roomDA) throws Exception{
        Room dup = roomDA.findByRoomNumber(room.getRoomNumber());
        if (dup != null && dup.getId() != room.getId()){
            throw new Exception("This Room Number is already exists");
        }
    }
    //--------------Business Logic Methods--------------
    public void save(Room room) throws Exception{
        log.debug("Room Business Logic Save");
        try(RoomDA roomDA = new RoomDA()){
            validateRoomNumber(room, roomDA);
            roomDA.save(room);
            log.info("Room Business Logic save" + room.getDisplayRoom() + "successfully");
        }
    }
    public void update(Room room) throws Exception{
        log.debug("Room Business Logic Update");
        try(RoomDA roomDA = new RoomDA()){
            validateRoomNumber(room, roomDA);
            roomDA.update(room);
            log.info("Room Business Logic update" + room.getDisplayRoom() + "successfully");
        }
    }
    public void delete(int id) throws Exception{
        log.debug("Room Business Logic Delete");
        try(RoomDA roomDA = new RoomDA()){
            roomDA.delete(id);
            log.info("Room Business Logic delete Room successfully");
        }
    }
    public Room findByID(int id) throws Exception{
        log.debug("Room Business Logic run 'Find by ID'");
        try(RoomDA roomDA = new RoomDA()){
            log.info("Room Business Logic run 'Find by ID' successfully.");
            return roomDA.findByID(id);
        }
    }
    public Room findByRoomNumber (int roomNumber) throws Exception{
        log.debug("Room Business Logic run 'Find by Room Number'");
        try(RoomDA roomDA = new RoomDA()){
            log.info("Room Business Logic run 'Find by Room Number' successfully.");
            return roomDA.findByRoomNumber(roomNumber);
        }
    }
}
