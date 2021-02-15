package hibernate.lesson4.service;

import hibernate.lesson4.repository.UserRepository;
import hibernate.lesson4.model.User;

public class UserService {
    UserRepository userRepository = new UserRepository();

    public void login(String userName, String password) throws Exception {
        userRepository.login(userName, password);
    }

    public User registerUser(User user) throws Exception {
        //все ли поля заполнены
        if (user != null && user.getUserName() != null && user.getPassword() != null &&
                user.getCountry() != null && user.getUserType() != null) {
            return userRepository.save(user);
        } else {
            throw new Exception("Ошибка! Не все поля заполнены!");
        }

    }
}