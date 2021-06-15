package by.talai.data.dao;


import by.talai.model.stock.Maintenance;

import java.util.List;

public interface MaintenanceDao {

    // create Maintenance
    void createMaintenance(Maintenance maintenance);

    // get Maintenance by id
    Maintenance getMaintenance(int id);

    // get all Maintenance
    List<Maintenance> getAllMaintenance();

    //update Maintenance
    void updateMaintenance(Maintenance maintenance);

    // delete Maintenance
    void deleteMaintenance(int id);



}
