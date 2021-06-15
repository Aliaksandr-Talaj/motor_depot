package by.talai.data.dao.impl;

import by.talai.data.dao.ConnectionPool;
import by.talai.data.dao.EquipmentDao;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Equipment;

import java.util.List;
import java.util.Set;

public class EquipmentDaoImpl implements EquipmentDao {


    @Override
    public void createEquipment(Equipment equipment) {

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
    public void deleteEquipment(int id) {

    }


    @Override
    public void addEquipmentToRequest(int equipmentId, int requestId) {

    }

    @Override
    public Set<Equipment> getAllEquipmentOfRequest(int requestId) {
        return null;
    }

    @Override
    public void removeEquipmentFromRequest(int equipmentId, int requestId) {

    }
}
