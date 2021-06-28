package by.talai.service;

import by.talai.data.exception.DaoException;
import by.talai.model.Address;
import by.talai.model.Charterer;

public interface AddressService {
    int addNewAddress(String country, String region, String locality, String street, String building, String apartment) throws DaoException;

    Address addAddressIfNew(Address address) throws DaoException;

}
