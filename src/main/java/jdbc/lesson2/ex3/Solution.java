package jdbc.lesson2.ex3;

import java.sql.*;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public static void main(String[] args) throws SQLException {

            getAllProducts();
            getProductsByPrice();
            getProductsByDescription();
    }

    private static void getAllProducts() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            connectToDb();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2")){
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") +
                            " " +resultSet.getString("description") + " " + resultSet.getInt("price"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void getProductsByPrice() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            connectToDb();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2 WHERE PRICE <= 100")){
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") +
                            " " +resultSet.getString("description") + " " + resultSet.getInt("price"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void getProductsByDescription() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            connectToDb();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2")){
                while (resultSet.next()) {
                    if (resultSet.getString(3) != null && resultSet.getString(3).length() > 50) {
                        System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") +
                                " " +resultSet.getString("description") + " " + resultSet.getInt("price"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public static void connectToDb() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) { // ошибка - если не добавили библиотеку ojdbc7
            System.out.println("Class" + JDBC_DRIVER + " not found");
        }
    }
}
