package by.talai.data.dao;

import by.talai.model.Cargo;

import java.util.List;

public interface CargoDao {

    // create cargo
    void createCargo(Cargo cargo) throws Exception;

    // get cargo by id
    Cargo getCargo(int id) throws Exception;

    // get all cargo
    List<Cargo> getAllCargos() throws Exception;

    //update cargo
    void updateCargo(Cargo cargo) throws Exception;

    // delete cargo
    void deleteCargo(int id) throws Exception;

    List<Cargo> getAllCargosOfRide(int rideId) throws Exception;
}
