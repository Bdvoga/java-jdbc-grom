package hibernate.lesson4.model;

import hibernate.lesson4.Repository.IdEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERSS")
public class Order extends IdEntity {
    private Long id;
    private User userOrdered;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private Double moneyPaid;

    @Id
    @SequenceGenerator(name = "O_SEQ", sequenceName = "ORDERSS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "O_SEQ")
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public User getUserOrdered() {
        return userOrdered;
    }

    public void setUserOrdered(User userOrdered) {
        this.userOrdered = userOrdered;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOMM_ID")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Column(name = "DATE_FROM")
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Column(name = "DATE_TO")
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    @Column(name = "MONEY_PAID")
    public Double getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(Double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
//                ", userOrdered=" + userOrdered +
//                ", room=" + room +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", moneyPaid=" + moneyPaid +
                '}';
    }
}
