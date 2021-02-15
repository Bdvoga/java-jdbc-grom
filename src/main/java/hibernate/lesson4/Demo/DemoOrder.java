package hibernate.lesson4.Demo;

import hibernate.lesson4.Repository.OrderRepository;
import hibernate.lesson4.Repository.RoomRepository;
import hibernate.lesson4.Repository.UserRepository;
import hibernate.lesson4.controller.OrderController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoOrder {
    public static void main(String[] args) throws Exception {
        OrderRepository orderRepository = new OrderRepository();
        UserRepository userRepository = new UserRepository();
        RoomRepository roomRepository = new RoomRepository();
        UserController userController = new UserController();
        OrderController orderController = new OrderController();

        userController.login("Fiona", "444");

//        System.out.println(orderRepository.findById(1));

        Date dateFrom = new SimpleDateFormat("dd-MM-yyyy").parse("20-02-2021");
        Date dateTo = new SimpleDateFormat("dd-MM-yyyy").parse("23-02-2021");
        Order order = new Order();
        order.setUserOrdered(userRepository.findById(5));
        order.setRoom(roomRepository.findById(22));
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setMoneyPaid(175.);

//        System.out.println(orderRepository.save(order));

//        orderRepository.delete(3);

        Date dateFrom1 = new SimpleDateFormat("dd-MM-yyyy").parse("16-02-2021");
        Date dateTo1 = new SimpleDateFormat("dd-MM-yyyy").parse("18-02-2021");
        Order order1 = new Order();
        order1.setId(1L);
        order1.setUserOrdered(userRepository.findById(2));
        order1.setRoom(roomRepository.findById(2));
        order1.setDateFrom(dateFrom1);
        order1.setDateTo(dateTo1);
        order1.setMoneyPaid(250.);

//        System.out.println(orderRepository.update(order1));

        Date dateFrom2 = new SimpleDateFormat("dd-MM-yyyy").parse("01-03-2021");
        Date dateTo2 = new SimpleDateFormat("dd-MM-yyyy").parse("05-03-2021");

//        orderController.bookRoom(6, 1, dateFrom2, dateTo2);

        orderRepository.cancelReservation(22,5);

        userController.logout();
    }
}