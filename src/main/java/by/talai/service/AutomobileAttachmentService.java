package by.talai.service;

import by.talai.model.AutomobileAttachment;

import java.util.List;

/**
 * The interface Automobile attachment service.
 */
public interface AutomobileAttachmentService {
    /**
     * Close attachment.
     *
     * @param attachmentId the attachment id
     * @throws Exception the exception
     */
    void closeAttachment(int attachmentId) throws Exception;

    /**
     * Create attachment.
     *
     * @param automobileId the automobile id
     * @param driverId     the driver id
     * @throws Exception the exception
     */
    void createAttachment(String automobileId, int driverId) throws Exception;

    /**
     * Gets automobile attachments of driver.
     *
     * @param driverId the driver id
     * @return the automobile attachments of driver
     * @throws Exception the exception
     */
    List<AutomobileAttachment> getAutomobileAttachmentsOfDriver(int driverId) throws Exception;
}
