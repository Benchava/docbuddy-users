package docbuddy.users.service;

import docbuddy.users.service.request.LoginRequest;
import docbuddy.users.service.responses.LoginResponse;

public interface AuthService {

    public LoginResponse login(LoginRequest request);
}
