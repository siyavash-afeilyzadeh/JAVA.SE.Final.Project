package black.model.bl;

import black.model.da.RoomDA;
import black.model.entity.Room;

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
        try(RoomDA roomDA = new RoomDA()){
            validateRoomNumber(room, roomDA);
            roomDA.save(room);
        }
    }
    public void update(Room room) throws Exception{
        try(RoomDA roomDA = new RoomDA()){
            validateRoomNumber(room, roomDA);
            roomDA.update(room);
        }
    }
    public void delete(int id) throws Exception{
        try(RoomDA roomDA = new RoomDA()){
            roomDA.delete(id);
        }
    }
    public Room findByID(int id) throws Exception{
        try(RoomDA roomDA = new RoomDA()){
            return roomDA.findByID(id);
        }
    }
    public Room findByRoomNumber (int roomNumber) throws Exception{
        try(RoomDA roomDA = new RoomDA()){
            return roomDA.findByRoomNumber(roomNumber);
        }
    }
}
