package lesson4.ex2;

public class DemoFile {
    public static void main(String[] args) throws Exception {
        FileDAO fileDAO = new FileDAO();

        Storage storage1 = new Storage(4L, new String[]{"txt", "jpg"}, "UA", 1000L);
        Storage storage2 = new Storage(1L, new String[]{"txt", "jpg"}, "UA", 1000L);
        Storage storage3 = new Storage(2L, new String[]{"txt", "jpg"}, "UA", 1000L);
        File file1 = new File(1L, "test1", "txt", 10L, storage1);
        File file2 = new File(2L, "test2", "txt", 15L, storage2);
        File file3 = new File(3L, "test3", "txt", 25L, storage3);
        File file5 = new File(5L, "test5", "rar", 2000L, storage3);
        File file6 = new File(6L, "test6", "jpg", 100L, storage3);


//        fileDAO.save(file6);

//        fileDAO.delete(5);

//        fileDAO.update(file3);



    }
}
