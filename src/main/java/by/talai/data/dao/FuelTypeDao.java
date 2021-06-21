package by.talai.data.dao;

import by.talai.model.stock.FuelType;

import java.util.Set;

public interface FuelTypeDao {

    //create fuel type
    void createFuelType(FuelType fuelType) throws Exception;

    //find fuel type
    FuelType findFuelType(int id) throws Exception;

    //find all fuel types
    Set<FuelType> findAllFuelTypes() throws Exception;

    //update fuel type
    void updateFuelType(FuelType fuelType) throws Exception;

    //delete fuel type
    void deleteFuelType(int id) throws Exception;
}
