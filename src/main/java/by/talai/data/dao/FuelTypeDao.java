package by.talai.data.dao;

import by.talai.model.stock.FuelType;

import java.util.Set;

/**
 * The interface Fuel type dao.
 */
public interface FuelTypeDao {

    /**
     * Create fuel type.
     *
     * @param fuelType the fuel type
     * @throws Exception the exception
     */
//create fuel type
    void createFuelType(FuelType fuelType) throws Exception;

    /**
     * Find fuel type fuel type.
     *
     * @param id the id
     * @return the fuel type
     * @throws Exception the exception
     */
//find fuel type
    FuelType findFuelType(int id) throws Exception;

    /**
     * Find all fuel types set.
     *
     * @return the set
     * @throws Exception the exception
     */
//find all fuel types
    Set<FuelType> findAllFuelTypes() throws Exception;

    /**
     * Update fuel type.
     *
     * @param fuelType the fuel type
     * @throws Exception the exception
     */
//update fuel type
    void updateFuelType(FuelType fuelType) throws Exception;

    /**
     * Delete fuel type.
     *
     * @param id the id
     * @throws Exception the exception
     */
//delete fuel type
    void deleteFuelType(int id) throws Exception;
}
