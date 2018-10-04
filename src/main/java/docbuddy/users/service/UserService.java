package docbuddy.users.service;

import docbuddy.users.model.User;
import docbuddy.users.persistence.Result;

import java.sql.SQLException;

public interface UserService {

    Long createUser(User user);

    User getUser(Long userId) throws SQLException;
    
    User getUser(User user);
    
    void updateUser(User user);

    void deleteUser(Long id);

    Result<User> getAllUsers() throws SQLException;
    
    
    
    
}
