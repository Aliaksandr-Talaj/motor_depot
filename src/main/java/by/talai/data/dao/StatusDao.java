package by.talai.data.dao;

import by.talai.model.Status;

import java.util.Set;

/**
 * The interface Status dao.
 */
public interface StatusDao {

    /**
     * Create status.
     *
     * @param status the status
     * @throws Exception the exception
     */
//create status
    void createStatus(Status status) throws Exception;

    /**
     * Find status status.
     *
     * @param id the id
     * @return the status
     * @throws Exception the exception
     */
//find status
    Status findStatus(int id) throws Exception;

    /**
     * Find all statuses set.
     *
     * @return the set
     * @throws Exception the exception
     */
//find all
    Set<Status> findAllStatuses() throws Exception;

    /**
     * Update status.
     *
     * @param status the status
     * @throws Exception the exception
     */
//update status
    void updateStatus(Status status) throws Exception;

    /**
     * Delete status.
     *
     * @param id the id
     * @throws Exception the exception
     */
//delete status
   void deleteStatus(int id) throws Exception;

}
