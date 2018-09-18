package docbuddy.users.service;

import docbuddy.users.model.User;
import docbuddy.users.persistence.BigQueryManager;
import docbuddy.users.persistence.Result;
import docbuddy.users.persistence.dao.UserDao;
import docbuddy.users.persistence.dao.UserDaoImpl;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

@Getter
@Setter
public class UserService {
    private BigQueryManager bigQueryManager;
    private UserDao userDao;

    public UserService() {
        this.bigQueryManager = new BigQueryManager();
        this.userDao = new UserDaoImpl();
    }

    public Long createUser(User user) {
        return userDao.createUser(user);
    }

    public User getUser(Long userId) throws SQLException {
        return userDao.getUser(userId);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public Result<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers("");
    }
}
