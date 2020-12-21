package lesson2.ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

            //saveProduct(statement);
            //deleteProduct(statement);
            deleteProductsByPrice(statement);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void saveProduct(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO PRODUCT2 VALUES (999, 'toy', 'for children', 60 )");
    }

    private static void deleteProduct(Statement statement) throws SQLException {
        statement.executeUpdate("DELETE FROM PRODUCT2 WHERE NAME != 'toy' ");
    }

    private static void deleteProductsByPrice(Statement statement) throws SQLException {
        statement.executeUpdate("DELETE FROM PRODUCT2 WHERE PRICE < 100");
    }
}