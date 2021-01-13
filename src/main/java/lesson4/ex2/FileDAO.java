package lesson4.ex2;

import java.sql.*;

public class FileDAO {
    //Controller controller = new Controller();
    StorageDAO storageDAO = new StorageDAO();

    public void save(File file) throws Exception {
        //1.верификация входных данных
        //2.Есть ли файл с таким ид в хранилище
        //3.Есть ли хранилище с таким id - findById(long id) +
        //4.Хватит ли места в Хранилище +
        //5.Поддерживает ли хранилище формат файла
        //6.Записать новый storage в БД +

        verification(file);
        storageDAO.ifHaveFormat(storageDAO.findById(file.getId()), file);

        try (Connection connection = storageDAO.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FILES VALUES (?,?,?,?,?)");

            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getSize());
            preparedStatement.setLong(5, file.getStorage().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }

    public void delete(long id) throws Exception {
        //1. есть ли такой id
        //2. удаляем
        if (findById(id) == null) {
            throw new Exception("Файл с id=" + id + " не найден");
        }

        try (Connection connection = storageDAO.getConnection()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM FILES WHERE ID = " + id);

        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }
    }

    public void update(File file) throws Exception {
        //верификация
        //есть ли файл
        //совпадают ли хранилища
        //хватит ли места в хранилище после изменения
        //апдейт

        int fileSizeDb = 4;

        ifHaveAllDataForFile(file);

        try (Connection connection = storageDAO.getConnection()) {
            Statement statement = connection.createStatement();

            //размер старого файла
            ResultSet rs = statement.executeQuery("SELECT * FROM FILES WHERE ID = " + file.getId());
            rs.next();
            long sizeOldFile = rs.getLong(fileSizeDb);

            ifFileNotInStorage(file.getStorage(), file);

            if (storageDAO.freeSpaceInStorage(file.getStorage().getId()) + sizeOldFile < file.getSize()) {
                throw new Exception("Для обновленного файла недостаточно места в хранилище id=" + file.getStorage().getId());
            }

            statement.executeUpdate("UPDATE FILES SET FILE_NAME = " + "'" + file.getName() + "'" +
                    ", FILE_FORMAT = " + "'" + file.getFormat() + "'" + ", FILE_SIZE = " +
                    file.getSize() + " WHERE ID = " + file.getId());

        } catch (SQLException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public File findById(long id) {
        int idBd = 1;
        int fileNameBd = 2;
        int fileFormatBd = 3;
        int fileSizeBd = 4;
        int storageIdBd = 5;

        try (Connection connection = storageDAO.getConnection()) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM FILES");
            while (rs.next()) {
                if (id == rs.getLong(idBd)) {
                    return new File(id, rs.getString(fileNameBd), rs.getString(fileFormatBd), rs.getLong(fileSizeBd), storageDAO.findById(rs.getLong(storageIdBd))); // id найден в базе
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }

        return null;
    }

    private void verification(File file) throws Exception {
        ifHaveAllDataForFile(file);

        if (findById(file.getId()) != null) {
            throw new Exception("Файл с id=" + file.getId() + " уже существует");
        }

        storageDAO.ifHaveStorage(storageDAO.findById(file.getStorage().getId()));
        storageDAO.ifHaveFreeSpaceForFile(file.getStorage(), file);
    }

    public void ifHaveAllDataForFile(File file) throws Exception {
        if (file == null || file.getId() == null || file.getFormat() == null || file.getSize() == null) {
            throw new Exception("Для файла не заполнены все данные");
        }
    }

    public void ifFileNotInStorage(Storage storage, File file) throws Exception {
        if (file.getStorage().getId() != storage.getId()) {
            throw new Exception("Файл id=" + file.getId() + " не найден в хранилище id=" + storage.getId());
        }
    }

    public void ifFileInStorage(Storage storage, File file) throws Exception {
        if (file.getStorage() != null) {
            throw new Exception("Файл id=" + file.getId() + " уже сохранен в хранилище id=" + file.getStorage().getId());
        }
    }

}