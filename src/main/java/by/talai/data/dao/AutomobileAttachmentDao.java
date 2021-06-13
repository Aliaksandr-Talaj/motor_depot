package by.talai.data.dao;

import by.talai.model.AutomobileAttachment;

import java.util.List;

public interface AutomobileAttachmentDao {

    //create attachment
    void createAttachment(AutomobileAttachment automobileAttachment);

    //find all attachments
    List<AutomobileAttachment> findAllAutomobileAttachments();

    //find attachments of automobile
    List<AutomobileAttachment> findAutomobileAttachments(String automobileId);

    //update attachment
    void updateAutomobileAttachment(AutomobileAttachment automobileAttachment);

    //delete attachment
    void deleteAutomobileAttachment(AutomobileAttachment automobileAttachment);


}
