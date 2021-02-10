package hibernate.lesson4.Demo;

import hibernate.lesson4.Repository.HotelRepository;
import hibernate.lesson4.model.Hotel;

public class DemoHotel {

    public static void main(String[] args) {
        HotelRepository hotelRepository = new HotelRepository();

        System.out.println(hotelRepository.findById(1));


    }


}