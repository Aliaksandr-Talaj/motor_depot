package by.talai.data.dao.interfaces;

import by.talai.model.Cargo;
import by.talai.model.stock.Automobile;

import java.util.List;

public interface CargoDao {

    // create cargo
    String createCargo(Cargo cargo);

    // get cargo by id
    Automobile getAutomobile(String id);

    // get all automobiles
    List<Automobile> getAutomobiles();

    //update automobile
    void updateAutomobile(Automobile automobile);

    // delete automobile
    boolean deleteAutomobile(String id);

}
