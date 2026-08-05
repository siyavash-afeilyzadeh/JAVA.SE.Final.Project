package black.model.da;

import black.model.entity.Room;
import black.model.entity.enums.RoomClass;
import black.utils.ConnectionProvider;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Slf4j
public class RoomDA implements AutoCloseable {
    private ConnectionProvider connectionProvider = new ConnectionProvider();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(Room room) throws Exception {
        log.debug("Room Data Access Save");
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
        preparedStatement.setString(5, room.getRoomClass().name());
        preparedStatement.execute();
        log.info("Room Data Access save" + room.getDisplayRoom() + "successfully");
    }

    public void update(Room room) throws Exception {
        log.debug("Room Data Access Update");
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
        log.info("Room Data Access update" + room.getDisplayRoom() + "successfully");
    }

    public void delete(int id) throws Exception {
        log.debug("Room Data Access Delete");
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ROOMS WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Room Data Access delete Room successfully");
    }

    public Room findByID(int id) throws Exception {
        log.debug("Room Data Access run 'Find by ID'");
        Room room = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM ROOMS WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            room = Room
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .roomNumber(resultSet.getInt("ROOM_NUMBER"))
                    .pricePerNight(resultSet.getBigDecimal("PRICE_PER_NIGHT"))
                    .roomCapacity(resultSet.getInt("ROOM_CAPACITY"))
                    .roomClass(RoomClass.valueOf(resultSet.getString("ROOM_CLASS")))
                    .build();
            log.info("Room found: " + room.getDisplayRoom());
        } else {
            log.info("No room found with ID: " + id);
        }
        return room;
    }

    public Room findByRoomNumber(int roomNumber) throws Exception {
        log.debug("Room Data Access run 'Find by Room Number'");
        Room room = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM ROOMS WHERE ROOM_NUMBER=?");
        preparedStatement.setInt(1, roomNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            room = Room
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .roomNumber(resultSet.getInt("ROOM_NUMBER"))
                    .pricePerNight(resultSet.getBigDecimal("PRICE_PER_NIGHT"))
                    .roomCapacity(resultSet.getInt("ROOM_CAPACITY"))
                    .roomClass(RoomClass.valueOf(resultSet.getString("ROOM_CLASS")))
                    .build();
            log.info("Room found: " + room.getDisplayRoom());
        } else {
            log.info("No room found with room number: " + roomNumber);
        }

        return room;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
