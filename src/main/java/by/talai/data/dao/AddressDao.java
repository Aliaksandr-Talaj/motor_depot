package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.Address;

import java.util.List;

public interface AddressDao {

    // create address
    Address createAddress(Address address) throws Exception;

    // get address by id
    Address getAddress(int id) throws Exception;

    // get all addresses
    List<Address> getAllAddresses() throws Exception;

    //update address
    void updateAddress(Address address) throws Exception;

    // delete address
    void deleteAddress(int id) throws Exception;


    int createAddress(String country, String region, String locality, String street, String building, String apartment) throws DaoException;

    Address createAddressIfNotRepeats(Address address) throws DaoException;
}
