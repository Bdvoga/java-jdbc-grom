package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RoomDAO {
    private SessionFactory sessionFactory;

    public Room save(Room room) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(room);

            session.getTransaction().commit();

            return session.get(Room.class, room.getRoomId());
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        }

        return null;
    }

    public Hotel findById(long id) {
        Session session = createSessionFactory().openSession();

        Hotel hotel = (Hotel) session.createSQLQuery("SELECT * FROM HOTEL WHERE id = " + id)
                .addEntity(Hotel.class)
                .getSingleResult();

        session.close();

        return hotel;
    }

    public SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}