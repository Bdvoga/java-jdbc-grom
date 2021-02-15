package hibernate.lesson4.repository;

import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class HotelRepository extends GeneralRepository<Hotel> {
    GeneralRepository<Hotel> generalRepository = new GeneralRepository<>(Hotel.class);

    public ArrayList<Hotel> findHotelByName(String name) {
        Session session = createSessionFactory().openSession();

        ArrayList<Hotel> hotels = (ArrayList<Hotel>) session.createQuery("from Hotel where name like " + "'%" + name + "%'").list();

        session.close();

        return hotels;
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

            tr.commit();

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

    public Hotel findById(long id) {
        return generalRepository.findById(id);
    }

    public ArrayList<Hotel> findHotelByCountry(String country) {
        Session session = createSessionFactory().openSession();

        ArrayList<Hotel> hotels = (ArrayList<Hotel>) session.createQuery("from Hotel where country like " + "'%" + country + "%'").list();

        session.close();

        return hotels;
    }

    public ArrayList<Hotel> findHotelByCity(String city) {
        Session session = createSessionFactory().openSession();

        ArrayList<Hotel> hotels = (ArrayList<Hotel>) session.createQuery("from Hotel where city like " + "'%" + city + "%'").list();

        session.close();

        return hotels;
    }

}