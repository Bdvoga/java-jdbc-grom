package lesson3.ex4;

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
            try (Connection connection = getConnection()){
                Date start = new Date();
                //Statement statement = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO TEST_SPEEED VALUES(?, ?, ?)");

                for (int i = 1; i < 1001; i++) {
                    //String sql = "INSERT INTO TEST_SPEEED VALUES(" + i + "," + " 'asd', 30)";
                    //statement.executeUpdate(sql);
                    ps.setInt(1, i);
                    ps.setString(2, "acd");
                    ps.setInt(3, 100);
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
        try (Connection connection = getConnection()){
            Date start = new Date();
            Statement statement = connection.createStatement();

            for (int i = 1; i < 1001; i++) {
                String sql = "DELETE FROM TEST_SPEEED WHERE ID = " + i;
                statement.executeUpdate(sql);
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
        try (Connection connection = getConnection()){
            Date start = new Date();
            Statement statement = connection.createStatement();

            for (int i = 1; i < 1001; i++) {
                String sql = "SELECT * FROM TEST_SPEEED WHERE ID = " + i;
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    resultSet.getInt(1);
                    resultSet.getString(2);
                    resultSet.getInt(3);
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
        try (Connection connection = getConnection()){
            Date start = new Date();
            Statement statement = connection.createStatement();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST_SPEEED")){
                while (resultSet.next()) {
                    resultSet.getInt(1);
                    resultSet.getString(2);
                    resultSet.getInt(3);
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

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    //Время выполнения testSelectPerformance: 12730

}
