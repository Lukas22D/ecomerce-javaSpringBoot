package app.ecomerce_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecomerce_api.service.UserService;
import app.ecomerce_api.config.View;
import app.ecomerce_api.controller.dto_controller.Response200;
import app.ecomerce_api.model.User;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @JsonView(View.UserWithCart.class)
    public ResponseEntity<Response200> registerUser( @RequestBody User user) {
        User userSave = userService.saveUser(user);
        return new ResponseEntity<>(new Response200().setResponse200("User registered", HttpStatus.OK.value(), userSave), HttpStatus.OK);
    }

    @GetMapping("/")
    @JsonView(View.UserWithCart.class)
    public ResponseEntity<Response200> findAllUser() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(new Response200().setResponse200("Users found", HttpStatus.OK.value(), users), HttpStatus.OK);
    }

    @PutMapping("/atualizar")
    @JsonView(View.UserWithCart.class)
    public ResponseEntity<Response200> updateUser(@RequestBody User user) {
        var userUpdate = userService.updateUser(user);
        return new ResponseEntity<>(new Response200().setResponse200("User updated", HttpStatus.OK.value(), userUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @JsonView(View.UserWithCart.class)
    public ResponseEntity<Response200> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(new Response200().setResponse200("User deleted", HttpStatus.OK.value(), null), HttpStatus.OK);
    }
    

    @GetMapping("/{id}")
    @JsonView(View.UserWithCart.class)
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        var user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
