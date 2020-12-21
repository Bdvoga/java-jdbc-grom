package lesson2.ex4;

import java.sql.*;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class" + JDBC_DRIVER + " not found");
            }

            increasePrice(statement);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void increasePrice(Statement statement) throws SQLException {
        String sql = "UPDATE PRODUCT2 SET PRICE = PRICE + 100 "  + "WHERE PRICE < 970";
        statement.executeUpdate(sql);

    }
}