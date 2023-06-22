package sql.employees;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class Main {
    public static final EmployeeDao empDao = new EmployeeDao();
    public static void main(String[] args) throws SQLException {
        insertDummyData();
        String prefix = "------------------------------------------------------------ ";
        String suffix = "---------------------------------------";
        System.out.println(prefix + "BEFORE UPDATING f_name AND vacation_balance " + suffix);
        selectAndPrint();
        updateVacationBalance();
        updateFirstName();
        empDao.executeBatch();
        System.out.println("\n\n" + prefix + "AFTER UPDATING f_name AND vacation_balance " + suffix);
        selectAndPrint();
        empDao.close();

    }

    private static void updateFirstName() {
        empDao.addToBatch("update employees set employees.f_name = CONCAT('Mr/Mrs ',employees.f_name) where employees.age>45");
    }

    private static void selectAndPrint() throws SQLException {
        try (ResultSet resultSet = empDao.selectAllEmployees()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("| %15s ", metaData.getColumnLabel(i));
            }
            System.out.println();

            while (resultSet.next()) {
                System.out.printf("| %15d | %15s | %15s | %15s | %15d | %15s | %15s | %15d | %n",
                                  resultSet.getInt(1),
                                  resultSet.getString(2),
                                  resultSet.getString(3),
                                  resultSet.getString(4),
                                  resultSet.getInt(5),
                                  resultSet.getString(6),
                                  resultSet.getString(7),
                                  resultSet.getInt(8));
            }
        }
    }

    private static void insertDummyData() throws SQLException {
        empDao.addToBatch("jan", "youssef", "male", 50, "heliopolis", "01280591319");
        empDao.addToBatch("mostafa", "ali", "male", 70, "Fayoum", "01555016386");
        empDao.addToBatch("mohamed", "osama", "male", 22, "behera", "01002865592");
        empDao.addToBatch("kareem", "reda", "male", 66, "tanta", "01111155555");
        empDao.addToBatch("marwan", "helmy", "male", 22, "kafr el sheikh", "01558370775");
        empDao.addToBatch("mariam", "ahmed", "female", 22, "zamalek", "56465456465");
        empDao.executeBatch();
    }

    private static void updateVacationBalance() {
        empDao.addToBatch("update employees set employees.vacation_balance = 45 where employees.age>45");
    }


}
