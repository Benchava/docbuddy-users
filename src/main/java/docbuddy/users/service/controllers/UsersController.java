package docbuddy.users.service.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import docbuddy.users.model.User;
import docbuddy.users.persistence.Result;
import docbuddy.users.service.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@NoArgsConstructor
public class UsersController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public Long addUser(@RequestBody User user) {
		log.info("About to insert object in Firebase.");
		return userService.createUser(user);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public User getUser(@RequestParam Long id) throws SQLException {
		return userService.getUser(id);
	}

	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public Result<User> getAllUser() throws SQLException {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public void updateUser(@RequestBody User updatedUser) {
		log.info("About to update object in Firebase.");
		userService.updateUser(updatedUser);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteUser(@RequestParam Long id) {
		userService.deleteUser(id);
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public User checkUser(@RequestBody User userRequest) {
		return userService.getUser(userRequest);

	}
}
