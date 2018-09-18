package docbuddy.users.persistence.dao;

import docbuddy.users.model.User;
import docbuddy.users.persistence.Result;

import java.sql.SQLException;

public interface UserDao {
    Long createUser(User user);

    User getUser(Long userId) throws SQLException;

    void updateUser(User user);

    void deleteUser(Long userId);

    Result<User> getAllUsers(String starterCursor) throws SQLException;
}
