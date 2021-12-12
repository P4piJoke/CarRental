package com.p4pijk.carrental.dao.user;

import com.p4pijk.carrental.dao.DAO;
import com.p4pijk.carrental.model.user.User;

public interface UserDao extends DAO<User> {

    User getUserByLoginAndPassword(String login, String password);

    boolean topUpBalance(long userId, double balance);

    boolean changeStatus(long userId, boolean status);
}
