package lesson4.ex2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    StorageDAO storageDAO = new StorageDAO();
    FileDAO fileDAO = new FileDAO();

    public void put(Storage storage, File file) throws Exception {
        //у файла нет хранилища
        //есть ли хранилище
        //есть ли место в хранилище
        //поддерживает ли хранилище формат
        //сохранить - записать в БД номер хранилища

        fileDAO.ifFileInStorage(storage, file);
        storageDAO.ifHaveStorage(storage);
        storageDAO.ifHaveFreeSpaceForFile(storage, file);
        storageDAO.ifHaveFormat(storage, file);

        try (Connection connection = storageDAO.getConnection()) {
            Statement statement = connection.createStatement();
            String sqlUpdate = "UPDATE FILES SET STORAGE_ID = " + storage.getId() + " WHERE ID = " + file.getId();
            statement.executeUpdate(sqlUpdate);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }

    public void putAll(Storage storage, List<File> files) throws Exception {
        for (File file : files) {
            fileDAO.ifFileInStorage(storage, file);
            storageDAO.ifHaveStorage(storage);
            storageDAO.ifHaveFreeSpaceForFile(storage, file);
            storageDAO.ifHaveFormat(storage, file);
        }

        try (Connection connection = storageDAO.getConnection()) {
            putAllList(storage, files, connection);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void delete(Storage storage, File file) throws Exception {
        //Удаляем из хранилища, но в БД оставляем
        //есть ли файл в хранилище

        storageDAO.ifHaveStorage(storage);

        if (fileDAO.findById(file.getId()) == null) {
            throw new Exception("Файл id=" + file.getId() + " не найден");
        }

        fileDAO.ifFileNotInStorage(storage, file);

        try (Connection connection = storageDAO.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = null WHERE ID = ?");
            ps.setLong(1, file.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        //Есть ли хранилище +
        //есть ли файл +
        //есть ли файл в хранилище +
        //хватит ли места в storageTo +
        //поддерживает ли хранилище тип файла

        storageDAO.ifHaveStorage(storageFrom);
        storageDAO.ifHaveStorage(storageTo);
        ifHaveFile(fileDAO.findById(id));

        fileDAO.ifFileInStorage(storageFrom, fileDAO.findById(id));

        if (storageDAO.freeSpaceInStorage(storageDAO.findById(storageTo.getId()).getId()) < fileDAO.findById(id).getSize()) {
            throw new Exception("В хранилище id=" + storageTo.getId() + " недостаточно свободного места");
        }

        storageDAO.ifHaveFormat(storageTo, fileDAO.findById(id));

        try (Connection connection = storageDAO.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE ID = ?");
            ps.setLong(1, storageTo.getId());
            ps.setLong(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        //Есть ли хранилища +
        //хватит ли места в хранилище storageTo +
        //считать все файлы из storageFrom в список +
        //поддерживает ли хранилище storageTo формат файлов из storageFrom +
        //сохранить файлы

        storageDAO.ifHaveStorage(storageFrom);
        storageDAO.ifHaveStorage(storageTo);

        if (storageDAO.freeSpaceInStorage(storageTo.getId()) < (storageFrom.getStorageMaxSize() - storageDAO.freeSpaceInStorage(storageFrom.getId()))) {
            throw new Exception("В хранилище id=" + storageTo.getId() + " недостаточно свободного места");
        }

        try (Connection connection = storageDAO.getConnection()) {
            Statement st = connection.createStatement();
            List<File> fileList = new ArrayList<>();
            String sqlQuery = "SELECT * FROM FILES WHERE STORAGE_ID = " + storageFrom.getId();
            ResultSet rs = st.executeQuery(sqlQuery);

            while (rs.next()) {
                File file = new File(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getLong(4), storageDAO.findById(rs.getLong(5)));

                storageDAO.ifHaveFormat(storageTo, file);

                fileList.add(file);
            }

            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE ID = ?");
            for (File file : fileList) {
                ps.setLong(1, storageTo.getId());
                ps.setLong(2, file.getId());

                ps.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

    }

    private void putAllList(Storage storage, List<File> files, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE ID = ?")) {
            connection.setAutoCommit(false);
            for (File file : files) {
                ps.setLong(1, storage.getId());
                ps.setLong(2, file.getId());
                ps.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e; //пробрасываем ошибку в save()
        }
    }

    public void ifHaveFile(File file) throws Exception {
        if ((fileDAO.findById(file.getId())) == null) {
            throw new Exception("Файл id=" + file.getId() + " не найден");
        }
    }
}