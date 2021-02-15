package hibernate.lesson4.Demo;

import hibernate.lesson4.Repository.UserRepository;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.model.Seance;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserType;

public class DemoUser {
    public static void main(String[] args) throws Exception {
        UserRepository userRepository = new UserRepository();
        UserController userController = new UserController();

        System.out.println(userRepository.findById(1));

        User user = new User();
        user.setUserName("Fiona");
        user.setPassword("444");
        user.setCountry("Italy");
        user.setUserType(UserType.USER);

//        System.out.println(userRepository.save(user));

//        System.out.println(userController.registerUser(user));

//        userRepository.delete(4);

        User user1 = new User();
        user1.setId(5);
        user1.setUserName("Rita from Brazil");
        user1.setPassword("333");
        user1.setCountry("Brazil");
        user1.setUserType(UserType.USER);

//        System.out.println(userRepository.update(user1));

//        userRepository.login("Mike", "222");
//        System.out.println(Seance.getUser());

//        userRepository.logout();
//        System.out.println(Seance.getUser());

    }
}
