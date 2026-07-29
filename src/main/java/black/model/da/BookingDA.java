package black.model.da;

import black.model.entity.Booking;
import black.model.entity.Guest;
import black.model.entity.Room;
import black.model.entity.enums.GuestType;
import black.model.entity.enums.RoomClass;
import black.utils.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDA implements AutoCloseable {
    private ConnectionProvider connectionProvider = new ConnectionProvider();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(Booking booking) throws SQLException {
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "select booking_seq.nextVal as NEXT_ID from dual"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        booking.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO BOOKINGS (ID, GUEST_ID, ROOM_ID, ARRIVAL_DATE, DEPARTURE_DATE, PARTY_SIZE) VALUES (?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, booking.getId());
        preparedStatement.setInt(2, booking.getGuest().getId());
        preparedStatement.setInt(3, booking.getRoom().getId());
        preparedStatement.setDate(4, Date.valueOf(booking.getArrivalDate()));
        preparedStatement.setDate(5, Date.valueOf(booking.getDepartureDate()));
        preparedStatement.setInt(6, booking.getPartySize());
        preparedStatement.execute();
    }

    public void update(Booking booking) throws SQLException {
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE BOOKINGS SET GUEST_ID=?, ROOM_ID=?, ARRIVAL_DATE=?, DEPARTURE_DATE=?, PARTY_SIZE=? WHERE ID=?"
        );
        preparedStatement.setInt(1, booking.getGuest().getId());
        preparedStatement.setInt(2, booking.getRoom().getId());
        preparedStatement.setDate(3, Date.valueOf(booking.getArrivalDate()));
        preparedStatement.setDate(4, Date.valueOf(booking.getDepartureDate()));
        preparedStatement.setInt(5, booking.getPartySize());
        preparedStatement.setInt(6, booking.getId());
        preparedStatement.execute();
    }

    public void delete(int id) throws SQLException {
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM BOOKINGS WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Booking> findAll() throws SQLException {
        connection = connectionProvider.getConnection();
        List<Booking> bookingList = new ArrayList<>();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BOOKING_REPORT ORDER BY ROOM_NUMBER"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Guest guest = new Guest(
                    resultSet.getInt("GUEST_ID"),
                    resultSet.getString("GUEST_FIRST_NAME"),
                    resultSet.getString("GUEST_LAST_NAME"),
                    GuestType.valueOf(resultSet.getString("GUEST_TYPE"))
            );
            Room room = new Room(
                    resultSet.getInt("ROOM_ID"),
                    resultSet.getInt("ROOM_NUMBER"),
                    RoomClass.valueOf(resultSet.getString("ROOM_CLASS"))
            );
            Booking booking = new Booking(
                    resultSet.getInt("BOOKING_ID"),
                    guest,
                    room,
                    resultSet.getDate("ARRIVAL_DATE").toLocalDate(),
                    resultSet.getDate("DEPARTURE_DATE").toLocalDate(),
                    resultSet.getInt("PARTY_SIZE")
                    );

            bookingList.add(booking);
        }
        return bookingList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
