package by.talai.data.dao;

import by.talai.model.personnel.User;

import java.util.List;

public interface UserDao {

    // create user
    void createUser(User user) throws Exception;

    // get user by id
    User getUser(int id) throws Exception;

    // get all users
    List<User> getAllUsers() throws Exception;

    // get all users with role
    List<User> getAllUsersWithRole(int roleId) throws Exception;

    //update user
    void updateUser(User user) throws Exception;

    // delete user
    void deleteUser(int id) throws Exception;

}
