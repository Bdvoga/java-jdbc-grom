package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RoomDAO {
    private HotelDAO hotelDAO = new HotelDAO();

    public Room update(Room room) {
        Transaction tr = null;
        try (Session session = hotelDAO.createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.get(Room.class, room.getId()).setNumberOfGuests(room.getNumberOfGuests());
            session.get(Room.class, room.getId()).setPrice(room.getPrice());
            session.get(Room.class, room.getId()).setBreakfastIncluded(room.getBreakfastIncluded());
            session.get(Room.class, room.getId()).setPetsAllowed(room.getPetsAllowed());
            session.get(Room.class, room.getId()).setDateAvailableFrom(room.getDateAvailableFrom());
            session.get(Room.class, room.getId()).setHotel(room.getHotel());

            session.getTransaction().commit();

            return session.get(Room.class, room.getId());
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
        try (Session session = hotelDAO.createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            if (session.get(Room.class, id) != null) {
                session.delete(session.get(Room.class, id));
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

    public Room findById(long id) {
        Session session = hotelDAO.createSessionFactory().openSession();
        Room room = session.get(Room.class, id);

        return room;
    }

    public Room save(Room room) {
        Transaction tr = null;
        try (Session session = hotelDAO.createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.save(room);

            session.getTransaction().commit();

            return session.get(Room.class, room.getId());
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        }

        return null;
    }
}