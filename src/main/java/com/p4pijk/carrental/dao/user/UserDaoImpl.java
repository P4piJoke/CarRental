package com.p4pijk.carrental.dao.user;

import com.p4pijk.carrental.connection.BasicConnectionPool;
import com.p4pijk.carrental.dao.QUERY;
import com.p4pijk.carrental.model.user.User;
import com.p4pijk.carrental.util.UserUtil;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class);
    private static UserDaoImpl instance;
    private static BasicConnectionPool basicConnectionPool;
    private Connection connection;

    private UserDaoImpl() {
        try {
            basicConnectionPool = BasicConnectionPool.create();
            connection = basicConnectionPool.getConnection();
        } catch (SQLException exception) {
            log.error(exception);
        }
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public User save(User user) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CREATE_USER.query(),
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setBoolean(6, user.isStatus());
            statement.setLong(7, user.getUserRole().ordinal() + 1);
            statement.setDouble(8, user.getBalance());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.error("User creation is failed");
            } else {
                log.info("User creation is successful");
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        User.builder().id(generatedKeys.getLong(1));
                    } else {
                        log.error("Failed to create user, no obtained id");
                    }
                }
            }
        } catch (SQLException exception) {
            user = User.builder().id(-1).build();
            log.error(exception);
        }
        return user;
    }

    @Override
    public void update(User user, String[] params) {
        throw new NotImplementedException();
    }

    @Override
    public User get(long id) {
        try (PreparedStatement statement = connection.prepareStatement(QUERY.GET_USER.query())) {
            statement.setLong(1, id);
            statement.execute();
            return getUserFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public User checkLogin(String login) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CHECK_USER.query())) {
            statement.setString(1, login);
            statement.execute();
            return getUserFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public void delete(User user) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> getAll() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(QUERY.GET_ALL_USERS.query());
            return getUsersFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return Collections.emptyList();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        log.info("Search for user by login and password");

        try (PreparedStatement statement = connection
                .prepareStatement(QUERY.GET_USER_BY_LOGIN_AND_PASSWORD.query())) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();
            return getUserFromResultSet(statement.getResultSet());
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    @Override
    public boolean topUpBalance(long userId, double balance) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.TOP_UP.query())) {
            statement.setDouble(1, balance);
            statement.setLong(2, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.info("Top up was failed");
                return false;
            } else {
                log.info("Top up was successful");
                return true;
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return false;
    }

    @Override
    public boolean changeStatus(long userId, boolean status) {
        try (PreparedStatement statement =
                     connection.prepareStatement(QUERY.CHANGE_USER_STATUS.query())) {
            statement.setBoolean(1, status);
            statement.setLong(2, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.info("Change status was failed");
                return false;
            } else {
                log.info("Change status was successful");
                return true;
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return false;
    }

    private User getUserFromResultSet(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return buildUserFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    private List<User> getUsersFromResultSet(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                users.add(buildUserFromResultSet(resultSet));
                log.info("User was fount and packed in object");
            }
        } catch (SQLException exception) {
            log.error(exception);
        }
        return users;
    }

    private User buildUserFromResultSet(ResultSet resultSet) throws SQLException {
        log.info("User was found and packed in object");
        return UserUtil.createUserFromResultSet(resultSet);
    }
}
