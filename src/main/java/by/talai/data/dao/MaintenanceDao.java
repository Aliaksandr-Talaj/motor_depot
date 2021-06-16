package by.talai.data.dao;


import by.talai.model.stock.Maintenance;

import java.util.List;

public interface MaintenanceDao {

    // create Maintenance
    void createMaintenance(Maintenance maintenance) throws Exception;

    // get Maintenance by id
    Maintenance getMaintenance(int id) throws Exception;

    // get all Maintenance
    List<Maintenance> getAllMaintenance() throws Exception;

    //update Maintenance
    void updateMaintenance(Maintenance maintenance) throws Exception;

    void addOrUpdateMaintenance(Maintenance maintenance) throws Exception;

    // delete Maintenance

    void deleteMaintenance(int id) throws Exception;

}
