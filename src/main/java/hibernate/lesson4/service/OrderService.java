package hibernate.lesson4.service;

import hibernate.lesson4.Repository.GeneralRepository;
import hibernate.lesson4.Repository.OrderRepository;
import hibernate.lesson4.Repository.RoomRepository;
import hibernate.lesson4.Repository.UserRepository;
import hibernate.lesson4.model.Order;

import java.util.Date;

public class OrderService extends GeneralRepository<Order> {
    GeneralService generalService = new GeneralService();
    RoomRepository roomRepository = new RoomRepository();
    UserRepository userRepository = new UserRepository();
    OrderRepository orderRepository = new OrderRepository();

    public void bookRoom(long userId, long roomId, Date dateFrom, Date dateTo) throws Exception {
        generalService.verification();

        //Есть ли такая комната
        System.out.println(roomId);
        System.out.println(roomRepository.findById(roomId));

        if (roomRepository.findById(22) == null) {
            throw new Exception("Такой комнаты не существует");
        }

        //Сбводна ли с даты dateFrom
        if (roomRepository.findById(roomId).getDateAvailableFrom().getTime() > dateFrom.getTime()) {
            throw new Exception("На указанные даты комната не доступна");
        }

        //Есть ли такой юзер
        if (userRepository.findById(userId) == null) {
            throw new Exception("Пользователь не найден");
        }

        orderRepository.bookRoom(userId, roomId, dateFrom, dateTo);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {
        generalService.verification();
        orderRepository.cancelReservation(roomId, userId);
    }
}
