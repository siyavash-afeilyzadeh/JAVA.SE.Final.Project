package black.model.da;

import black.model.entity.Room;
import black.model.entity.enums.RoomClass;
import black.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoomDA implements AutoCloseable {
    private ConnectionProvider connectionProvider = new ConnectionProvider();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(Room room) throws Exception{
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "select ROOM_SEQ.nextval as NEXT_ID from DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        room.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO ROOMS(ID, ROOM_NUMBER, PRICE_PER_NIGHT, ROOM_CAPACITY,ROOM_CLASS) VALUES(?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, room.getId());
        preparedStatement.setInt(2, room.getRoomNumber());
        preparedStatement.setBigDecimal(3, room.getPricePerNight());
        preparedStatement.setInt(4, room.getRoomCapacity());
        preparedStatement.setString(5,room.getRoomClass().name());
        preparedStatement.execute();
    }
    public void update(Room room) throws Exception{
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE ROOMS SET ROOM_NUMBER=?, PRICE_PER_NIGHT=?, ROOM_CAPACITY=?, ROOM_CLASS=? WHERE ID=?"
        );
        preparedStatement.setInt(1, room.getRoomNumber());
        preparedStatement.setBigDecimal(2, room.getPricePerNight());
        preparedStatement.setInt(3, room.getRoomCapacity());
        preparedStatement.setString(4, room.getRoomClass().name());
        preparedStatement.setInt(5, room.getId());
        preparedStatement.execute();
    }
    public void delete(int id) throws Exception{
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ROOMS WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Room findByID(int id) throws Exception{
        Room room = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM ROOMS WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            room = Room
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .roomNumber(resultSet.getInt("ROOM_NUMBER"))
                    .pricePerNight(resultSet.getBigDecimal("PRICE_PER_NIGHT"))
                    .roomCapacity(resultSet.getInt("ROOM_CAPACITY"))
                    .roomClass(RoomClass.valueOf(resultSet.getString("ROOM_CLASS")))
                    .build();
        }
        return room;
    }

    public Room findByRoomNumber (int roomNumber) throws Exception{
        Room room = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM ROOMS WHERE ROOM_NUMBER=?");
        preparedStatement.setInt(1, roomNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            room = Room
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .roomNumber(resultSet.getInt("ROOM_NUMBER"))
                    .pricePerNight(resultSet.getBigDecimal("PRICE_PER_NIGHT"))
                    .roomCapacity(resultSet.getInt("ROOM_CAPACITY"))
                    .roomClass(RoomClass.valueOf(resultSet.getString("ROOM_CLASS")))
                    .build();
        }
        return room;
    }

    @Override
    public void close() throws Exception{
        preparedStatement.close();
        connection.close();
    }

}
