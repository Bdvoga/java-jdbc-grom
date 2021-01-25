package jdbc.lesson3.ex1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    //CRUD - create, read, update, delete

    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public Product save (Product product) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT2 VALUES (?, ?, ?, ?)");

            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());

            int res = preparedStatement.executeUpdate();
            System.out.println("save was finished with result " + res);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        return product;
    }

    public Product update (Product product) {
        int id = 1;
        int name = 2;
        int description = 3;
        int price = 4;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUCT2 SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE ID = ?");

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());

            int res = preparedStatement.executeUpdate();
            System.out.println("update was finished with result " + res);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        return product;
    }

    public List<Product> getProducts() {
        int id = 1;
        int name = 2;
        int description = 3;
        int price = 4;

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2");

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(id),
                        resultSet.getString(name), resultSet.getString(description),
                        resultSet.getInt(price));
                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    public void delete (long id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUCT2 WHERE ID = ?");

            preparedStatement.setLong(1, id);

            int res = preparedStatement.executeUpdate();
            System.out.println("delete was finished with result " + res);


        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
