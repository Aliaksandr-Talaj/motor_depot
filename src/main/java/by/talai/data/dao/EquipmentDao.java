package by.talai.data.dao;

import by.talai.model.Equipment;

import java.util.List;

public interface EquipmentDao {

    // create Equipment
    String createEquipment(Equipment equipment);

    // get Equipment by id
    Equipment getEquipment(String id);

    // get all Equipment
    List<Equipment> getAllEquipment();

    //update Equipment
    void updateEquipment(Equipment equipment);

    // delete Equipment
    boolean deleteEquipment(String id);

}
