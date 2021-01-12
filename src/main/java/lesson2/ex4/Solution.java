package lesson2.ex4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public static void main(String[] args) throws SQLException {

//            increasePrice();
            changeDescription();
    }

    private static void increasePrice() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class" + JDBC_DRIVER + " not found");
            }

            statement.executeUpdate("UPDATE PRODUCT2 SET PRICE = PRICE + 100 "  + "WHERE PRICE < 970");

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void changeDescription() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class" + JDBC_DRIVER + " not found");
            }

            String newString = "";
            List<String[]> listDescription = new ArrayList<>();
            List<Integer> listId = new ArrayList<>();
            List<String> listSql = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2")){
                while (resultSet.next()) {
                    if (resultSet.getString(3) != null && resultSet.getString(3).length() > 100) {
                        String[] strings = resultSet.getString(3).split("\\. ");
                        listId.add(resultSet.getInt(1));
                        listDescription.add(strings);
                    }
                }

                int count = 0;
                for (String[] strings: listDescription) {
                    for (int i = 0; i < strings.length - 1; i++) {
                        newString = newString + strings[i] + ". ";
                    }

                    listSql.add("UPDATE PRODUCT2 SET DESCRIPTION = " + "'" + newString  + "'" +
                            " WHERE ID = " + listId.get(count));
                    newString = "";
                    count++;
                }
            }

            if (listSql.size() != 0) {
                for (String el : listSql) {
                    statement.executeUpdate(el);
                }
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

    }
}