package by.talai.data.dao;

import by.talai.model.Unit;

import java.util.List;

public interface UnitDao {
    // create Unit
    void createUnit(Unit unit) throws Exception;

    // get Unit by id
    Unit getUnit(int id) throws Exception;

    // get all Units
    List<Unit> getAllUnits() throws Exception;

    //update Unit
    void updateUnit(Unit unit) throws Exception;

    // delete Unit
    void deleteUnit(int id) throws Exception;
}
