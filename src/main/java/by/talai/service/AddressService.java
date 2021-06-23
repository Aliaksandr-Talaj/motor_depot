package by.talai.service;

import by.talai.data.exception.DaoException;

public interface AddressService {
    int addNewAddress(String country, String region, String locality, String street, String building, String apartment) throws DaoException;
}
