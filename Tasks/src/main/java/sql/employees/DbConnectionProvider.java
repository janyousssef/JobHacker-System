package sql.employees;

import java.sql.*;

public class DbConnectionProvider {
    public DbConnectionProvider() {
    }

    public static Statement getStatement()  {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://containers-us-west-82.railway.app:7893/railway",
                                           "root",
                                           "SIDB6zoo0uq7zupNfMOR");
    }

    public static PreparedStatement getPreparedStatement(String sql)  {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
