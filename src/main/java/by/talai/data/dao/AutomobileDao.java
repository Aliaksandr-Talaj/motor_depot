package by.talai.data.dao;

import by.talai.model.stock.Automobile;

import java.util.List;

public interface AutomobileDao {

    // create automobile
    void createAutomobile(Automobile automobile) throws Exception;

    // get automobile by id
    Automobile getAutomobile(String id) throws Exception;

    // get all automobiles
    List<Automobile> getAllAutomobiles() throws Exception;

    //update automobile
    void updateAutomobile(Automobile automobile) throws Exception;

    // delete automobile
    void deleteAutomobile(String id) throws Exception;

}
