package by.talai.data.dao;


import by.talai.model.stock.Malfunction;

import java.util.List;

/**
 * The interface Malfunction dao.
 */
public interface MalfunctionDao {
    /**
     * Create malfunction.
     *
     * @param malfunction the malfunction
     * @throws Exception the exception
     */
// create Malfunction
    void createMalfunction(Malfunction malfunction) throws Exception;

    /**
     * Gets malfunction.
     *
     * @param id the id
     * @return the malfunction
     * @throws Exception the exception
     */
// get Malfunction by id
    Malfunction getMalfunction(int id) throws Exception;

    /**
     * Gets all malfunctions.
     *
     * @return the all malfunctions
     * @throws Exception the exception
     */
// get all Malfunctions
    List<Malfunction> getAllMalfunctions() throws Exception;

    /**
     * Update malfunction.
     *
     * @param malfunction the malfunction
     * @throws Exception the exception
     */
//update Malfunction
    void updateMalfunction(Malfunction malfunction) throws Exception;

    /**
     * Add or update malfunction.
     *
     * @param malfunction the malfunction
     * @throws Exception the exception
     */
    void addOrUpdateMalfunction(Malfunction malfunction) throws Exception;

    /**
     * Delete malfunction.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete Malfunction
    void deleteMalfunction(int id) throws Exception;


}
