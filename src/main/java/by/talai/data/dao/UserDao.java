package by.talai.data.dao;

import by.talai.model.personnel.User;

import java.util.List;

public interface UserDao {

    // create user
    void createUser(User user);

    // get user by id
    User getUser(int id);

    // get all users
    List<User> getAllUsers();

    // get all users with role
    List<User> getAllUsersWithRole(int roleId);

    //update user
    void updateUser(User user);

    // delete user
    void deleteUser(int id);

}
