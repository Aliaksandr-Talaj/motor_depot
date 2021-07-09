package by.talai.service;

public interface AutomobileAttachmentService {
    void closeAttachment(int attachmentId) throws Exception;

    void createAttachment(String automobileId, int driverId) throws Exception;
}
