package hibernate.lesson4.model;

import java.util.Date;

public class Order {
    private Long id;
    private User userOrdered;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private Double moneyPaid;
}
