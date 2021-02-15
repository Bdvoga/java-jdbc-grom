package hibernate.lesson4.service;

import hibernate.lesson4.repository.HotelRepository;
import hibernate.lesson4.model.Hotel;

import java.util.ArrayList;

public class HotelService {
    HotelRepository hotelRepository = new HotelRepository();
    GeneralService generalService = new GeneralService();

    public ArrayList<Hotel> findHotelByName(String name) throws Exception {
        generalService.verification();

        return hotelRepository.findHotelByName(name);
    }

    public ArrayList<Hotel> findHotelByCity(String city) throws Exception {
        generalService.verification();

        return hotelRepository.findHotelByCity(city);
    }
}
