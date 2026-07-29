package black.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
    private final static BasicDataSource BASIC_DATA_SOURCE = new BasicDataSource();

    static{
        BASIC_DATA_SOURCE.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        BASIC_DATA_SOURCE.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        BASIC_DATA_SOURCE.setUsername("javase");
        BASIC_DATA_SOURCE.setPassword("java123");
        BASIC_DATA_SOURCE.setMinIdle(5);
        BASIC_DATA_SOURCE.setMaxTotal(20);
    }

    public Connection getConnection() throws SQLException {
      return BASIC_DATA_SOURCE.getConnection();
    }
}
