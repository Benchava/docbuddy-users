package docbuddy.users.service.users.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.FirebaseException;
import docbuddy.users.exceptions.DataNotFoundException;
import docbuddy.users.exceptions.JacksonUtilityException;
import docbuddy.users.exceptions.ServerException;
import docbuddy.users.model.User;
import docbuddy.users.persistence.Firebase;
import docbuddy.users.service.users.responses.FirebaseResponse;
import docbuddy.users.util.JacksonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {
    private Firebase firebaseManager;

    public UsersController() {
        try {
            this.firebaseManager = new Firebase();
        } catch (FirebaseException e) {
            log.error("There was an error trying to instantiate the Firebase manager.");
            throw new ServerException(e);
        }
    }

    @RequestMapping("/add")
    public FirebaseResponse addUser(@RequestBody User user) throws FirebaseException, UnsupportedEncodingException, JsonProcessingException {
        log.info("About to insert object in Firebase.");
        return firebaseManager.put(generateUserPath(user.getId()), new ObjectMapper().writeValueAsString(user));
    }

    @RequestMapping("/get")
    public User getUser(@RequestParam String id) throws IOException, FirebaseException, JacksonUtilityException {
        FirebaseResponse response = firebaseManager.get(generateUserPath(id));

        if (response.getCode() == 200 && response.getBody().size() > 0) {
            return new ObjectMapper().readValue(JacksonUtility.getJsonStringFromMap(response.getBody()), User.class);
        } else {
            throw new DataNotFoundException();
        }
    }

    @RequestMapping("/update")
    public FirebaseResponse updateUser(@RequestParam String id, @RequestBody User updatedUser) throws UnsupportedEncodingException, FirebaseException, JsonProcessingException {
        log.info("About to update object in Firebase.");
        return firebaseManager.patch(generateUserPath(id), new ObjectMapper().writeValueAsString(updatedUser));
    }

    @RequestMapping("/delete")
    public FirebaseResponse deleteUser(@RequestParam String id) throws UnsupportedEncodingException, FirebaseException {
        return firebaseManager.delete(generateUserPath(id));
    }

    private String generateUserPath(String id) {
        if (id == null || id.isEmpty()) {
            return "users/";
        } else {
            return "users/" + id + "/";
        }
    }
}