package hibernate.lesson4.model;

import java.util.List;

public class User {
    private long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private List<Order> orders;


}
