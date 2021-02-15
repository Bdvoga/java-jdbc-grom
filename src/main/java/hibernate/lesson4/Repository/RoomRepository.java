package hibernate.lesson4.Repository;

import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomRepository extends GeneralRepository<Room> {
    GeneralRepository<Room> generalRepository = new GeneralRepository<>(Room.class);

    public List findRooms(Filter filter) {
        Session session = createSessionFactory().openSession();

        List rooms = session.createQuery("select room from Room as room " +
                "join room.hotel as hotel " +
                "where room.numberOfGuests = " + filter.getNumberOfGuests() +
                " and room.price = " + filter.getPrice() +
                " and room.breakfastIncluded = " + filter.getBreakfastIncluded() +
                " and room.petsAllowed = " + filter.getPetsAllowed() +
                " and to_char(room.dateAvailableFrom, 'dd-mm-yyyy') = " + "'" + new SimpleDateFormat(("dd-MM-yyyy")).format(filter.getDateAvailableFrom()) + "'" +
                " and hotel.country = " + "'" + filter.getCountry() + "'" +
                " and hotel.city = " + "'" + filter.getCity() + "'").list();

        session.close();

        return rooms;
    }

    public Room findById(long id) {
        return generalRepository.findById(id);
    }

    public Room update(Room room) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
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
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        }

        return null;
    }

    public Room save(Room room) {
        return generalRepository.save(room);
    }

}