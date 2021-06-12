package by.talai.data.dao;

import by.talai.model.Address;

import java.util.List;

public interface AddressDao {

    // create address
    int createAddress(Address address);

    // get address by id
    Address getAddress(int id);

    // get all addresses
    List<Address> getAllAddresses();

    //update address
    void updateAddress(Address address);

    // delete address
    boolean deleteAddress(int id);

}
