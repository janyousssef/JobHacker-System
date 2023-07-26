package sql.employees;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDao implements AutoCloseable {
    private final Statement statement;
    private final PreparedStatement ps;

    public EmployeeDao() {
        statement = DbConnectionProvider.getStatement();
        ps = DbConnectionProvider.getPreparedStatement("INSERT INTO employees (f_name,l_name,sex,age,address,phone_number)VALUES(?,?,?,?,?,?)");
        clearTable();
        createEmployeeTableIfNotExist();
    }


    public void executeBatch() {
        try {
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet selectAllEmployees() {
        try {
            return statement.executeQuery("SELECT * FROM employees");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToBatch(String fname, String lname, String gender, int age, String address, String number) throws SQLException {
        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, gender);
        ps.setInt(4, age);
        ps.setString(5, address);
        ps.setString(6, number);
        ps.addBatch();
    }
    public void addToBatch(String sql){
        try {
            ps.addBatch(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void close() {
        try {
            ps.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTable() {
        try {
            statement.execute("TRUNCATE TABLE employees");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createEmployeeTableIfNotExist() {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS employees(" + "id INTEGER AUTO_INCREMENT PRIMARY KEY " +
                                      ",f_name VARCHAR(80)" + ",l_name VARCHAR(80)" + ", sex enum('male','female')" +
                                      ",age INTEGER" + ",address VARCHAR(200)" + ",phone_number VARCHAR(200)" +
                                      ",vacation_balance INTEGER  default 30)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
