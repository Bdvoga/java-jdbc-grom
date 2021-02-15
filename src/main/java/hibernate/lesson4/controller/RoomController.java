package hibernate.lesson4.controller;

import hibernate.lesson4.model.Filter;
import hibernate.lesson4.service.RoomService;

import java.util.List;

public class RoomController {
    RoomService roomService = new RoomService();

    public List findRooms(Filter filter) throws Exception {
        return roomService.findRooms(filter);
    }
}
