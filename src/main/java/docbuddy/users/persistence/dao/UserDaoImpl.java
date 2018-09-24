package docbuddy.users.persistence.dao;

import com.google.cloud.datastore.*;
import docbuddy.users.exceptions.BadRequestException;
import docbuddy.users.model.User;
import docbuddy.users.persistence.DatastoreManager;
import docbuddy.users.persistence.Result;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Getter
@Setter
public class UserDaoImpl implements UserDao {

    private DatastoreManager datastoreManager;
    private KeyFactory userKeyFactory;

    @Autowired
    public UserDaoImpl(DatastoreManager datastoreManager) {
        this.datastoreManager = datastoreManager;
        this.userKeyFactory = datastoreManager.getDatastoreClient().newKeyFactory().setKind("User");
    }

    private User entityToUser(Entity entity) {
        return User.builder()
                .id(entity.getKey().getId())
                .admin(entity.getBoolean(User.Constants.ADMIN))
                .doctor(entity.getBoolean(User.Constants.DOCTOR))
                .firstName(entity.getString(User.Constants.FIRST_NAME))
                .lastName(entity.getString(User.Constants.LAST_NAME))
                .userName(entity.getString(User.Constants.USERNAME))
                .password(entity.getString(User.Constants.PASSWORD))
                .build();
    }

    @Override
    public Long createUser(User user) {
        validateUser(user);

        IncompleteKey key = userKeyFactory.newKey();
        FullEntity<IncompleteKey> incUserEntity = Entity.newBuilder(key)
                .set(User.Constants.ADMIN, user.isAdmin())
                .set(User.Constants.DOCTOR, user.isDoctor())
                .set(User.Constants.FIRST_NAME, user.getFirstName())
                .set(User.Constants.LAST_NAME, user.getLastName())
                .set(User.Constants.USERNAME, user.getUserName())
                .set(User.Constants.PASSWORD, user.getPassword())
                .build();

        Entity userEntity = datastoreManager.getDatastoreClient().add(incUserEntity);
        return userEntity.getKey().getId();
    }

    @Override
    public User getUser(Long userId) {
        if (userId == null) {
            throw new BadRequestException("Bad request. User ID can't be null.");
        }

        Entity userEntity = datastoreManager.getDatastoreClient().get(userKeyFactory.newKey(userId));
        return entityToUser(userEntity);
    }

    @Override
    public void updateUser(User user) {
        validateUser(user);
        Key key = userKeyFactory.newKey(user.getId());

        Entity entity = Entity.newBuilder(key)
                .set(User.Constants.ADMIN, user.isAdmin())
                .set(User.Constants.DOCTOR, user.isDoctor())
                .set(User.Constants.FIRST_NAME, user.getUserName())
                .set(User.Constants.LAST_NAME, user.getLastName())
                .set(User.Constants.USERNAME, user.getUserName())
                .set(User.Constants.PASSWORD, user.getPassword())
                .build();
        datastoreManager.getDatastoreClient().update(entity);
    }

    @Override
    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new BadRequestException("Bad request. User ID can't be null.");
        }

        Key key = userKeyFactory.newKey(userId);
        datastoreManager.getDatastoreClient().delete(key);
    }

    @Override
    public Result<User> getAllUsers(String startCursorString) {
        Cursor startCursor = null;
        if (startCursorString != null && !startCursorString.equals("")) {
            startCursor = Cursor.fromUrlSafe(startCursorString);
        }

        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("User")
                .setLimit(10)
                .setStartCursor(startCursor)
                .build();

        QueryResults<Entity> resultList = datastoreManager.getDatastoreClient().run(query);
        List<User> resultUsers = entitiesToUsers(resultList);
        Cursor cursor = resultList.getCursorAfter();
        if (cursor != null && resultUsers.size() == 10) {
            String cursorString = cursor.toUrlSafe();
            return new Result<>(resultUsers, cursorString);
        } else {
            return new Result<>(resultUsers);
        }
    }

    private List<User> entitiesToUsers(QueryResults<Entity> resultList) {
        List<User> resultUsers = new ArrayList<>();
        while (resultList.hasNext()) {
            resultUsers.add(entityToUser(resultList.next()));
        }
        return resultUsers;
    }

    private void validateUser(User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new BadRequestException(String.format("Bad request. User Name for user is %s.", user.getUserName()));
        } else {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new BadRequestException(String.format("Bad request. Password for user is %s.", user.getPassword()));
            }
        }
    }
}
