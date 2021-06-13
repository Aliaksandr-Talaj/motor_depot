package by.talai.data.dao;

import by.talai.model.Cargo;

import java.util.List;

public interface CargoDao {

    // create cargo
    String createCargo(Cargo cargo);

    // get cargo by id
    Cargo getCargo(int id);

    // get all cargo
    List<Cargo> getAllCargos();

    //update cargo
    void updateCargo(Cargo cargo);

    // delete cargo
    void deleteCargo(int id);

}
