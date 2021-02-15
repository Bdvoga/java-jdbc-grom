package hibernate.lesson4.Demo;

import hibernate.lesson4.Repository.GeneralRepository;
import hibernate.lesson4.Repository.HotelRepository;
import hibernate.lesson4.Repository.RoomRepository;
import hibernate.lesson4.Repository.UserRepository;
import hibernate.lesson4.controller.RoomController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.Seance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoRoom {

    public static void main(String[] args) throws Exception {
        RoomRepository roomRepository = new RoomRepository();
        HotelRepository hotelRepository = new HotelRepository();
        RoomController roomController = new RoomController();
        UserController userController = new UserController();

        userController.login("Fiona", "444");

//        GeneralRepository<Room> roomGeneralRepository = new GeneralRepository<>();
//        GeneralRepository<Hotel> hotelGeneralRepository = new GeneralRepository<>();
//        roomGeneralRepository.setType(Room.class);
//        hotelGeneralRepository.setType(Hotel.class);

        Hotel hotel = hotelRepository.findById(6);
//        System.out.println(hotel);

//        System.out.println(roomRepository.findById(3));

//        roomRepository.delete(4);

        Room room = new Room();
        room.setId(1L);
        room.setNumberOfGuests(4);
        room.setPrice(234D);
        room.setBreakfastIncluded(false);
        room.setPetsAllowed(true);
        room.setDateAvailableFrom(new Date());
        room.setHotel(hotel);

//        System.out.println(roomRepository.update(room));

        Room room1 = new Room();
        room1.setNumberOfGuests(5);
        room1.setPrice(15D);
        room1.setBreakfastIncluded(false);
        room1.setPetsAllowed(true);
        room1.setDateAvailableFrom(new Date());
        room1.setHotel(hotel);

//        System.out.println(roomRepository.save(room1));


        Date dateFrom = new SimpleDateFormat("dd-MM-yyyy").parse("13-02-2021");

        Filter filter = new Filter(5, 15D, false, true,
                dateFrom, hotel.getCountry(), hotel.getCity());

        System.out.println(roomController.findRooms(filter));

        userController.logout();

    }

}
