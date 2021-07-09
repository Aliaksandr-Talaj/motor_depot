package by.talai.data.dao;

import by.talai.model.Unit;

import java.util.List;

/**
 * The interface Unit dao.
 */
public interface UnitDao {
    /**
     * Create unit.
     *
     * @param unit the unit
     * @throws Exception the exception
     */
    abstract void createUnit(Unit unit) throws Exception;

    /**
     * Create unit return id int.
     *
     * @param unit the unit
     * @return the int
     * @throws Exception the exception
     */
    int createUnitReturnId(Unit unit) throws Exception;

    /**
     * Gets unit.
     *
     * @param id the id
     * @return the unit
     * @throws Exception the exception
     */
// get Unit by id
    Unit getUnit(int id) throws Exception;

    /**
     * Gets all units.
     *
     * @return the all units
     * @throws Exception the exception
     */
// get all Units
    List<Unit> getAllUnits() throws Exception;

    /**
     * Update unit.
     *
     * @param unit the unit
     * @throws Exception the exception
     */
//update Unit
    void updateUnit(Unit unit) throws Exception;

    /**
     * Delete unit.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete Unit
    void deleteUnit(int id) throws Exception;
}
