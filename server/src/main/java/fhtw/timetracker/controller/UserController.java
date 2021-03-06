package fhtw.timetracker.controller;

import fhtw.timetracker.model.SignUpUser;
import fhtw.timetracker.model.UserDTO;
import fhtw.timetracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public void createUser(@RequestBody SignUpUser signUpUser) {
        userService.createUser(signUpUser.getLogin(), signUpUser.getFirstname(), signUpUser.getLastname(), signUpUser.getRole(), signUpUser.getPasswordPlain());
    }

    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) throws Exception {
        return userService.findByUsername(username);
    }

    @DeleteMapping("/users/{userId}")
    public boolean deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
