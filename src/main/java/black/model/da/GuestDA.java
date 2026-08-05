package black.model.da;

import black.model.entity.Guest;
import black.model.entity.enums.GuestType;
import black.utils.ConnectionProvider;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GuestDA implements AutoCloseable {
    private ConnectionProvider connectionProvider = new ConnectionProvider();
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void save(Guest guest) throws SQLException {
        log.debug("Guest Data Access Save");
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
        log.info("Guest Data Access save" + guest.getDisplayGuest() + "successfully");
    }

    public void update(Guest guest) throws SQLException {
        log.debug("Guest Data Access Update");
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
        log.info("Guest Data Access update" + guest.getDisplayGuest() + "successfully");
    }

    public void delete(int id) throws SQLException {
        log.debug("Guest Data Access Delete");
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM GUESTS WHERE id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("Guest Data Access delete Guest successfully");
    }

    public Guest findByID(int id) throws Exception {
        log.debug("Guest Data Access run 'Find by ID'");
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
            log.info("Guest found: " + guest.getDisplayGuest());
        } else {
            log.info("No guest found with ID: " + id);
        }
        return guest;
    }

    public List<Guest> findByName(String firstName) throws Exception {
        log.debug("Guest Data Access run 'Find by Name'");
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
            log.debug("Guest Data Access add founded Guest to list of 'findByName' successfully.");
        }
        log.info("Guest Data Access run 'Find by Name' successfully.");
        return guestList;
    }

    public List<Guest> findByFamily(String lastName) throws Exception {
        log.debug("Guest Data Access run 'Find by Family'");
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
            log.debug("Guest Data Access add founded Guest to list of 'findByFamily' successfully.");
        }
        log.info("Guest Data Access run 'Find by Family' successfully.");
        return guestList;
    }

    public Guest findByNationalID(String nationalId) throws Exception {
        log.debug("Guest Data Access run 'Find by National ID'");
        Guest guest = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM GUESTS WHERE NATIONAL_ID=?"
        );
        preparedStatement.setString(1, nationalId);
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
            log.info("Guest found: " + guest.getDisplayGuest());
        } else {
            log.info("No guest found with ID: " + nationalId);
        }
        return guest;
    }

    public Guest findByPassportNumber(String passportNumber) throws Exception {
        log.debug("Guest Data Access run 'Find by Passport Number'");
        Guest guest = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM GUESTS WHERE PASSPORT_NUMBER=?"
        );
        preparedStatement.setString(1, passportNumber);
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
            log.info("Guest found: " + guest.getDisplayGuest());
        } else {
            log.info("No guest found with passport number: " + passportNumber);
        }
        return guest;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }


}
