package by.talai.service.impl;

import by.talai.data.dao.AddressDao;
import by.talai.data.dao.impl.AddressDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao = new AddressDaoImpl();
    public static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    public AddressServiceImpl() throws ConnectionPoolException {


    }

    @Override
    public int addNewAddress(String country, String region, String locality, String street, String building,
                             String apartment) throws DaoException {
        return addressDao.createAddress(country, region, locality, street, building, apartment);
    }
}
