package by.talai.service;

import by.talai.data.exception.DaoException;
import by.talai.model.Address;
import by.talai.model.Charterer;

import java.util.List;

/**
 * The interface Charterer service.
 */
public interface ChartererService {

    /**
     * Add charterer int.
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
    int addCharterer(String name, String surname, String country, String region, String locality, String street,
                     String building, String apartment) throws DaoException;

    /**
     * Gets charterer.
     *
     * @param id the id
     * @return the charterer
     * @throws Exception the exception
     */
    Charterer getCharterer(int id) throws Exception;

    /**
     * Gets charterers.
     *
     * @return the charterers
     * @throws Exception the exception
     */
    List<Charterer> getCharterers() throws Exception;

    /**
     * Add new address to charterer.
     *
     * @param address   the address
     * @param charterer the charterer
     * @throws Exception the exception
     */
    void addNewAddressToCharterer(Address address, Charterer charterer) throws Exception;
}
