package jdbc.lesson4.ex2;

import java.sql.*;

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

        int idBd = 1;

        ifHaveStorage(findById(id));

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            //Есть ли файлы в хранилище id
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM FILES WHERE STORAGE_ID = " + id);

            resultSet.next();
            int count = resultSet.getInt(idBd);
            if (count > 0) {
                throw new Exception("В хранилище с id=" + id + " храняться файлы. Удаление невозможно!");
            }

            statement.executeUpdate("DELETE FROM STORAGES WHERE ID = " + id);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

    }

    public void update(Storage storage) throws Exception {
        //верификация
        //есть ли хранилище
        //хватит ли места для существующих файлов после апдейта
        //апдейт

        int maxSize = 4;

        verification(storage);

        if (findById(storage.getId()) == null) {
            throw new Exception("Хранилище с id=" + storage.getId() + " не найдено");
        }

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            //размер старого хранилища
            ResultSet rs = statement.executeQuery("SELECT * FROM STORAGES WHERE ID = " + storage.getId());
            rs.next();

            if (storage.storageMaxSize < (rs.getLong(maxSize) - freeSpaceInStorage(storage.getId()))) {
                throw new Exception("Новый размер хранилища id=" + storage.getId() + " недостаточен для хранения существующих файлов");
            }

            statement.executeUpdate("UPDATE STORAGES SET FORMAT_SUPPORTED = " + "'" + transferString(storage) + "'" +
                    ", COUNTRY = " + "'" + storage.getStorageCountry() + "'" + ", MAX_SIZE = " +
                    storage.getStorageMaxSize() + "WHERE ID = " + storage.getId());
        }
    }

    public Storage findById(long id) throws SQLException {
        int idDb = 1;
        int formatSupported = 2;
        int country = 3;
        int maxSize = 4;
        String split = ", ";

        try (Connection connection = getConnection()) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM STORAGES");
            while (rs.next()) {
                if (id == rs.getLong(idDb)) {
                    String[] strings = rs.getString(formatSupported).split(split);

                    return new Storage(id, strings, rs.getString(country), rs.getLong(maxSize)); // id найден в базе
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

        return null;
    }

    //Сколько свободного места в хранилище
    public long freeSpaceInStorage(long id) throws Exception {
        int fileSize = 4;

        if (findById(id) == null) {
            throw new Exception("Хранилище с id=" + id + " не существует");
        }

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            //суммарный размер файлов, хранящихся в хранилище
            ResultSet rs = statement.executeQuery("SELECT * FROM FILES WHERE STORAGE_ID = " + id);

            long sizeFiles = 0;
            while (rs.next()) {
                sizeFiles += rs.getLong(fileSize);
            }

            //Емкость хранилища
            ResultSet rs2 = statement.executeQuery("SELECT * FROM STORAGES WHERE ID = " + id);

            rs2.next();
            long sizeStorage = rs2.getLong(fileSize);

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
        String separator = ", ";

        StringBuilder strBild = new StringBuilder();
        int count = storage.getFormatSupported().length - 1;
        for (String el : storage.getFormatSupported()) {
            if (count > 0) {
                strBild.append(el).append(separator);
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