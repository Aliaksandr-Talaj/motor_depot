package by.talai.data.dao;


import by.talai.model.stock.Malfunction;

import java.util.List;

public interface MalfunctionDao {
    // create Malfunction
    void createMalfunction(Malfunction malfunction);

    // get Malfunction by id
    Malfunction getMalfunction(int id);

    // get all Malfunctions
    List<Malfunction> getAllMalfunctions();

    //update Malfunction
    void updateMalfunction(Malfunction malfunction);

    // delete Malfunction
    void deleteMalfunction(int id);


}
