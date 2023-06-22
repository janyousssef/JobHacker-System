package sql.employees;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class Main {
    public static final EmployeeDao empDao = new EmployeeDao();
    public static void main(String[] args) throws SQLException {
            insertDummyData();
            System.out.println("------------- BEFORE UPDATING f_name AND vacation_balance -------------");
            selectAndPrint();
            updateVacationBalance();
            updateFirstName();
            empDao.executeBatch();
            System.out.println("\n\n\n ------------- AFTER UPDATING f_name AND vacation_balance -------------");
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
            for (int i = 1; i < columnCount; i++) {
                System.out.print(metaData.getColumnLabel(i)+" | ");
            }
            System.out.println();

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
                        id + " | " + fName + " | " + lName + " | " + sex + " | " + age + " | " + address + " | " + phoneNumber +
                                " | " +
                                vacationBalance);
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
