package black.model.da;

import black.model.entity.Guest;
import black.model.entity.enums.GuestType;
import black.utils.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDA implements AutoCloseable {
    private ConnectionProvider connectionProvider = new ConnectionProvider();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(Guest guest) throws SQLException {
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "select GUEST_SEQ.nextval as NEXT_ID from DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        guest.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO GUESTS(ID, FIRST_NAME, LAST_NAME, GUEST_TYPE, PASSPORT_NUMBER, NATIONAL_ID,BIRTH_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, guest.getId());
        preparedStatement.setString(2, guest.getFirstName());
        preparedStatement.setString(3, guest.getLastName());
        preparedStatement.setString(4, guest.getGuestType().name());
        preparedStatement.setString(5, guest.getPassportNumber());
        preparedStatement.setString(6, guest.getNationalID());
        preparedStatement.setDate(7, Date.valueOf(guest.getBirthDate()));
        preparedStatement.execute();
    }

    public void update(Guest guest) throws SQLException {
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE GUESTS SET FIRST_NAME=?, LAST_NAME=?, GUEST_TYPE=?, PASSPORT_NUMBER=?, NATIONAL_ID=?, BIRTH_DATE=? WHERE ID=?"
        );
        preparedStatement.setString(1, guest.getFirstName());
        preparedStatement.setString(2, guest.getLastName());
        preparedStatement.setString(3, guest.getGuestType().name());
        preparedStatement.setString(4, guest.getPassportNumber());
        preparedStatement.setString(5, guest.getNationalID());
        preparedStatement.setDate(6, Date.valueOf(guest.getBirthDate()));
        preparedStatement.setInt(7, guest.getId());
        preparedStatement.execute();
    }

    public void delete(int id) throws SQLException {
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM GUESTS WHERE id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Guest findByID(int id) throws Exception {
        Guest guest = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM GUESTS WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            guest = Guest
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FIRST_NAME"))
                    .lastName(resultSet.getString("LAST_NAME"))
                    .guestType(GuestType.valueOf(resultSet.getString("GUEST_TYPE")))
                    .passportNumber(resultSet.getString("PASSPORT_NUMBER"))
                    .nationalID(resultSet.getString("NATIONAL_ID"))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .build();
        }
        return guest;
    }

    public List<Guest> findByName(String firstName) throws Exception {
        List<Guest> guestList = new ArrayList<>();
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM GUESTS WHERE FIRST_NAME=?");
        preparedStatement.setString(1, firstName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Guest guest = Guest
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FIRST_NAME"))
                    .lastName(resultSet.getString("LAST_NAME"))
                    .guestType(GuestType.valueOf(resultSet.getString("GUEST_TYPE")))
                    .passportNumber(resultSet.getString("PASSPORT_NUMBER"))
                    .nationalID(resultSet.getString("NATIONAL_ID"))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .build();
            guestList.add(guest);
        }
        return guestList;
    }

    public List<Guest> findByFamily(String lastName) throws Exception {
        List<Guest> guestList = new ArrayList<>();
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("" +
                "SELECT * FROM GUESTS WHERE LAST_NAME=?");
        preparedStatement.setString(1, lastName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Guest guest = Guest
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FIRST_NAME"))
                    .lastName(resultSet.getString("LAST_NAME"))
                    .guestType(GuestType.valueOf(resultSet.getString("GUEST_TYPE")))
                    .passportNumber(resultSet.getString("PASSPORT_NUMBER"))
                    .nationalID(resultSet.getString("NATIONAL_ID"))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .build();
            guestList.add(guest);
        }
        return guestList;
    }

    public Guest findByNationalID(String nationalId) throws Exception {
        Guest guest = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM GUESTS WHERE NATIONAL_ID=?"
        );
        preparedStatement.setString(1, nationalId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            guest = Guest
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FIRST_NAME"))
                    .lastName(resultSet.getString("LAST_NAME"))
                    .guestType(GuestType.valueOf(resultSet.getString("GUEST_TYPE")))
                    .passportNumber(resultSet.getString("PASSPORT_NUMBER"))
                    .nationalID(resultSet.getString("NATIONAL_ID"))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .build();
        }
        return guest;
    }

    public Guest findByPassportNumber(String passportNumber) throws Exception {
        Guest guest = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM GUESTS WHERE PASSPORT_NUMBER=?"
        );
        preparedStatement.setString(1, passportNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            guest = Guest
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .firstName(resultSet.getString("FIRST_NAME"))
                    .lastName(resultSet.getString("LAST_NAME"))
                    .guestType(GuestType.valueOf(resultSet.getString("GUEST_TYPE")))
                    .passportNumber(resultSet.getString("PASSPORT_NUMBER"))
                    .nationalID(resultSet.getString("NATIONAL_ID"))
                    .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                    .build();
        }
        return guest;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }


}
