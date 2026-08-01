package black.model.bl;

import black.model.da.RoomDA;
import black.model.entity.Room;

public class RoomBL {
    public void save(Room room) throws Exception{
        try(RoomDA roomDA = new RoomDA()){
            roomDA.save(room);
        }
    }
    public void update(Room room) throws Exception{
        try(RoomDA roomDA = new RoomDA()){
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
