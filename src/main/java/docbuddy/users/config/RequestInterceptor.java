package docbuddy.users.config;

import docbuddy.users.exceptions.InvalidAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class RequestInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("Interceptor calling");


        //Get session
        HttpSession session = request.getSession();

        //Perform Token Validation
        String sessionToken = (String) session.getAttribute("token");

        String requestToken = request.getHeader("");

        //TODO:: validate token expiration date
        if (requestToken == null || !Objects.equals(sessionToken, requestToken)) {
            throw new InvalidAccessException();
        }


        return true;

    }

}
