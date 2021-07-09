package by.talai.service;

import by.talai.data.exception.DaoException;
import by.talai.model.Address;
import by.talai.model.Charterer;

/**
 * The interface Address service.
 */
public interface AddressService {
    /**
     * Add new address int.
     *
     * @param country   the country
     * @param region    the region
     * @param locality  the locality
     * @param street    the street
     * @param building  the building
     * @param apartment the apartment
     * @return the int
     * @throws DaoException the dao exception
     */
    int addNewAddress(String country, String region, String locality, String street, String building, String apartment) throws DaoException;

    /**
     * Add address if new address.
     *
     * @param address the address
     * @return the address
     * @throws DaoException the dao exception
     */
    Address addAddressIfNew(Address address) throws DaoException;

}
