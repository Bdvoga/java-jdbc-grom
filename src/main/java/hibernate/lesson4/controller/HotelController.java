package hibernate.lesson4.controller;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.service.HotelService;
import java.util.ArrayList;

public class HotelController {
    HotelService hotelService = new HotelService();

    public ArrayList<Hotel> findHotelByName(String name) throws Exception {
        return hotelService.findHotelByName(name);
    }

    public ArrayList<Hotel> findHotelByCity(String city) throws Exception {
        return hotelService.findHotelByCity(city);
    }

}
