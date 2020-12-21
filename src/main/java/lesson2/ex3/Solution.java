package lesson2.ex3;

import java.sql.*;
import java.util.ArrayList;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) { // ошибка - если не добавили библиотеку ojdbc7
                System.out.println("Class" + JDBC_DRIVER + " not found");
            }

            //getAllProducts(statement);
            //getProductsByPrice(statement);
            getProductsByDescription(statement);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void getAllProducts(Statement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2")){
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) +
                        " " +resultSet.getString(3) + " " + resultSet.getInt(4));
            }
        }
    }

    private static void getProductsByPrice(Statement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2 WHERE PRICE <= 100")){
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) +
                        " " +resultSet.getString(3) + " " + resultSet.getInt(4));
            }
        }
    }

    private static void getProductsByDescription(Statement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2")){
            while (resultSet.next()) {
                if (resultSet.getString(3).length() > 50) {
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) +
                            " " +resultSet.getString(3) + " " + resultSet.getInt(4));
                }
            }
        }
    }
}
