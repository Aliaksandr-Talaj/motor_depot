package by.talai.data.dao;

import by.talai.model.FuelType;

import java.util.Set;

public interface FuelTypeDao {

    //create fuel type
    void createFuelType(FuelType fuelType);

    //find fuel type
    FuelType findFuelType(int id);

    //find all fuel types
    Set<FuelType> findAllFuelTypes();

    //update fuel type
    void updateFuelType();

    //delete fuel type
    boolean deleteFuelType();
}
