package docbuddy.users.service.controllers;

import docbuddy.users.model.User;
import docbuddy.users.persistence.Result;
import docbuddy.users.service.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/users")
@Slf4j
@NoArgsConstructor

public class UsersController {
    @Autowired
    private UserService userService;


    @RequestMapping("/add")
    public Long addUser(@RequestBody User user) {
        log.info("About to insert object in Firebase.");
        return userService.createUser(user);
    }

    @RequestMapping("/get")
    public User getUser(@RequestParam Long id) throws SQLException {
        return userService.getUser(id);
    }

    @RequestMapping("/get/all")
    public Result<User> getAllUser() throws SQLException {
        return userService.getAllUsers();
    }

    @RequestMapping("/update")
    public void updateUser(@RequestBody User updatedUser) {
        log.info("About to update object in Firebase.");
        userService.updateUser(updatedUser);
    }

    @RequestMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
}
