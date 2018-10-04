package docbuddy.users.service;

import docbuddy.users.model.User;
import docbuddy.users.persistence.Result;
import docbuddy.users.persistence.dao.UserDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements  UserService{
    @Autowired
    private UserDao userDao;

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

	@Override
	public User getUser(User user) {
		return userDao.login(user);
	}
}
