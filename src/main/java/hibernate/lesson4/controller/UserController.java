package hibernate.lesson4.controller;

import hibernate.lesson4.model.Seance;
import hibernate.lesson4.model.User;
import hibernate.lesson4.service.UserService;

public class UserController {
    UserService userService = new UserService();

    public void login(String userName, String password) throws Exception {
        userService.login(userName, password);
    }

    public void logout() {
        Seance.setUser(null);
    }

    public User registerUser(User user) throws Exception {
        return userService.registerUser(user);
    }
}