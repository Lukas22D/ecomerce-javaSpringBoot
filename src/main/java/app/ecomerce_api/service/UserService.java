package app.ecomerce_api.service;

import java.util.List;

import app.ecomerce_api.model.User;

public interface UserService {
    public User saveUser(User user);

    public User getUserById(Long id);

    public List<User> getAllUsers();

    public void deleteUserById(Long id);

    public User updateUser(User user);

}
