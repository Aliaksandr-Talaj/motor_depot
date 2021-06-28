package by.talai.data.dao;

import by.talai.model.stock.Equipment;

import java.util.List;
import java.util.Set;

public interface EquipmentDao {

    // create Equipment
    void createEquipment(Equipment equipment) throws Exception;

    // get Equipment by id
    Equipment getEquipment(int id) throws Exception;

    // get all Equipment
    List<Equipment> getAllEquipment() throws Exception;

    //update Equipment
    void updateEquipment(Equipment equipment) throws Exception;

    // delete Equipment
    void deleteEquipment(int id) throws Exception;


    // add required equipment to the request
    void addEquipmentToRequest(int equipmentId, String requestId) throws Exception;

    // get  all required equipment of the request
    Set<Equipment> getAllEquipmentOfRequest(String requestId) throws Exception;

    //remove required equipment from the request
    void removeEquipmentFromRequest(int equipmentId, String requestId) throws Exception;
}
