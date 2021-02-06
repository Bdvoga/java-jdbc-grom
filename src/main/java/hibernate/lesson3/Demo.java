package hibernate.lesson3;

public class Demo {

    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();

        Hotel hotel = new Hotel();
        hotel.setName("Dnipro");
        hotel.setCountry("Ukraine");
        hotel.setCity("Kyiv");
        hotel.setStreet("str1");

        System.out.println(hotelDAO.save(hotel));

//        hotelDAO.delete(5);

        Hotel hotel1 = new Hotel();
        hotel1.setId(3);
        hotel1.setName("Volna");
        hotel1.setCountry("Ukraine");
        hotel1.setCity("Odesa");
        hotel1.setStreet("str2");

//        System.out.println(hotelDAO.update(hotel1));

//        System.out.println(hotelDAO.findById(2));

        Room room = new Room();
        room.setNumberOfGuests(3);
        room.setPrice(50);
        room.setBreakfastIncluded(1);
        room.setPetsAllowed(0);
        room.setHotel(hotel);

        roomDAO.save(room);


    }

}
