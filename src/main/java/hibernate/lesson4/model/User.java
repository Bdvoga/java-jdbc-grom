package hibernate.lesson4.model;

import hibernate.lesson4.repository.IdEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERSS")
public class User extends IdEntity {
    private long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private List<Order> orders;

    @Id
    @SequenceGenerator(name = "U_SEQ", sequenceName = "USERSS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "U_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "USER_PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @OneToMany(mappedBy = "userOrdered", cascade = CascadeType.ALL, orphanRemoval = true)
    //mappedBy = "userOrdered" => поле private User userOrdered в классе Order
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", userType=" + userType +
//                ", orders=" + orders +
                '}';
    }
}
