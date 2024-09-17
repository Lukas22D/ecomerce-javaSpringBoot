package app.ecomerce_api.service.core;

import app.ecomerce_api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import app.ecomerce_api.handler.custom.UserAlreadyExistsException;
import app.ecomerce_api.model.User;
import app.ecomerce_api.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class UserServiceCore implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userRepository.save(user);

    }

}
