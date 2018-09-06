package docbuddy.service.controllers;

import com.google.firebase.FirebaseException;
import docbuddy.exceptions.JacksonUtilityException;
import docbuddy.model.Doctor;
import docbuddy.model.Patient;
import docbuddy.persistence.Firebase;
import docbuddy.responses.FirebaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @RequestMapping("/add/doctor")
    public FirebaseResponse addDoctor(@RequestBody Doctor doctor) throws FirebaseException, UnsupportedEncodingException, JacksonUtilityException {
        // create the firebase
        Firebase firebase = new Firebase();

        log.info("Preparing Doctor object for insertion.");
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put(doctor.getId(), doctor);


        log.info("About to insert object in Firebase.");
        return firebase.put("users/doctors/", dataMap);
    }

    @RequestMapping("/add/patient")
    public FirebaseResponse addPatient(@RequestBody Patient patient) throws FirebaseException, UnsupportedEncodingException, JacksonUtilityException {
        // create the firebase
        Firebase firebase = new Firebase();

        log.info("Preparing Patient object for insertion.");
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put(patient.getId(), patient);


        log.info("About to insert object in Firebase.");
        return firebase.put("users/patients/", dataMap);
    }
}
