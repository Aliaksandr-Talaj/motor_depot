package by.talai.data.dao;

import by.talai.model.Address;

import java.util.List;
import java.util.Set;

public interface AddressDao {

    // create address
    void createAddress(Address address) throws Exception;

    // get address by id
    Address getAddress(int id) throws Exception;

    // get all addresses
    List<Address> getAllAddresses() throws Exception;

    //update address
    void updateAddress(Address address) throws Exception;

    // delete address
    void deleteAddress(int id) throws Exception;




}
