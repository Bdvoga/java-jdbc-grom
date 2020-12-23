package lesson3.ex2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public List<Product> findProductsByPrice(int price, int delta) {
        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            List<Product> products= new ArrayList<>();

            int min = price - delta;
            int max = price + delta;

            String sql = "SELECT * FROM PRODUCT2 WHERE PRICE > " + min + " AND PRICE < " + max;

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> findProductsByName(String word) throws Exception {
        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            List<Product> products= new ArrayList<>();

            //1. Проверяем word на корректоность
            if (word.split(" ").length > 1) {
                throw new Exception("Слово " + word + " содержит больше одного слова");
            }
            if (!Pattern.matches("[0-9a-zA-Z]+", word) || word.length() < 3 ) {
                throw new Exception("Слово " + word + " содержит спецсимволы или длина меньше 3");
            }


            //2. список со словом

            String sql = "SELECT * FROM PRODUCT2 WHERE NAME = " + "'" + word + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> findProductsWithEmpty() {
        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            List<Product> products= new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2 WHERE DESCRIPTION IS NULL");

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }

            return products;

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }

        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
