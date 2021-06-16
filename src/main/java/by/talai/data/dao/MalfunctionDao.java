package by.talai.data.dao;


import by.talai.model.stock.Malfunction;

import java.util.List;

public interface MalfunctionDao {
    // create Malfunction
    void createMalfunction(Malfunction malfunction) throws Exception;

    // get Malfunction by id
    Malfunction getMalfunction(int id) throws Exception;

    // get all Malfunctions
    List<Malfunction> getAllMalfunctions() throws Exception;

    //update Malfunction
    void updateMalfunction(Malfunction malfunction) throws Exception;

    void addOrUpdateMalfunction(Malfunction malfunction) throws Exception;

    // delete Malfunction
    void deleteMalfunction(int id) throws Exception;


}
