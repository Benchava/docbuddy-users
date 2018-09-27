package docbuddy.users.config;

import docbuddy.users.config.session.TokenConstants;
import docbuddy.users.exceptions.InvalidAccessException;
import docbuddy.users.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Value("${jwt.token.lifetime}")
    private Integer tokenExpiredDays;

    @Value("${jwt.secret}")
    private String secret;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String requestToken = request.getHeader(TokenConstants.HEADER_TOKEN);

        //TODO:: validate token expiration date
        if (requestToken == null) {
            throw new InvalidAccessException();
        }

        try {
            validateToken(requestToken);
        } catch (InvalidTokenException ex) {
            throw new InvalidAccessException();
        }


        return true;

    }

    private void validateToken(String token) throws InvalidTokenException {

        Claims claims = Jwts.parser()
                .setSigningKey(TextCodec.BASE64.encode(secret))
                .parseClaimsJws(token).getBody();

        //Validate subject
        if (claims.getSubject() == null) {
            throw new InvalidTokenException();
        }


        //Validate expiraton date

        Calendar calendar = Calendar.getInstance();

        Date generatedDate = claims.getIssuedAt();

        Date today = calendar.getTime();

        calendar.setTime(generatedDate);

        calendar.add(Calendar.DATE, tokenExpiredDays);

        Date expirationDate = calendar.getTime();

        if (expirationDate.before(today)) {
            throw new InvalidTokenException();
        }

    }

}
