package app.ecomerce_api.service;

import java.util.List;

import app.ecomerce_api.controller.dto_controller.UserCreateDto;
import app.ecomerce_api.model.User;

public interface UserService {
    public User saveUser(UserCreateDto userRequest);

    public User getUserById(Long id);

    public List<User> getAllUsers();

    public void deleteUserById(Long id);

    public User updateUser(User user);

}
