package by.talai.data.dao;

import by.talai.model.stock.Equipment;

import java.util.List;
import java.util.Set;

/**
 * The interface Equipment dao.
 */
public interface EquipmentDao {

    /**
     * Create equipment.
     *
     * @param equipment the equipment
     * @throws Exception the exception
     */
// create Equipment
    void createEquipment(Equipment equipment) throws Exception;

    /**
     * Gets equipment.
     *
     * @param id the id
     * @return the equipment
     * @throws Exception the exception
     */
// get Equipment by id
    Equipment getEquipment(int id) throws Exception;

    /**
     * Gets all equipment.
     *
     * @return the all equipment
     * @throws Exception the exception
     */
// get all Equipment
    List<Equipment> getAllEquipment() throws Exception;

    /**
     * Update equipment.
     *
     * @param equipment the equipment
     * @throws Exception the exception
     */
//update Equipment
    void updateEquipment(Equipment equipment) throws Exception;

    /**
     * Delete equipment.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete Equipment
    void deleteEquipment(int id) throws Exception;


    /**
     * Add equipment to request.
     *
     * @param equipmentId the equipment id
     * @param requestId   the request id
     * @throws Exception the exception
     */
// add required equipment to the request
    void addEquipmentToRequest(int equipmentId, String requestId) throws Exception;

    /**
     * Gets all equipment of request.
     *
     * @param requestId the request id
     * @return the all equipment of request
     * @throws Exception the exception
     */
// get  all required equipment of the request
    Set<Equipment> getAllEquipmentOfRequest(String requestId) throws Exception;

    /**
     * Remove equipment from request.
     *
     * @param equipmentId the equipment id
     * @param requestId   the request id
     * @throws Exception the exception
     */
//remove required equipment from the request
    void removeEquipmentFromRequest(int equipmentId, String requestId) throws Exception;
}
