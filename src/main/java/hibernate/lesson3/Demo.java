package hibernate.lesson3;

import java.util.Date;

public class Demo {

    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();

        Hotel hotel = new Hotel();
        hotel.setName("Dnipro");
        hotel.setCountry("Ukraine");
        hotel.setCity("Kyiv");
        hotel.setStreet("str1");

//        System.out.println(hotelDAO.save(hotel));

//        hotelDAO.delete(5);

//        Hotel hotel1 = new Hotel();
//        hotel1.setId(3);
//        hotel1.setName("Volna");
//        hotel1.setCountry("Ukraine");
//        hotel1.setCity("Odesa");
//        hotel1.setStreet("str2");

//        System.out.println(hotelDAO.update(hotel1));

        Room room = new Room();
        room.setNumberOfGuests(3);
        room.setPrice(50);
        room.setBreakfastIncluded(1);
        room.setPetsAllowed(0);
        room.setHotel(hotelDAO.findById(8));

//        System.out.println(hotelDAO.findById(8));

//        roomDAO.save(room);

//        System.out.println(roomDAO.findById(25));

//        roomDAO.delete(26);

        Room room1 = new Room();
        room1.setId(27);
        room1.setNumberOfGuests(5);
        room1.setPrice(500);
        room1.setBreakfastIncluded(1);
        room1.setPetsAllowed(1);
        room1.setDateAvailableFrom(new Date());
        room1.setHotel(hotelDAO.findById(9));

        System.out.println(roomDAO.update(room1));
    }

}
