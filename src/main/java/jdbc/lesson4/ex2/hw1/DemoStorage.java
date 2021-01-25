package jdbc.lesson4.ex2.hw1;

import jdbc.lesson4.ex2.Storage;
import jdbc.lesson4.ex2.StorageDAO;

public class DemoStorage {

    public static void main(String[] args) throws Exception {
        StorageDAO storageDAO = new StorageDAO();
        Storage storage1 = new Storage(4L, new String[]{"txt", "jpg"}, "UA", 1000L);
        Storage storage2 = new Storage(4L, new String[]{"txt", "jpg"}, "UA", 1000L);
        Storage storage3 = new Storage(4L, new String[]{"txt", "jpg"}, "FRA", 4000L);
        Storage storage4 = new Storage(4L, new String[]{"txt", "jpg"}, "ITA", 5L);

        //storageDAO.save(storage1);

        //storageDAO.delete(3);

        //System.out.println(storageDAO.freeSpaceInStorage(1));

        //storageDAO.update(null);

        storageDAO.findById(4);
    }

}
