package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.AutomobileAttachment;
import by.talai.model.personnel.User;
import by.talai.model.stock.Automobile;

import java.util.List;

public interface AutomobileAttachmentDao {

    //create attachment
    int createAttachment(AutomobileAttachment automobileAttachment) throws Exception;

    //find all attachments
    List<AutomobileAttachment> findAllAutomobileAttachments() throws Exception;

    //update attachment
    void updateAutomobileAttachment(AutomobileAttachment automobileAttachment) throws Exception;

    //delete attachment
    void deleteAutomobileAttachment(int id) throws Exception;

    List<AutomobileAttachment> findAttachmentsOfDriver(User user) throws DaoException;

    List<AutomobileAttachment> findAttachmentsOfAutomobile(Automobile automobile) throws DaoException;
}
