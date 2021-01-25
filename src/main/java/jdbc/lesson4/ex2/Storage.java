package jdbc.lesson4.ex2;

public class Storage {
    Long id;
    String[] formatSupported;
    String storageCountry;
    Long storageMaxSize;

    public Storage(Long id, String[] formatSupported, String storageCountry, Long storageMaxSize) {
        this.id = id;
        this.formatSupported = formatSupported;
        this.storageCountry = storageCountry;
        this.storageMaxSize = storageMaxSize;
    }

    public Long getId() {
        return id;
    }

    public String[] getFormatSupported() {
        return formatSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public Long getStorageMaxSize() {
        return storageMaxSize;
    }


}