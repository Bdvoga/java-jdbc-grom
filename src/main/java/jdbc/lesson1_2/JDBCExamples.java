package jdbc.lesson1_2;

import java.sql.*;

public class JDBCExamples {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            //boolean res = statement.execute("INSERT INTO PRODUCT2 VALUES (2, 'toy', 'for children', 60 )");
            //execute возвращает true, если мы получили в ответ на запрос данные
            //и всегда false, если ничего, не зависимо от того, удачно или нет добавлены данные

//            boolean res = statement.execute("DELETE FROM PRODUCT2 WHERE NAME = 'toy111'");
//            System.out.println(res);

            //используется очень редко
            boolean res = statement.execute("DELETE FROM PRODUCT2 WHERE NAME = 'toy111'");
            System.out.println(res);

            //Основной в использовании
            //Возвр число строк, которые были response (ответ получен)
            int response = statement.executeUpdate("INSERT INTO PRODUCT2 VALUES (6, 'toy6', 'for children', 770 )");
            System.out.println(response);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

}
