package by.talai.service;

import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Request;
import by.talai.model.stock.Automobile;
import by.talai.service.dto.AutomobilesDto;

import java.util.List;
import java.util.Set;

/**
 * The interface Automobile service.
 */
public interface AutomobileService {

    /**
     * Save new automobile.
     *
     * @param automobile the automobile
     * @throws Exception the exception
     */
    void saveNewAutomobile(Automobile automobile) throws Exception;

    /**
     * Find automobile by id automobile.
     *
     * @param id the id
     * @return the automobile
     * @throws Exception the exception
     */
    Automobile findAutomobileById(String id) throws Exception;

    /**
     * Find automobile of driver automobile.
     *
     * @param driverId the driver id
     * @return the automobile
     * @throws Exception the exception
     */
    Automobile findAutomobileOfDriver(int driverId) throws Exception;

    /**
     * Find all automobiles list.
     *
     * @return the list
     * @throws Exception the exception
     */
    List<Automobile> findAllAutomobiles() throws Exception;

    /**
     * Update automobile.
     *
     * @param automobile the automobile
     * @throws Exception the exception
     */
    void updateAutomobile(Automobile automobile) throws Exception;

    /**
     * Gets all automobiles dto.
     *
     * @return the all automobiles dto
     * @throws Exception the exception
     */
    AutomobilesDto getAllAutomobilesDto() throws Exception;

    /**
     * Add new automobile.
     *
     * @param id             the id
     * @param brand          the brand
     * @param model          the model
     * @param fuel           the fuel
     * @param carrying       the carrying
     * @param type           the type
     * @param equipment1     the equipment 1
     * @param equipment2     the equipment 2
     * @param equipment3     the equipment 3
     * @param top            the top
     * @param rear           the rear
     * @param side           the side
     * @param platformLength the platform length
     * @param platformWidth  the platform width
     * @param heightLimit    the height limit
     * @param volumeLimit    the volume limit
     * @throws Exception the exception
     */
    void addNewAutomobile(String id, String brand, String model, String fuel, String carrying, String type,
                          String equipment1, String equipment2, String equipment3, String top, String rear,
                          String side, String platformLength, String platformWidth, String heightLimit,
                          String volumeLimit) throws Exception;

    /**
     * Find suitable automobiles set.
     *
     * @param request the request
     * @return the set
     * @throws Exception the exception
     */
    Set<Automobile> findSuitableAutomobiles(Request request) throws Exception;

    /**
     * Create malfunction.
     *
     * @param automobile the automobile
     * @param problem    the problem
     * @throws Exception the exception
     */
    void createMalfunction(Automobile automobile, String problem) throws Exception;
}
