package docbuddy.users.service.controllers;

import docbuddy.users.config.session.TokenConstants;
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

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@Slf4j
@NoArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    @PostMapping
    public LoginResponse login(HttpSession session, @RequestBody LoginRequest login) {
        LoginResponse response =authService.login(login);
        session.setAttribute(TokenConstants.SESSION_TOKEN, response.getToken());
        return response;
    }

    @RequestMapping("/logout")
    @PostMapping
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
