package by.talai.data.dao.interfaces;


import by.talai.model.stock.Maintenance;

import java.util.List;

public interface MaintenanceDao {

    // create Maintenance
    String createMaintenance(Maintenance maintenance);

    // get Maintenance by id
    Maintenance getMaintenance(String id);

    // get all Maintenance
    List<Maintenance> getAllMaintenance();

    //update Maintenance
    void updateMaintenance(Maintenance maintenance);

    // delete Maintenance
    boolean deleteMaintenance(String id);

}
