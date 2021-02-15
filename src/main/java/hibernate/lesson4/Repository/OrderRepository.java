package hibernate.lesson4.Repository;


import hibernate.lesson4.model.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class OrderRepository extends GeneralRepository<Order> {
    GeneralRepository<Order> generalRepository = new GeneralRepository<>(Order.class);
    UserRepository userRepository = new UserRepository();
    RoomRepository roomRepository = new RoomRepository();

    public void cancelReservation(long roomId, long userId) {
        Session session = createSessionFactory().openSession();
        Order order = (Order) session.createQuery("from Order where userOrdered = " + userId +
                " and room = " + roomId).uniqueResult();

        delete(order.getId());

    }

    public void bookRoom(long userId, long roomId, Date dateFrom, Date dateTo) {
        Order order = new Order();
        order.setUserOrdered(userRepository.findById(userId));
        order.setRoom(roomRepository.findById(roomId));
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setMoneyPaid(((dateTo.getTime() - dateFrom.getTime()) / 86400000)
                * roomRepository.findById(roomId).getPrice());

        save(order);
    }

    public Order update (Order order) {
        Transaction tr = null;
        try(Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.get(Order.class, order.getId()).setUserOrdered(order.getUserOrdered());
            session.get(Order.class, order.getId()).setRoom(order.getRoom());
            session.get(Order.class, order.getId()).setDateFrom(order.getDateFrom());
            session.get(Order.class, order.getId()).setDateTo(order.getDateTo());
            session.get(Order.class, order.getId()).setMoneyPaid(order.getMoneyPaid());

            tr.commit();

            return session.get(Order.class, order.getId());
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        }

        return null;
    }

    public Order findById(long id) {
        return generalRepository.findById(id);
    }
}