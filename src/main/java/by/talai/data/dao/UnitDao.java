package by.talai.data.dao;

import by.talai.model.Unit;

import java.util.List;

public interface UnitDao {
    // create Unit
    String createUnit(Unit unit);

    // get Unit by id
    Unit getUnit(String id);

    // get all Units
    List<Unit> getAllUnits();

    //update Unit
    void updateUnit(Unit unit);

    // delete Unit
    boolean deleteUnit(String id);
}
