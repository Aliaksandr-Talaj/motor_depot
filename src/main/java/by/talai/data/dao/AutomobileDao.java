package by.talai.data.dao;

import by.talai.model.AutomobileAttachment;
import by.talai.model.stock.Equipment;
import by.talai.model.stock.LoadingType;
import by.talai.model.stock.Automobile;
import by.talai.model.stock.Maintenance;
import by.talai.model.stock.Malfunction;

import java.util.List;
import java.util.Set;

/**
 * The interface Automobile dao.
 */
public interface AutomobileDao {

    /**
     * Create automobile.
     *
     * @param automobile the automobile
     * @throws Exception the exception
     */
// CREATE automobile
    void createAutomobile(Automobile automobile) throws Exception;

    /**
     * Gets automobile.
     *
     * @param id the id
     * @return the automobile
     * @throws Exception the exception
     */
// GET automobile by id
    Automobile getAutomobile(String id) throws Exception;

    /**
     * Gets all automobiles.
     *
     * @return the all automobiles
     * @throws Exception the exception
     */
// GET ALL automobiles
    List<Automobile> getAllAutomobiles() throws Exception;

    /**
     * Gets all ready automobiles.
     *
     * @return the all ready automobiles
     * @throws Exception the exception
     */
    List<Automobile> getAllReadyAutomobiles() throws Exception;

    /**
     * Update automobile.
     *
     * @param automobile the automobile
     * @throws Exception the exception
     */
//UPDATE automobile
    void updateAutomobile(Automobile automobile) throws Exception;

    /**
     * Delete automobile.
     *
     * @param id the id
     * @throws Exception the exception
     */
// DELETE automobile
    void deleteAutomobile(String id) throws Exception;


    /**
     * Add equipment to automobile.
     *
     * @param equipmentId  the equipment id
     * @param automobileId the automobile id
     * @throws Exception the exception
     */
//Interactions with EQUIPMENT
    // ADD equipment to the automobile
    void addEquipmentToAutomobile(int equipmentId, String automobileId) throws Exception;

    /**
     * Gets all equipment on automobile.
     *
     * @param automobileId the automobile id
     * @return the all equipment on automobile
     * @throws Exception the exception
     */
// GET ALL equipment ON the automobile
    Set<Equipment> getAllEquipmentOnAutomobile(String automobileId) throws Exception;

    /**
     * Remove equipment from automobile.
     *
     * @param equipmentId  the equipment id
     * @param automobileId the automobile id
     * @throws Exception the exception
     */
//REMOVE equipment FROM the automobile
    void removeEquipmentFromAutomobile(int equipmentId, String automobileId) throws Exception;


    /**
     * Add loading type to automobile.
     *
     * @param loadingTypeId the loading type id
     * @param automobileId  the automobile id
     * @throws Exception the exception
     */
//Interactions with LOADING TYPES
    // ADD loading type TO the automobile
    void addLoadingTypeToAutomobile(int loadingTypeId, String automobileId) throws Exception;

    /**
     * Gets all loading types of automobile.
     *
     * @param automobileId the automobile id
     * @return the all loading types of automobile
     * @throws Exception the exception
     */
// GET ALL loading types OF the automobile
    Set<LoadingType> getAllLoadingTypesOfAutomobile(String automobileId) throws Exception;

    /**
     * Remove loading type from automobile.
     *
     * @param loadingTypeId the loading type id
     * @param automobileId  the automobile id
     * @throws Exception the exception
     */
//REMOVE loading type FROM the automobile
    void removeLoadingTypeFromAutomobile(int loadingTypeId, String automobileId) throws Exception;


    /**
     * Gets malfunctions of automobile.
     *
     * @param automobile the automobile
     * @return the malfunctions of automobile
     * @throws Exception the exception
     */
//Interaction with MALFUNCTIONS
    //GET ALL malfunctions OF the automobile
    List<Malfunction> getMalfunctionsOfAutomobile(Automobile automobile) throws Exception;

    /**
     * Gets maintenance of automobile.
     *
     * @param automobile the automobile
     * @return the maintenance of automobile
     * @throws Exception the exception
     */
//Interaction with MAINTENANCE
    //GET ALL maintenance OF automobile
    List<Maintenance> getMaintenanceOfAutomobile(Automobile automobile) throws Exception;

    /**
     * Find automobile attachments list.
     *
     * @param automobile the automobile
     * @return the list
     * @throws Exception the exception
     */
//Interaction with AUTOMOBILE ATTACHMENTS
    //find attachments of automobile
    List<AutomobileAttachment> findAutomobileAttachments(Automobile automobile) throws Exception;


}
