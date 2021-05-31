package by.talai.data.dao.interfaces;

import by.talai.model.Cargo;

import java.util.List;

public interface CargoDao {

    // create cargo
    String createCargo(Cargo cargo);

    // get cargo by id
    Cargo getCargo(String id);

    // get all cargo
    List<Cargo> getAllCargos();

    //update cargo
    void updateCargo(Cargo cargo);

    // delete cargo
    boolean deleteCargo(String id);

}
