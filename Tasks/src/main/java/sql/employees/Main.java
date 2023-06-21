package sql.employees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sql.employees.DbConnectionProvider.getPreparedStatement;

public class Main {
    public static void main(String[] args) throws SQLException {

        try (Statement statement = DbConnectionProvider.getStatement()) {
            createEmployeeTableIfNotExist(statement);
            clearTable(statement);
            insertDummyData();
            selectAndPrint(statement);
            batchUpdateEmployees(statement);
            System.out.println("\n\n\n ------------- AFTER UPDATING f_name AND vacation_balance -------------");
            selectAndPrint(statement);
        }


    }

    private static void createEmployeeTableIfNotExist(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS employees(" + "id INTEGER AUTO_INCREMENT PRIMARY KEY " +
                                  ",f_name VARCHAR(80)" + ",l_name VARCHAR(80)" + ", sex enum('male','female')" +
                                  ",age INTEGER" + ",address VARCHAR(200)" + ",phone_number VARCHAR(200)" +
                                  ",vacation_balance INTEGER  default 30)");
    }

    private static void clearTable(Statement statement) throws SQLException {
        statement.execute("TRUNCATE TABLE employees");
    }

    private static void insertDummyData() throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement("INSERT INTO employees (f_name,l_name,sex,age,address,phone_number)VALUES(?,?,?,?,?,?)");
        insert("jan", "youssef", "male", 50, "heliopolis", "01280591319", preparedStatement);
        insert("mostafa", "ali", "male", 70, "Fayoum", "01555016386", preparedStatement);
        insert("mohamed", "osama", "male", 22, "behera", "01002865592", preparedStatement);
        insert("kareem", "reda", "male", 66, "tanta", "01111155555", preparedStatement);
        insert("marwan", "helmy", "male", 22, "kafr el sheikh", "01558370775", preparedStatement);
        insert("mariam", "ahmed", "female", 22, "zamalek", "56465456465", preparedStatement);
        preparedStatement.executeBatch();
    }

    private static void selectAndPrint(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String fName = resultSet.getString(2);
            String lName = resultSet.getString(3);
            String sex = resultSet.getString(4);
            int age = resultSet.getInt(5);
            String address = resultSet.getString(6);
            String phoneNumber = resultSet.getString(7);
            int vacationBalance = resultSet.getInt(8);

            System.out.println(
                    id + " " + fName + " " + lName + " " + sex + " " + age + " " + address + " " + phoneNumber + " " +
                            vacationBalance);
        }
    }

    private static void batchUpdateEmployees(Statement statement) throws SQLException {
        statement.addBatch("update employees set employees.vacation_balance = 45 where employees.age>45");
        statement.addBatch("update employees set employees.f_name = CONCAT('Mr/Mrs ',employees.f_name) where employees.age>45");
        statement.executeBatch();
    }


    private static void insert(String fname, String lname, String gender, int age, String address, String number, PreparedStatement ps) throws SQLException {
        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, gender);
        ps.setInt(4, age);
        ps.setString(5, address);
        ps.setString(6, number);
        ps.addBatch();
    }


}
