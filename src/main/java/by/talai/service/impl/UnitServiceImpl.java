package by.talai.service.impl;

import by.talai.data.dao.UnitDao;
import by.talai.data.dao.impl.UnitDaoImpl;
import by.talai.model.Unit;
import by.talai.service.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnitServiceImpl implements UnitService {

    private final UnitDao unitDao = new UnitDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(UnitServiceImpl.class);

    public UnitServiceImpl() throws Exception {
    }


    @Override
    public int addNewUnit(String unitType, int unitLength, int unitWidth,
                          double unitHeight, double unitWeight) throws Exception {
        Unit unit = new Unit();
        unit.setType(unitType);
        unit.setLength(unitLength);
        unit.setWidth(unitWidth);
        unit.setHeight(unitHeight);
        unit.setWeight(unitWeight);
        return unitDao.createUnitReturnId(unit);
    }

    @Override
    public Unit findUnit(int unitId) throws Exception {
        return unitDao.getUnit(unitId);
    }
}
