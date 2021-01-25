package jdbc.lesson3.ex2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "As172394";

    public List<Product> findProductsByPrice(int price, int delta) {
        int idDB = 1;
        int nameDb = 2;
        int descriptionDb = 3;
        int priceDb = 4;

        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            List<Product> products= new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2 WHERE PRICE > " + (price - delta) + " AND PRICE < " + (price + delta));

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(idDB), resultSet.getString(nameDb),
                        resultSet.getString(descriptionDb), resultSet.getInt(priceDb));
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
        int idDb = 1;
        int nameDb = 2;
        int descriptionDb = 3;
        int priceDb = 4;
        int wordCount = 1;
        int lengthWord = 3;
        String correctSymbols = "[0-9a-zA-Z]+";
        String split = " ";

        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            List<Product> products= new ArrayList<>();

            //1. Проверяем word на корректоность
            if (word.split(split).length > wordCount) {
                throw new Exception("Слово " + word + " содержит больше одного слова");
            }
            if (!Pattern.matches(correctSymbols, word) || word.length() < lengthWord) {
                throw new Exception("Слово " + word + " содержит спецсимволы или длина меньше 3");
            }

            //2. список со словом
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2 WHERE NAME = " + "'" + word + "'");

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(idDb), resultSet.getString(nameDb),
                        resultSet.getString(descriptionDb), resultSet.getInt(priceDb));
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
        int idDb = 1;
        int nameDb = 2;
        int descriptionDb = 3;
        int priceDb = 4;

        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            List<Product> products= new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT2 WHERE DESCRIPTION IS NULL");

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(idDb), resultSet.getString(nameDb),
                        resultSet.getString(descriptionDb), resultSet.getInt(priceDb));
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
