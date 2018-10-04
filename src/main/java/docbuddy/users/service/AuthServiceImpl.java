package docbuddy.users.service;


import docbuddy.users.model.User;
import docbuddy.users.persistence.dao.UserDao;
import docbuddy.users.service.request.LoginRequest;
import docbuddy.users.service.responses.LoginResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Service
public class AuthServiceImpl implements AuthService {

//    @Value("${jwt.secret}")
    private String secret;


    @Autowired
    private UserDao userDao;

    public LoginResponse login(LoginRequest request) {
        User example = new User();
        example.setUserName(request.getUserName());
        example.setPassword(request.getPassword());

        User response = userDao.login(example);

        LoginResponse loginResponse = new LoginResponse();
        if (response != null) {
            // Generate and persist token

            loginResponse.setToken(generateToken(response));
            loginResponse.setUserId(response.getId());


        }
        return loginResponse;
    }

    private String generateToken(User user) {

        Date now = Calendar.getInstance().getTime();

        final String jwt = Jwts.builder()
                .setSubject(user.getUserName())
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
                .compact();

        return jwt;
    }


}
