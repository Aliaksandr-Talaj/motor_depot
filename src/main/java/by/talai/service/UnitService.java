package by.talai.service;

import by.talai.model.Unit;

/**
 * The interface Unit service.
 */
public interface UnitService {


    /**
     * Add new unit int.
     *
     * @param unitType   the unit type
     * @param unitLength the unit length
     * @param unitWidth  the unit width
     * @param unitHeight the unit height
     * @param unitWeight the unit weight
     * @return the int
     * @throws Exception the exception
     */
    int addNewUnit(String unitType, int unitLength, int unitWidth,
                   double unitHeight, double unitWeight) throws Exception;

    /**
     * Find unit unit.
     *
     * @param unitId the unit id
     * @return the unit
     * @throws Exception the exception
     */
    Unit findUnit(int unitId) throws Exception;
}
