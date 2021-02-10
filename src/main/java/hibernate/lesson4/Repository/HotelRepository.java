package hibernate.lesson4.Repository;

import hibernate.lesson4.model.Hotel;
import org.hibernate.Session;

public class HotelRepository {
    GeneralRepository generalRepository = new GeneralRepository();

    public Hotel findById(long id) {
        Session session = generalRepository.createSessionFactory().openSession();

        Hotel hotel = session.get(Hotel.class, id);
        session.close();

        return hotel;
    }



    public Hotel save(Hotel hotel) {

        return null;
    }

    public void delete(long id) {


    }

    public Hotel update(Hotel hotel) {

        return null;
    }
}
