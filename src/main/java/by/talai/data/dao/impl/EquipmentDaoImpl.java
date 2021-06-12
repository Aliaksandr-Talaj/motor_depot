package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.EquipmentDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Equipment;

import java.util.List;
import java.util.Set;

public class EquipmentDaoImpl implements EquipmentDao {

    ConnectionPool connectionPool = ConnectionPool.getInstance();

    public EquipmentDaoImpl() throws ConnectionPoolException {
    }

    @Override
    public String createEquipment(Equipment equipment) {
        return null;
    }

    @Override
    public Equipment getEquipment(int id) {
        return null;
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return null;
    }

    @Override
    public void updateEquipment(Equipment equipment) {

    }

    @Override
    public boolean deleteEquipment(int id) {
        return false;
    }

    @Override
    public void addToAutomobile(int id) {

    }

    @Override
    public boolean deleteFromAutomobile(int id) {
        return false;
    }

    @Override
    public Set<Equipment> getAllEquipmentOnAutomobile(String id) {
        return null;
    }
}
