package by.talai.data.dao;

import by.talai.model.Equipment;

import java.util.List;
import java.util.Set;

public interface EquipmentDao {

    // create Equipment
    String createEquipment(Equipment equipment);

    // get Equipment by id
    Equipment getEquipment(int id);

    // get all Equipment
    List<Equipment> getAllEquipment();

    //update Equipment
    void updateEquipment(Equipment equipment);

    // delete Equipment
    boolean deleteEquipment(int id);

    // add Equipment to automobile
    void addToAutomobile(int id);

    // delete equipment from automobile
    boolean deleteFromAutomobile(int id);

    //get all equipment on automobile
    Set<Equipment> getAllEquipmentOnAutomobile(String id);

}
