package by.talai.data.dao;

import by.talai.model.Equipment;

import java.util.List;
import java.util.Set;

public interface EquipmentDao {

    // create Equipment
    void createEquipment(Equipment equipment);

    // get Equipment by id
    Equipment getEquipment(int id);

    // get all Equipment
    List<Equipment> getAllEquipment();

    //update Equipment
    void updateEquipment(Equipment equipment);

    // delete Equipment
    void deleteEquipment(int id);





    // add required equipment to the request
    void addEquipmentToRequest(int equipmentId, int requestId);

    // get  all required equipment of the request
    Set<Equipment> getAllEquipmentOfRequest(int requestId);

    //remove required equipment from the request
    void removeEquipmentFromRequest(int equipmentId, int requestId);

}
