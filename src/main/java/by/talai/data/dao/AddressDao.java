package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.Address;

import java.util.List;

/**
 * The interface Address dao.
 */
public interface AddressDao {

    /**
     * Create address address.
     *
     * @param address the address
     * @return the address
     * @throws Exception the exception
     */
// create address
    Address createAddress(Address address) throws Exception;

    /**
     * Gets address.
     *
     * @param id the id
     * @return the address
     * @throws Exception the exception
     */
// get address by id
    Address getAddress(int id) throws Exception;

    /**
     * Gets all addresses.
     *
     * @return the all addresses
     * @throws Exception the exception
     */
// get all addresses
    List<Address> getAllAddresses() throws Exception;

    /**
     * Update address.
     *
     * @param address the address
     * @throws Exception the exception
     */
//update address
    void updateAddress(Address address) throws Exception;

    /**
     * Delete address.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete address
    void deleteAddress(int id) throws Exception;


    /**
     * Create address int.
     *
     * @param country   the country
     * @param region    the region
     * @param locality  the locality
     * @param street    the street
     * @param building  the building
     * @param apartment the apartment
     * @return the id of the created address
     * @throws DaoException the dao exception
     */
    int createAddress(String country, String region, String locality, String street, String building, String apartment)
            throws DaoException;

    /**
     * Create address if not repeats address.
     *
     * @param address the address
     * @return the address with new id
     * @throws DaoException the dao exception
     */
    Address createAddressIfNotRepeats(Address address) throws DaoException;
}
