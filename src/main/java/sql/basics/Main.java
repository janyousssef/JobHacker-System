package sql.basics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends DbConnectionProvider {
    public static void main(String[] args) throws SQLException {

        try (Statement statement = DbConnectionProvider.getStatement()) {
            createEmployeesIfNotExist(statement);
            insertDummyData(statement);
            selectAndPrint(statement);
        }


    }

    private static void selectAndPrint(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + "   " + resultSet.getString(2));
        }
    }

    private static void insertDummyData(Statement statement) throws SQLException {
        statement.execute("INSERT INTO employees (name)VALUES ('jan'),('mostafa'),('ayoub'),('marwan')");
    }

    private static void createEmployeesIfNotExist(Statement statement) throws SQLException {
        statement.execute
                ("CREATE TABLE IF NOT EXISTS employees(id INTEGER AUTO_INCREMENT PRIMARY KEY ,name varchar(80) )");
    }

}
