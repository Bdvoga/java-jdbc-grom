package jdbc.lesson3.ex4;

import java.sql.*;
import java.util.Date;

public class Solution {
    //Время выполнения testSavePerformance: 127498 - Statement
    //Время выполнения testSavePerformance: 126433 - PreparedStatement
    //Время выполнения testDeleteByIdPerformance: 127346
    //Время выполнения testDeletePerformance: 179
    //Время выполнения testSelectByIdPerformance: 125569
    //Время выполнения testSelectPerformance: 12730

    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public static void main(String[] args) {

        //testSavePerformance();
        //testDeleteByIdPerformance();
        //testDeletePerformance();
        //testSelectByIdPerformance();
        testSelectPerformance();
    }

    private static void testSavePerformance() {
        int id = 1;
        int someString = 2;
        int someNumber = 3;
        int number = 100;
        int quantityLines = 1001;
        String string = "acd";

        try (Connection connection = getConnection()){
            Date start = new Date();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO TEST_SPEEED VALUES(?, ?, ?)");

            for (int i = 1; i < quantityLines; i++) {
                ps.setInt(id, i);
                ps.setString(someString, string);
                ps.setInt(someNumber, number);
                ps.executeUpdate();
            }

            Date finish = new Date();
            long time = finish.getTime() - start.getTime();

            System.out.println("Время выполнения testSavePerformance: " + time);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    //Время выполнения testSavePerformance: 127498 - Statement
    //Время выполнения testSavePerformance: 126433 - PreparedStatement

    private static void testDeleteByIdPerformance() {
        int quantityLines = 1001;

        try (Connection connection = getConnection()){
            Date start = new Date();
            Statement statement = connection.createStatement();

            for (int i = 1; i < quantityLines; i++) {
                statement.executeUpdate("DELETE FROM TEST_SPEEED WHERE ID = " + i);
            }

            Date finish = new Date();
            long time = finish.getTime() - start.getTime();

            System.out.println("Время выполнения testDeleteByIdPerformance: " + time);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    //Время выполнения testDeleteByIdPerformance: 127346

    private static void testDeletePerformance() {
        try (Connection connection = getConnection()){
            Date start = new Date();

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM TEST_SPEEED");

            Date finish = new Date();
            long time = finish.getTime() - start.getTime();

            System.out.println("Время выполнения testDeletePerformance: " + time);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    //Время выполнения testDeletePerformance: 179

    private static void testSelectByIdPerformance() {
        int id = 1;
        int someString = 2;
        int someNumber = 3;
        int quantityLines = 1001;

        try (Connection connection = getConnection()){
            Date start = new Date();
            Statement statement = connection.createStatement();

            for (int i = 1; i < quantityLines; i++) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST_SPEEED WHERE ID = " + i);
                while (resultSet.next()) {
                    resultSet.getInt(id);
                    resultSet.getString(someString);
                    resultSet.getInt(someNumber);
                }
            }

            Date finish = new Date();
            long time = finish.getTime() - start.getTime();

            System.out.println("Время выполнения testSelectByIdPerformance: " + time);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    //Время выполнения testSelectByIdPerformance: 125569

    private static void testSelectPerformance() {
        int id = 1;
        int someString = 2;
        int someNumber = 3;

        try (Connection connection = getConnection()){
            Date start = new Date();
            Statement statement = connection.createStatement();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST_SPEEED")){
                while (resultSet.next()) {
                    resultSet.getInt(id);
                    resultSet.getString(someString);
                    resultSet.getInt(someNumber);
                }
            }

            Date finish = new Date();
            long time = finish.getTime() - start.getTime();

            System.out.println("Время выполнения testSelectPerformance: " + time);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    //Время выполнения testSelectPerformance: 12730

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
