package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.Address;
import by.talai.model.Charterer;

import java.util.List;
import java.util.Set;

/**
 * The interface Charterer dao.
 */
public interface ChartererDao {

    /**
     * Create charterer.
     *
     * @param charterer the charterer
     * @throws Exception the exception
     */
// create charterer
    void createCharterer(Charterer charterer) throws Exception;

    /**
     * Gets charterer.
     *
     * @param id the id
     * @return the charterer
     * @throws Exception the exception
     */
// get charterer by id
    Charterer getCharterer(int id) throws Exception;

    /**
     * Gets all charterers.
     *
     * @return the all charterers
     * @throws Exception the exception
     */
// get all charterer
    List<Charterer> getAllCharterers() throws Exception;

    /**
     * Update charterer.
     *
     * @param charterer the charterer
     * @throws Exception the exception
     */
//update charterer
    void updateCharterer(Charterer charterer) throws Exception;

    /**
     * Delete charterer.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete charterer
    void deleteCharterer(int id) throws Exception;


    /**
     * Add address to charterer.
     *
     * @param addressId   the address id
     * @param chartererId the charterer id
     * @throws Exception the exception
     */
// add address
    void addAddressToCharterer(int addressId, int chartererId) throws Exception;

    /**
     * Find used addresses set.
     *
     * @param chartererId the charterer id
     * @return the set
     * @throws Exception the exception
     */
// find used addresses
    Set<Address> findUsedAddresses(int chartererId) throws Exception;

    /**
     * Delete usage of address.
     *
     * @param addressId   the address id
     * @param chartererId the charterer id
     * @throws Exception the exception
     */
// delete the usage of the address
    void deleteUsageOfAddress(int addressId, int chartererId) throws Exception;

    /**
     * Create charterer int.
     *
     * @param name    the name
     * @param surname the surname
     * @param address the address
     * @return the int
     * @throws DaoException the dao exception
     */
    int createCharterer(String name, String surname, Address address) throws DaoException;

    /**
     * Create charterer int.
     *
     * @param name      the name
     * @param surname   the surname
     * @param country   the country
     * @param region    the region
     * @param locality  the locality
     * @param street    the street
     * @param building  the building
     * @param apartment the apartment
     * @return the int
     * @throws DaoException the dao exception
     */
    int createCharterer(String name, String surname, String country, String region,
                        String locality,String street, String building, String apartment) throws DaoException;
}
