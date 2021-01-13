package lesson3.ex1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    //CRUD - create, read, update, delete

    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public Product save (Product product) {
        int id = 1;
        int name = 2;
        int description = 3;
        int price = 4;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT2 VALUES (?, ?, ?, ?)");

            preparedStatement.setLong(id, product.getId());
            preparedStatement.setString(name, product.getName());
            preparedStatement.setString(description, product.getDescription());
            preparedStatement.setInt(price, product.getPrice());

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

            preparedStatement.setLong(price, product.getId());
            preparedStatement.setString(id, product.getName());
            preparedStatement.setString(name, product.getDescription());
            preparedStatement.setInt(description, product.getPrice());

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
        int idDb = 1;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUCT2 WHERE ID = ?");

            preparedStatement.setLong(idDb, id);

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
