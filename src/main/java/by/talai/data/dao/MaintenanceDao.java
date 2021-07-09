package by.talai.data.dao;


import by.talai.model.stock.Maintenance;

import java.util.List;

/**
 * The interface Maintenance dao.
 */
public interface MaintenanceDao {

    /**
     * Create maintenance.
     *
     * @param maintenance the maintenance
     * @throws Exception the exception
     */
// create Maintenance
    void createMaintenance(Maintenance maintenance) throws Exception;

    /**
     * Gets maintenance.
     *
     * @param id the id
     * @return the maintenance
     * @throws Exception the exception
     */
// get Maintenance by id
    Maintenance getMaintenance(int id) throws Exception;

    /**
     * Gets all maintenance.
     *
     * @return the all maintenance
     * @throws Exception the exception
     */
// get all Maintenance
    List<Maintenance> getAllMaintenance() throws Exception;

    /**
     * Update maintenance.
     *
     * @param maintenance the maintenance
     * @throws Exception the exception
     */
//update Maintenance
    void updateMaintenance(Maintenance maintenance) throws Exception;

    /**
     * Add or update maintenance.
     *
     * @param maintenance the maintenance
     * @throws Exception the exception
     */
    void addOrUpdateMaintenance(Maintenance maintenance) throws Exception;

    // delete Maintenance

    /**
     * Delete maintenance.
     *
     * @param id the id
     * @throws Exception the exception
     */
    void deleteMaintenance(int id) throws Exception;

}
