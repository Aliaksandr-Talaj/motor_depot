package by.talai.service;

import by.talai.data.dao.StatusDao;
import by.talai.data.dao.UserDao;
import by.talai.data.dao.impl.UserDaoImpl;
import by.talai.data.dao.impl.UserStatusDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Status;
import by.talai.model.personnel.User;
import by.talai.service.dto.UsersDto;

public class Main {


    public Main() throws ConnectionPoolException {
    }

    public static void main(String[] args) throws Exception {

        UserDao userDao = new UserDaoImpl();
        User user1 = userDao.getUser(3);
        StatusDao statusDao = new UserStatusDaoImpl();
        Status status = statusDao.findStatus(2);
        user1.setStatus(status);
        userDao.updateUser(user1);
        UsersDto usersDto = new UsersDto();
        usersDto.setUsers(userDao.getAllUsers());
        usersDto.setAmount(userDao.countAllUsers());

        for (User user : usersDto.getUsers()) {

            System.out.println(user.getId() + " " + user.getName()
                    + " " + user.getSurname() + " " + user.getStatus().getStatus());
            System.out.println();


        }
//        StatusDao statusDao = new TechnicalStatusDaoImpl();
//        Set<Status> statuses = statusDao.findAllStatuses();
//        for (Status status : statuses) {
//            System.out.println(status.getId() + " "+ status.getStatus());
//            System.out.println();
//        }
    }

}
