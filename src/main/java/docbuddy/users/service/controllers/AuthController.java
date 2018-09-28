package docbuddy.users.service.controllers;

import docbuddy.users.service.AuthService;
import docbuddy.users.service.request.LoginRequest;
import docbuddy.users.service.responses.LoginResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@NoArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest login) {
        return authService.login(login);
    }
}
