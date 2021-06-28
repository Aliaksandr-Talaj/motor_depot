package by.talai.service;

import by.talai.model.Unit;

public interface UnitService {


   
    int addNewUnit(String unitType, int unitLength, int unitWidth,
                   double unitHeight, double unitWeight) throws Exception;

    Unit findUnit(int unitId) throws Exception;
}
