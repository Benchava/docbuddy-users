package docbuddy.users.service;

import docbuddy.users.model.User;
import docbuddy.users.persistence.Result;

import java.sql.SQLException;

public interface UserService {

    public Long createUser(User user);

    public User getUser(Long userId) throws SQLException;

    public void updateUser(User user);

    public void deleteUser(Long id);

    public Result<User> getAllUsers() throws SQLException;
}
