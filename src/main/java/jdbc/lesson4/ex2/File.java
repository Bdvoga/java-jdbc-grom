package jdbc.lesson4.ex2;

public class File {
    Long id;
    String name;
    String format;
    Long size;
    Storage storage;

    public File(Long id, String name, String format, Long size, Storage storage) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;
        this.storage = storage;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public Long getSize() {
        return size;
    }

    public Storage getStorage() {
        return storage;
    }
}