package by.talai.data.dao;

import by.talai.model.AutomobileAttachment;

import java.util.List;

public interface AutomobileAttachmentDao {

    //create attachment
    void createAttachment(AutomobileAttachment automobileAttachment) throws Exception;

    //find all attachments
    List<AutomobileAttachment> findAllAutomobileAttachments() throws Exception;

    //update attachment
    void updateAutomobileAttachment(AutomobileAttachment automobileAttachment) throws Exception;

    //delete attachment
    void deleteAutomobileAttachment(int id) throws Exception;
}
