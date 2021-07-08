package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.AutomobileAttachment;
import by.talai.model.personnel.User;
import by.talai.model.stock.Automobile;

import java.util.List;

/**
 * The interface Automobile attachment dao.
 */
public interface AutomobileAttachmentDao {

    /**
     * Create attachment int.
     *
     * @param automobileAttachment the automobile attachment
     * @return the id of new attachment
     * @throws Exception the exception
     */
//create attachment
    int createAttachment(AutomobileAttachment automobileAttachment) throws Exception;

    /**
     * Find all automobile attachments list.
     *
     * @return the list of all attachments
     * @throws Exception the exception
     */
//find all attachments
    List<AutomobileAttachment> findAllAutomobileAttachments() throws Exception;

    /**
     * Update automobile attachment.
     *
     * @param automobileAttachment the automobile attachment
     * @throws Exception the exception
     */
//update attachment
    void updateAutomobileAttachment(AutomobileAttachment automobileAttachment) throws Exception;

    /**
     * Delete automobile attachment.
     *
     * @param id the id
     * @throws Exception the exception
     */
//delete attachment
    void deleteAutomobileAttachment(int id) throws Exception;

    /**
     * Find attachments of driver list.
     *
     * @param user the user
     * @return the list of all attachments of the driver
     * @throws DaoException the dao exception
     */
    List<AutomobileAttachment> findAttachmentsOfDriver(User user) throws DaoException;

    /**
     * Find attachments of automobile list.
     *
     * @param automobile the automobile
     * @return the list of all attachments of the automobile
     * @throws DaoException the dao exception
     */
    List<AutomobileAttachment> findAttachmentsOfAutomobile(Automobile automobile) throws DaoException;
}
