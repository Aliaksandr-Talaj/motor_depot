package by.talai.data.dao.interfaces;


import by.talai.model.personnel.Driver;

import java.util.List;

public interface DriverDao {

    // create driver
    String createDriver(Driver driver);

    // get driver by id
    Driver getDriver(String id);

    // get all drivers
    List<Driver> getAllDrivers();

    //update driver
    void updateDriver(Driver driver);

    // delete driver
    boolean deleteDriver(String id);

}
