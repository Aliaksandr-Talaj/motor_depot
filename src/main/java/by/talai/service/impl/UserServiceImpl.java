package by.talai.service.impl;

import by.talai.data.dao.StatusDao;
import by.talai.data.dao.UserDao;
import by.talai.data.dao.impl.UserDaoImpl;
import by.talai.data.dao.impl.UserStatusDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Status;
import by.talai.model.personnel.User;
import by.talai.service.ServiceException;
import by.talai.service.UserService;
import by.talai.service.dto.UsersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    private final StatusDao userStatusDao = new UserStatusDaoImpl();


    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl() throws ConnectionPoolException {
    }

    @Override
    public void addUser(User user) throws Exception {
        try {
            userDao.createUser(user);
        } catch (Exception e) {
            logger.error("Sql exception in addUser() method");
            throw new ServiceException("Sql exception in addUser() method", e);
        }
    }

    @Override
    public void addUser(String name, String surname, String login, String password, int roleId, int statusId) throws ServiceException {
        try {
            userDao.createUser(name, surname, login, password, roleId, statusId);
        } catch (Exception e) {
            logger.error("Sql exception in addUser() method");
            throw new ServiceException("Sql exception in addUser() method", e);
        }
    }

    @Override
    public User findUser(int userId) throws Exception {
        User user;
        try {
            user = userDao.getUser(userId);
        } catch (Exception e) {
            logger.error("Sql exception in findUser() method");
            throw new ServiceException("Sql exception in findUser() method", e);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws Exception {
        List<User> users;
        try {
            users = userDao.getAllUsers();
        } catch (Exception e) {
            logger.error("Sql exception in findAllUsers() method");
            throw new ServiceException("Sql exception in findAllUsers() method", e);
        }
        return users;
    }

    @Override
    public List<User> findAllUsersOnPage(int pageNum, int pageSize) throws ServiceException {
        List<User> users;
        if (pageNum <= 0 || pageSize <= 0) {
            pageNum = 1;
            pageSize = 10;
        }

        try {
            int usersAmount = userDao.countAllUsers();
            users = userDao.getAllUsersOnPage(pageNum, pageSize);
        } catch (Exception e) {
            logger.error("Sql exception in findAllUsersOnPage() method");
            throw new ServiceException("Sql exception in findAllUsersOnPage() method", e);
        }
        return users;
    }

    @Override
    public void changeUserStatus(int userId, int statusId) throws Exception {
        User user = userDao.getUser((userId));
        Status status = userStatusDao.findStatus(statusId);
        user.setStatus(status);
        userDao.updateUser(user);
    }

    @Override
    public UsersDto getAllUsersDto() throws Exception {
        UsersDto usersDto = new UsersDto();
        List<User> users = userDao.getAllUsers();
        usersDto.setUsers(users);
        usersDto.setAmount(userDao.countAllUsers());
        return usersDto;
    }

}
