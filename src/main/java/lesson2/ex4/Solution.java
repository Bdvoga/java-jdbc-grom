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
            connectToDb();

            statement.executeUpdate("UPDATE PRODUCT2 SET PRICE = PRICE + 100 "  + "WHERE PRICE < 970");

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void changeDescription() throws SQLException {
        int lengthDescription = 10;
        int id = 1;
        int description = 3;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            connectToDb();

//            String shortDescription = "";
//            List<String> listDescription = new ArrayList<>();
            List<Integer> listId = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2 WHERE LENGTH(DESCRIPTION) > " + lengthDescription)){
                while (resultSet.next()) {
                    listId.add(resultSet.getInt(id));
//                    listDescription.add(resultSet.getString(description));
//                    resultSet.updateString(description, deleteLastSentence(resultSet.getString(description)));
//                    resultSet.updateRow();

                }
            }

            for (Integer el: listId) {
                ResultSet rs = statement.executeQuery("SELECT * FROM PRODUCT2 WHERE ID = " + el);
                rs.next();
                statement.executeUpdate("UPDATE PRODUCT2 SET DESCRIPTION = " + "'" + deleteLastSentence(rs.getString(description))  + "'" + " WHERE ID = " + el);
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static String deleteLastSentence (String string) {
        String shortDescription = "";
        String split = "\\. ";
        String[] strings = string.split(split);

        for (int i = 0; i < strings.length - 1; i++) {
            shortDescription = shortDescription + strings[i] + ". ";
        }

        return shortDescription;
    }

    public static void connectToDb() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Class" + JDBC_DRIVER + " not found");
        }
    }
}