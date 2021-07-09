package by.talai.service;

import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.AutomobileAttachment;
import by.talai.model.personnel.Driver;
import by.talai.model.personnel.User;
import by.talai.service.dto.DriverDto;
import by.talai.service.dto.UsersDto;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Add user.
     *
     * @param user     the user
     * @param password the password
     * @throws Exception the exception
     */
    void addUser(User user, String password) throws Exception;

    /**
     * Find user user.
     *
     * @param userId the user id
     * @return the user
     * @throws Exception the exception
     */
    User findUser(int userId) throws Exception;

    /**
     * Find user user.
     *
     * @param login the login
     * @return the user
     * @throws Exception the exception
     */
    User findUser(String login) throws Exception;

    /**
     * Find all users list.
     *
     * @return the list
     * @throws Exception the exception
     */
    List<User> findAllUsers() throws Exception;


    /**
     * Gets all drivers.
     *
     * @return the all drivers
     * @throws Exception the exception
     */
    List<Driver> getAllDrivers() throws Exception;

    /**
     * Gets driver.
     *
     * @param driverId the driver id
     * @return the driver
     * @throws ServiceException        the service exception
     * @throws ConnectionPoolException the connection pool exception
     */
    Driver getDriver(int driverId) throws ServiceException, ConnectionPoolException;

    /**
     * Find all users on page list.
     *
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllUsersOnPage(int pageNum, int pageSize) throws ServiceException;

    /**
     * Change user status.
     *
     * @param userId   the user id
     * @param statusId the status id
     * @throws Exception the exception
     */
    void changeUserStatus(int userId, int statusId) throws Exception;

    /**
     * Gets all users dto.
     *
     * @return the all users dto
     * @throws Exception the exception
     */
    UsersDto getAllUsersDto() throws Exception;

    /**
     * Add user.
     *
     * @param name     the name
     * @param surname  the surname
     * @param login    the login
     * @param password the password
     * @param roleId   the role id
     * @param statusId the status id
     * @throws ServiceException the service exception
     */
    void addUser(String name, String surname, String login, String password, int roleId, int statusId) throws ServiceException;

    /**
     * Validate boolean.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     * @throws Exception the exception
     */
    boolean validate(String login, String password) throws Exception;

    /**
     * Gets current attached automobile.
     *
     * @param driver the driver
     * @return the current attached automobile
     */
    AutomobileAttachment getCurrentAttachedAutomobile(Driver driver);

    /**
     * Gets all drivers dto list.
     *
     * @return the all drivers dto list
     * @throws Exception the exception
     */
    List<DriverDto> getAllDriversDtoList() throws Exception;
}
