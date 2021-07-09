package by.talai.service.impl;

import by.talai.data.dao.AutomobileAttachmentDao;
import by.talai.data.dao.impl.AutomobileAttachmentDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.AutomobileAttachment;
import by.talai.model.personnel.Driver;
import by.talai.model.stock.Automobile;
import by.talai.service.AutomobileAttachmentService;
import by.talai.service.AutomobileService;
import by.talai.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

public class AutomobileAttachmentServiceImpl implements AutomobileAttachmentService {

    private final AutomobileAttachmentDao automobileAttachmentDao = new AutomobileAttachmentDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(AutomobileAttachmentServiceImpl.class);

    public AutomobileAttachmentServiceImpl() throws ConnectionPoolException {
    }

    @Override
    public void closeAttachment(int attachmentId) throws Exception {
        List<AutomobileAttachment> automobileAttachments = automobileAttachmentDao.findAllAutomobileAttachments();
        AutomobileAttachment attachment = null;
        for (AutomobileAttachment automobileAttachment : automobileAttachments) {
            if (automobileAttachment.getId() == attachmentId) {
                attachment = automobileAttachment;
            }
        }
        Date now = new Date(new java.util.Date().getTime());
        if (attachment != null) {
            attachment.setDateOfDetachment(now);
        }
        automobileAttachmentDao.updateAutomobileAttachment(attachment);
    }

    @Override
    public void createAttachment(String automobileId, int driverId) throws Exception {
        AutomobileAttachment attachment = new AutomobileAttachment();
        attachment.setAutomobile(new AutomobileServiceImpl().findAutomobileById(automobileId));
        List<Driver> drivers = new UserServiceImpl().getAllDrivers();
        Driver attachedDriver = null;
        for (Driver driver : drivers) {
            if (driver.getId() == driverId) {
                attachedDriver = driver;
            }
        }
        attachment.setDriver(attachedDriver);
        attachment.setDateOfAttachment(new Date(new java.util.Date().getTime()));
        automobileAttachmentDao.createAttachment(attachment);
    }

    @Override
    public List<AutomobileAttachment> getAutomobileAttachmentsOfDriver(int driverId) throws Exception {
        UserService userService = new UserServiceImpl();
        Driver driver = userService.getDriver(driverId);
        return automobileAttachmentDao.findAttachmentsOfDriver(driver);
    }

}
