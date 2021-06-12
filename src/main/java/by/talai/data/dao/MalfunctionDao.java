package by.talai.data.dao;


import by.talai.model.stock.Malfunction;

import java.util.List;

public interface MalfunctionDao {
    // create Malfunction
    String createMalfunction(Malfunction malfunction);

    // get Malfunction by id
    Malfunction getMalfunction(String id);

    // get all Malfunctions
    List<Malfunction> getAllMalfunctions();

    //update Malfunction
    void updateMalfunction(Malfunction malfunction);

    // delete Malfunction
    boolean deleteMalfunction(String id);

    //get all malfunctions of the automobile
    List<Malfunction> getMalfunctionsOfAutomobile(String id);
}
