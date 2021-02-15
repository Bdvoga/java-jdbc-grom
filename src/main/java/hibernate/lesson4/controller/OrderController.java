package hibernate.lesson4.controller;

import hibernate.lesson4.service.OrderService;

import java.util.Date;

public class OrderController {

    OrderService orderService = new OrderService();

    public void bookRoom(long userId, long roomId, Date dateFrom, Date dateTo) throws Exception {
        orderService.bookRoom(userId, roomId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        orderService.cancelReservation(roomId, userId);
    }

}
