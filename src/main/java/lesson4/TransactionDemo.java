package lesson4;
import lesson3.ex1.Product;
import lesson3.ex1.ProductDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDemo {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public void save (List<Product> products) {
        try (Connection connection = getConnection()) {
            saveList(products, connection);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private void saveList(List<Product> products, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT2 VALUES (?, ?, ?, ?)")) {

            //авто коммит отлючен
            connection.setAutoCommit(false);

            for (Product product : products) {
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setInt(4, product.getPrice());

                int res = preparedStatement.executeUpdate();
                System.out.println("save was finished with result " + res);
            }
            //Транзакция заканчивается
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e; //пробрасываем ошибку в save()
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
