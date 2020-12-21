package lesson2.ex4;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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

            //increasePrice(statement);
            changeDescription(statement);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void increasePrice(Statement statement) throws SQLException {
        statement.executeUpdate("UPDATE PRODUCT2 SET PRICE = PRICE + 100 "  + "WHERE PRICE < 970");

    }

    private static void changeDescription(Statement statement) throws SQLException {
        String newString = "";
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2")){
            while (resultSet.next()) {
                if (resultSet.getString(3).length() > 10) {
                    String str = resultSet.getString(3);
                    String[] strings = str.split("\\. ");
                    strings[strings.length - 1] = "";
                    for (String el : strings) {
                        newString = newString + el;
                    }
                    String sql = "UPDATE PRODUCT2 SET DESCRIPTION = " + "'" + newString  + "'" +
                            " WHERE ID = " + resultSet.getInt(1);
                    statement.executeUpdate(sql);
                }
            }
        }
    }
}