package by.talai.data.dao;

import by.talai.model.AutomobileAttachment;
import by.talai.model.stock.Equipment;
import by.talai.model.stock.LoadingType;
import by.talai.model.stock.Automobile;
import by.talai.model.stock.Maintenance;
import by.talai.model.stock.Malfunction;

import java.util.List;
import java.util.Set;

public interface AutomobileDao {

    // CREATE automobile
    void createAutomobile(Automobile automobile) throws Exception;

    // GET automobile by id
    Automobile getAutomobile(String id) throws Exception;

    // GET ALL automobiles
    List<Automobile> getAllAutomobiles() throws Exception;

    //UPDATE automobile
    void updateAutomobile(Automobile automobile) throws Exception;

    // DELETE automobile
    void deleteAutomobile(String id) throws Exception;


    //Interactions with EQUIPMENT
    // ADD equipment to the automobile
    void addEquipmentToAutomobile(int equipmentId, String automobileId) throws Exception;

    // GET ALL equipment ON the automobile
    Set<Equipment> getAllEquipmentOnAutomobile(String automobileId) throws Exception;

    //REMOVE equipment FROM the automobile
    void removeEquipmentFromAutomobile(int equipmentId, String automobileId) throws Exception;


    //Interactions with LOADING TYPES
    // ADD loading type TO the automobile
    void addLoadingTypeToAutomobile(int loadingTypeId, String automobileId) throws Exception;

    // GET ALL loading types OF the automobile
    Set<LoadingType> getAllLoadingTypesOfAutomobile(String automobileId) throws Exception;

    //REMOVE loading type FROM the automobile
    void removeLoadingTypeFromAutomobile(int loadingTypeId, String automobileId) throws Exception;


    //Interaction with MALFUNCTIONS
    //GET ALL malfunctions OF the automobile
    List<Malfunction> getMalfunctionsOfAutomobile(Automobile automobile) throws Exception;

    //Interaction with MAINTENANCE
    //GET ALL maintenance OF automobile
    List<Maintenance> getMaintenanceOfAutomobile(Automobile automobile) throws Exception;

    //Interaction with AUTOMOBILE ATTACHMENTS
    //find attachments of automobile
    List<AutomobileAttachment> findAutomobileAttachments(Automobile automobile) throws Exception;


}
