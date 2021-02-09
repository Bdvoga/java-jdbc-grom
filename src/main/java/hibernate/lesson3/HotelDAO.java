package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HotelDAO {
    private SessionFactory sessionFactory;

    public Hotel findById(long id) {
        Session session = createSessionFactory().openSession();

        Hotel hotel = session.get(Hotel.class, id);
        session.close();

        return hotel;
    }

    public Hotel update(Hotel hotel) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.get(Hotel.class, hotel.getId()).setName(hotel.getName());
            session.get(Hotel.class, hotel.getId()).setCountry(hotel.getCountry());
            session.get(Hotel.class, hotel.getId()).setCity(hotel.getCity());
            session.get(Hotel.class, hotel.getId()).setStreet(hotel.getStreet());

            session.getTransaction().commit();

            return session.get(Hotel.class, hotel.getId());
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        }

        return null;
    }

    public void delete(long id) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            if (session.get(Hotel.class, id) != null) {
                session.delete(session.get(Hotel.class, id));
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        }
    }

    public Hotel save(Hotel hotel) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(hotel);

            session.getTransaction().commit();

            return session.get(Hotel.class, hotel.getId());
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        }

        return null;
    }

    public SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}