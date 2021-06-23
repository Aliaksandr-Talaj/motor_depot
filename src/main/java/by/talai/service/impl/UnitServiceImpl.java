package by.talai.service.impl;

import by.talai.data.dao.UserDao;
import by.talai.data.dao.impl.UserDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.service.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnitServiceImpl implements UnitService {

    private final UserDao userDao = new UserDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(UnitServiceImpl.class);

    public UnitServiceImpl() throws ConnectionPoolException {
    }


}
