package by.talai.service.impl;

import by.talai.data.dao.AutomobileAttachmentDao;
import by.talai.data.dao.StatusDao;
import by.talai.data.dao.UserDao;
import by.talai.data.dao.impl.AutomobileAttachmentDaoImpl;
import by.talai.data.dao.impl.UserDaoImpl;
import by.talai.data.dao.impl.UserStatusDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.AutomobileAttachment;
import by.talai.model.Status;
import by.talai.model.personnel.Driver;
import by.talai.model.personnel.User;
import by.talai.service.ServiceException;
import by.talai.service.UserService;
import by.talai.service.dto.DriverDto;
import by.talai.service.dto.UsersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    private final StatusDao userStatusDao = new UserStatusDaoImpl();


    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl() throws ConnectionPoolException {
    }

    @Override
    public void addUser(User user, String password) throws Exception {
        try {
            userDao.createUser(user, password);
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
    public User findUser(String login) throws Exception {
        User user;
        try {
            user = userDao.getUser(login);
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
    public List<Driver> getAllDrivers() throws Exception {
        List<User> users;
        List<Driver> drivers = new ArrayList<>();
        AutomobileAttachmentDao automobileAttachmentDao = new AutomobileAttachmentDaoImpl();
        try {
            users = userDao.getAllUsers();
            for (User user : users) {
                Driver driver = new Driver();
                driver.setId(user.getId());
                driver.setName(user.getName());
                driver.setSurname(user.getSurname());
                driver.setAutomobileAttachments(automobileAttachmentDao.findAttachmentsOfDriver(driver));
                drivers.add(driver);
            }

        } catch (Exception e) {
            logger.error("Sql exception in findAllUsers() method");
            throw new ServiceException("Sql exception in findAllUsers() method", e);
        }
        return drivers;
    }

    @Override
    public Driver getDriver(int driverId) throws ServiceException, ConnectionPoolException {
        AutomobileAttachmentDao automobileAttachmentDao = new AutomobileAttachmentDaoImpl();
        Driver driver;
        try {

            User user = userDao.getUser(driverId);
            driver = new Driver();
            driver.setId(user.getId());
            driver.setName(user.getName());
            driver.setSurname(user.getSurname());
            driver.setAutomobileAttachments(automobileAttachmentDao.findAttachmentsOfDriver(driver));

        } catch (Exception e) {
            logger.error("Sql exception in findDriver() method");
            throw new ServiceException("Sql exception in findDriver() method", e);
        }

        return driver;
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

    @Override
    public boolean validate(String login, String password) throws Exception {
        User user = userDao.getUser(login);
        return user.validateUser(password);

    }

    @Override
    public AutomobileAttachment getCurrentAttachedAutomobile(Driver driver) {
        List<AutomobileAttachment> automobileAttachments = driver.getAutomobileAttachments();
        AutomobileAttachment automobileAttachment = new AutomobileAttachment();
        java.util.Date now = new java.util.Date();
        Date currentDate = new Date(now.getTime());
        for (AutomobileAttachment attachment : automobileAttachments) {
            if (attachment.getDateOfDetachment() == null) {
                Date date = attachment.getDateOfAttachment();
                if (!date.after(currentDate)) {
                    return attachment;
                }

            }
        }
        return automobileAttachment;
    }

    @Override
    public List<DriverDto> getAllDriversDtoList() throws Exception {

        List<Driver> drivers = getAllDrivers();

        List<DriverDto> driverDtoList = new ArrayList<>();
        for (Driver driver : drivers) {
            DriverDto driverDto = new DriverDto();
            driverDto.setDriver(driver);
            AutomobileAttachment attachment = getCurrentAttachedAutomobile(driver);
            driverDto.setCurrentAttachment(attachment);
            driverDtoList.add(driverDto);
        }
        return driverDtoList;
    }

}


