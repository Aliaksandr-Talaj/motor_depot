package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.personnel.User;

import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDao {

    // create user

    /**
     * Create user.
     *
     * @param user     the user
     * @param password the password
     * @throws Exception the exception
     */
    void createUser(User user, String password) throws Exception;

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     * @throws Exception the exception
     */
// get user by id
    User getUser(int id) throws Exception;

    /**
     * Gets all users.
     *
     * @return the all users
     * @throws Exception the exception
     */
// get all users.jsp
    List<User> getAllUsers() throws Exception;

    /**
     * Gets all users with role.
     *
     * @param roleId the role id
     * @return the all users with role
     * @throws Exception the exception
     */
// get all users.jsp with role
    List<User> getAllUsersWithRole(int roleId) throws Exception;

    //update user

    /**
     * Update user.
     *
     * @param user the user
     * @throws Exception the exception
     */
    void updateUser(User user) throws Exception;

    /**
     * Delete user.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete user
    void deleteUser(int id) throws Exception;

    /**
     * Gets all users on page.
     *
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the all users on page
     * @throws Exception the exception
     */
    List<User> getAllUsersOnPage(int pageNum, int pageSize) throws Exception;

    /**
     * Count all users int.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int countAllUsers() throws DaoException;

    /**
     * Count all users with role int.
     *
     * @param roleId the role id
     * @return the int
     * @throws DaoException the dao exception
     */
    int countAllUsersWithRole(int roleId) throws DaoException;

    /**
     * Create user.
     *
     * @param name     the name
     * @param surname  the surname
     * @param login    the login
     * @param password the password
     * @param roleId   the role id
     * @param statusId the status id
     * @throws DaoException the dao exception
     */
    void createUser(String name, String surname, String login, String password, int roleId, int statusId) throws DaoException;

    /**
     * Gets user.
     *
     * @param login the login
     * @return the user
     * @throws Exception the exception
     */
    User getUser(String login) throws Exception;
}
