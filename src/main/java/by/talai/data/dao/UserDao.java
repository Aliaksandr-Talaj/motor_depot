package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.personnel.User;

import java.util.List;

public interface UserDao {

    // create user

    void createUser(User user, String password) throws Exception;

    // get user by id
    User getUser(int id) throws Exception;

    // get all users.jsp
    List<User> getAllUsers() throws Exception;

    // get all users.jsp with role
    List<User> getAllUsersWithRole(int roleId) throws Exception;

    //update user

    void updateUser(User user) throws Exception;

    // delete user
    void deleteUser(int id) throws Exception;

    List<User> getAllUsersOnPage(int pageNum, int pageSize) throws Exception;

    int countAllUsers() throws DaoException;

    int countAllUsersWithRole(int roleId) throws DaoException;

    void createUser(String name, String surname, String login, String password, int roleId, int statusId) throws DaoException;

    User getUser(String login) throws Exception;
}
