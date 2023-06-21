package sql.employees;

import java.sql.*;

public class DbConnectionProvider {
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://containers-us-west-82.railway.app:7893/railway",
                                           "root",
                                           "SIDB6zoo0uq7zupNfMOR");
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return DbConnectionProvider.getConnection().prepareStatement(sql);
    }

    public static Statement getStatement() throws SQLException {
        return DbConnectionProvider.getConnection().createStatement();
    }
}
