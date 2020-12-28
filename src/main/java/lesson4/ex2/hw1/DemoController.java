package lesson4.ex2.hw1;

import lesson4.ex2.Controller;
import lesson4.ex2.File;
import lesson4.ex2.Storage;

import java.util.ArrayList;
import java.util.List;

public class DemoController {

    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();

        Storage storage = new Storage(3L, new String[]{"txt", "jpg"}, "UA", 1000L);
        File file = new File(5L, "test5", "rar", 50L, null);

        //controller.put(storage, file);

        Storage storage1 = new Storage(2L, new String[]{"txt", "jpg"}, "FRA", 2000L);
        File file1 = new File(6L, "test6", "txt", 100L, null);
        File file2 = new File(7L, "test7", "jpg", 75L, null);
        File file3 = new File(8L, "test8", "txt", 55L, null);
        List<File> list = new ArrayList<>();
        list.add(file1);
        list.add(file2);
        list.add(file3);

        //controller.putAll(storage1, list);

        Storage storage2 = new Storage(2L, new String[]{"txt", "jpg", "rar"}, "FRA", 2000L);
        File file4 = new File(3L, "test3", "txt", 25L, storage2);

        //controller.delete(storage2, file4);

        File file5 = new File(5L, "test5", "rar", 50L, storage2);
        //controller.transferFile(storage2, storage, 5); //2 => 3

        //Storage storage5 = new Storage(5L, new String[]{"txt"}, "USA", 10L);
        //controller.transferAll(storage2, storage5); //2 => 5
        Storage storage5 = new Storage(5L, new String[]{"rar", "txt", "jpg"}, "USA", 1000L);
        controller.transferAll(storage2, storage5); //2 => 5


    }
}
