package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HOTEL")
public class Hotel {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    //один Hotel связан со множеством Room
    //mappedBy = "hotel" - указывает на поле private Hotel hotel в классе Room
    //orphanRemoval = true - удаляем "сирот" - если удалим Отель, будут удалены все связанные с ним Комнаты
    private List<Room> rooms;

    @Id
    @SequenceGenerator(name = "H_SEQ", sequenceName = "HOTEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "H_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    @Column(name = "HOTEL_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

//    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Room> getRooms() {
        return this.rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
