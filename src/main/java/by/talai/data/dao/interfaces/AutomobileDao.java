package by.talai.data.dao.interfaces;

import by.talai.model.stock.Automobile;

import java.util.List;

public interface AutomobileDao {

    // create automobile
    String createAutomobile(Automobile automobile);

    // get automobile by id
    Automobile getAutomobile(String id);

    // get all automobiles
    List<Automobile> getAutomobiles();

    //update automobile
    void updateAutomobile(Automobile automobile);

    // delete automobile
    boolean deleteAutomobile(String id);

}
