package hibernate.lesson4.service;

import hibernate.lesson4.Repository.RoomRepository;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Seance;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
    RoomRepository roomRepository = new RoomRepository();
    GeneralService generalService = new GeneralService();

    public List findRooms(Filter filter) throws Exception {
        //Залогинен ли юзер
        //Все ли поля заполнены

        generalService.verification();

        if (filter.getNumberOfGuests() != 0 && filter.getPrice() != 0.0 && filter.getBreakfastIncluded() != null &&
                filter.getPetsAllowed() != null && filter.getDateAvailableFrom() != null &&
                filter.getCountry() != null && filter.getCity() != null) {
            return roomRepository.findRooms(filter);
        } else {
            throw new Exception("Не все поля заполнены!");
        }
    }
}
