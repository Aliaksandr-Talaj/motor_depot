package by.talai.data.dao;


import by.talai.model.personnel.Driver;

import java.util.List;

/**
 * The interface Driver dao.
 */
public interface DriverDao {

    /**
     * Create driver string.
     *
     * @param driver the driver
     * @return the string
     */
// create driver
    String createDriver(Driver driver);

    /**
     * Gets driver.
     *
     * @param id the id
     * @return the driver
     */
// get driver by id
    Driver getDriver(String id);

    /**
     * Gets all drivers.
     *
     * @return the all drivers
     */
// get all drivers
    List<Driver> getAllDrivers();

    /**
     * Update driver.
     *
     * @param driver the driver
     */
//update driver
    void updateDriver(Driver driver);

    /**
     * Delete driver boolean.
     *
     * @param id the id
     * @return the boolean
     */
// delete driver
    boolean deleteDriver(String id);

}
