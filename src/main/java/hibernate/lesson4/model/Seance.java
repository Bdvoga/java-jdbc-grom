package hibernate.lesson4.model;

public class Seance {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Seance.user = user;
    }
}
