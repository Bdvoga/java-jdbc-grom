package hibernate.lesson4.demo;

import hibernate.lesson4.repository.HotelRepository;
import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Hotel;

public class DemoHotel {

    public static void main(String[] args) throws Exception {
        HotelRepository hotelRepository = new HotelRepository();
        HotelController hotelController = new HotelController();
        UserController userController = new UserController();

        userController.login("Fiona", "444");

//        System.out.println(hotelRepository.findById(2));

        Hotel hotel = new Hotel();
        hotel.setName("Second");
        hotel.setCountry("USA");
        hotel.setCity("Boston");
        hotel.setStreet("str3");

//        System.out.println(hotelRepository.save(hotel));

//        Hotel hotel1 = new Hotel(); //null
//
//        System.out.println(hotelRepository.save(hotel1));

//        hotelRepository.delete(23);

        Hotel hotel2 = new Hotel();
        hotel2.setId(25L);
        hotel2.setName("Third-Third");
        hotel2.setCountry("France");
        hotel2.setCity("Paris");
        hotel2.setStreet("str4");

//        System.out.println(hotelRepository.update(hotel2));



//        System.out.println(hotelController.findHotelByName("XXX"));

        System.out.println(hotelController.findHotelByCity("Boston"));

        userController.logout();

    }

}