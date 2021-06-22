package by.talai.service;

import by.talai.model.AutomobileAttachment;
import by.talai.model.personnel.Driver;
import by.talai.model.personnel.User;
import by.talai.service.dto.DriverDto;
import by.talai.service.dto.UsersDto;

import java.util.List;

public interface UserService {

    void addUser(User user, String password) throws Exception;

    User findUser(int userId) throws Exception;

    User findUser(String login) throws Exception;

    List<User> findAllUsers() throws Exception;


    List<Driver> getAllDrivers() throws Exception;

    List<User> findAllUsersOnPage(int pageNum, int pageSize) throws ServiceException;

    void changeUserStatus(int userId, int statusId) throws Exception;

    UsersDto getAllUsersDto() throws Exception;

    void addUser(String name, String surname, String login, String password, int roleId, int statusId) throws ServiceException;

    boolean validate(String login, String password) throws Exception;

    AutomobileAttachment getCurrentAttachedAutomobile(Driver driver);

    List<DriverDto> getAllDriversDtoList() throws Exception;
}
