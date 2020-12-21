import java.sql.*;

public class JDBCFirstStep {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public static void main(String[] args) {
        //Connection connection = null;
        //Statement statement = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            //1. DB Driver +
            //2. Create connection +
            //3. create query/statement +
            //4. execute query +
            //5. work with result +
            //6. close all connection

            try { // Можно запускать один раз, джава запоминает драйвер
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) { // ошибка - если не добавили библиотеку ojdbc7
                System.out.println("Class" + JDBC_DRIVER + " not found");
                return;
            }

            //connection = DriverManager.getConnection(DB_URL, USER, PASS); //2. Create connection

            //statement = connection.createStatement(); //3. create query/statement
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM Test"); //4. execute query
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer WHERE CUSTOMER_ID > 3004")) {
                while (resultSet.next()) { //5. work with result
                    long customerId = resultSet.getLong(1);
                    String companyName = resultSet.getString(2);
                    String contactName = resultSet.getString(3);
                    String contactTitle = resultSet.getString(4);
                    Order order = new Order(customerId, companyName, contactName, contactTitle);
                    System.out.println(order.toString());
                }
            }

            //6. close all connection
//            resultSet.close();
//            statement.close();
//            connection.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }
}