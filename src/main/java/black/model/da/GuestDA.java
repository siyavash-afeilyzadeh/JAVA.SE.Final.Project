package black.model.da;

import black.model.entity.Guest;
import black.model.entity.enums.GuestType;
import black.utils.ConnectionProvider;

import java.sql.*;

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
        preparedStatement = connection.prepareStatement("SELECT * FROM GUESTS WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet =preparedStatement.executeQuery();
        if(resultSet.next()){
            guest = Guest
                    .builder()
                    .id(resultSet.getInt("id"))
                    .firstName(resultSet.getString("firstName"))
                    .lastName(resultSet.getString("lastName"))
                    .guestType(GuestType.valueOf(resultSet.getString("guestType")))
                    .passportNumber(resultSet.getString("passportNumber"))
                    .nationalID(resultSet.getString("nationalID"))
                    .birthDate(resultSet.getDate("birthDate").toLocalDate())
                    .build();
        }
        return guest;
    }

    public Guest findByName(String firstName) throws Exception {
        Guest guest = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM GUESTS WHERE FIRST_NAME=?");
        preparedStatement.setString(1, firstName);
        ResultSet resultSet =preparedStatement.executeQuery();
        if(resultSet.next()){
            guest = Guest
                    .builder()
                    .id(resultSet.getInt("id"))
                    .firstName(resultSet.getString("firstName"))
                    .lastName(resultSet.getString("lastName"))
                    .guestType(GuestType.valueOf(resultSet.getString("guestType")))
                    .passportNumber(resultSet.getString("passportNumber"))
                    .nationalID(resultSet.getString("nationalID"))
                    .birthDate(resultSet.getDate("birthDate").toLocalDate())
                    .build();
        }
        return guest;
    }

    public Guest findByFamily(String lastName) throws Exception {
        Guest guest = null;
        connection = connectionProvider.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM GUESTS WHERE LAST_NAME=?");
        preparedStatement.setString(1, lastName);
        ResultSet resultSet =preparedStatement.executeQuery();
        if(resultSet.next()){
            guest = Guest
                    .builder()
                    .id(resultSet.getInt("id"))
                    .firstName(resultSet.getString("firstName"))
                    .lastName(resultSet.getString("lastName"))
                    .guestType(GuestType.valueOf(resultSet.getString("guestType")))
                    .passportNumber(resultSet.getString("passportNumber"))
                    .nationalID(resultSet.getString("nationalID"))
                    .birthDate(resultSet.getDate("birthDate").toLocalDate())
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
