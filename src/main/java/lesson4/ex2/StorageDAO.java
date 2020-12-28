package lesson4.ex2;

import java.sql.*;
import java.util.Arrays;

public class StorageDAO {

    public void save(Storage storage) throws Exception {
        //верификация
        //1.Есть ли хранилище с таким id - findById(long id)
        //2.Записать новый storage в БД

        verification(storage);

        if (findById(storage.getId()) != null) {
            throw new Exception("Хранилище с id=" + storage.getId() + " уже существует");
        }

        //преобразуем формат String[] к String формата БД
        String str = transferString(storage);

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO STORAGES VALUES (?,?,?,?)");

            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, str);
            preparedStatement.setString(3, storage.getStorageCountry());
            preparedStatement.setLong(4, storage.getStorageMaxSize());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }

    public void delete(long id) throws Exception {
        //1. есть ли такой id
        //2. Пустое ли хранилище
        //3. удаляем

        ifHaveStorage(findById(id));

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            //Есть ли файлы в хранилище id
            String sql = "SELECT COUNT(*) FROM FILES WHERE STORAGE_ID = " + id;
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                throw new Exception("В хранилище с id=" + id + " храняться файлы. Удаление невозможно!");
            }

            String sql2 = "DELETE FROM STORAGES WHERE ID = " + id;
            statement.executeUpdate(sql2);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

    }

    public void update(Storage storage) throws Exception {
        //верификация
        //есть ли хранилище
        //хватит ли места для существующих файлов после апдейта
        //апдейт

        verification(storage);

        if (findById(storage.getId()) == null) {
            throw new Exception("Хранилище с id=" + storage.getId() + " не найдено");
        }

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            //размер старого хранилища
            String sqlQuery = "SELECT * FROM STORAGEs WHERE ID = " + storage.getId();
            ResultSet rs = statement.executeQuery(sqlQuery);
            rs.next();
            long oldStorageSize = rs.getLong(4);
            long oldStorageFreeSpace = freeSpaceInStorage(storage.getId());

            if (storage.storageMaxSize < (oldStorageSize - oldStorageFreeSpace)) {
                throw new Exception("Новый размер хранилища id=" + storage.getId() + " недостаточен для хранения существующих файлов");
            }

            String sqlUpdate = "UPDATE STORAGES SET FORMAT_SUPPORTED = " + "'" + transferString(storage) + "'" +
                    ", COUNTRY = " + "'" + storage.getStorageCountry() + "'" + ", MAX_SIZE = " +
                    storage.getStorageMaxSize() + "WHERE ID = " + storage.getId();

            statement.executeUpdate(sqlUpdate);
        }
    }

    public Storage findById(long id) throws SQLException {
        try (Connection connection = getConnection()) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM STORAGES");
            while (rs.next()) {
                if (id == rs.getLong(1)) {
                    String[] strings = rs.getString(2).split(", ");

                    return new Storage(id, strings, rs.getString(3), rs.getLong(4)); // id найден в базе
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

        return null;
    }

    //Сколько свободного места в хранилище
    public long freeSpaceInStorage(long id) throws Exception {

        if (findById(id) == null) {
            throw new Exception("Хранилище с id=" + id + " не существует");
        }

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            //суммарный размер файлов, хранящихся в хранилище
            String sql = "SELECT * FROM FILES WHERE STORAGE_ID = " + id;
            ResultSet rs = statement.executeQuery(sql);

            long sizeFiles = 0;
            while (rs.next()) {
                sizeFiles += rs.getLong(4);
            }

            //Емкость хранилища
            String sql2 = "SELECT * FROM STORAGES WHERE ID = " + id;
            ResultSet rs2 = statement.executeQuery(sql2);

            rs2.next();
            long sizeStorage = rs2.getLong(4);

            return sizeStorage - sizeFiles;

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

        return 0;
    }

    private void verification(Storage storage) throws Exception {
        if (storage == null || storage.getId() == null || storage.getFormatSupported() == null ||
                storage.getStorageCountry() == null || storage.getStorageMaxSize() == null) {
            throw new Exception("Для хранилища не заполнены все данные");
        }
    }

    //преобразуем формат String[] к String формата БД
    private String transferString(Storage storage) {
        StringBuilder strBild = new StringBuilder();
        int count = storage.getFormatSupported().length - 1;
        for (String el : storage.getFormatSupported()) {
            if (count > 0) {
                strBild.append(el).append(", ");
            } else {
                strBild.append(el);
            }
            count--;
        }

        return strBild.toString();
    }

    public void ifHaveFormat(Storage storage, File file) throws Exception {
        String[] strings = storage.getFormatSupported();
        for (String el : strings) {
            if (el.equals(file.getFormat())) {
                return;
            }
        }
        throw new Exception("Хранилище id=" + storage.getId() + " не поддерживает файлы типа " + file.getFormat());
    }

    public Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:oracle:thin:@gromcode-lessons.cbwvy0uzrtqa.us-east-2.rds.amazonaws.com:1521:ORCL";
        String USER = "main";
        String PASS = "As172394";
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void ifHaveStorage(Storage storage) throws Exception {
        if (findById(storage.getId()) == null) {
            throw new Exception("Хранишлище id=" + storage.getId() + " не найдено");
        }
    }

    public void ifHaveFreeSpaceForFile(Storage storage, File file) throws Exception {
        if (freeSpaceInStorage(storage.getId()) < file.getSize()) {
            throw new Exception("В хранилище id=" + storage.getId() + " недостаточно места для сохранения файла id=" + file.getId());
        }
    }
}