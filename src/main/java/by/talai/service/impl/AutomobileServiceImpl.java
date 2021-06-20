package by.talai.service.impl;

import by.talai.data.dao.*;
import by.talai.data.dao.impl.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.stock.Automobile;
import by.talai.model.stock.Maintenance;
import by.talai.model.stock.Malfunction;
import by.talai.service.AutomobileService;
import by.talai.service.dto.AutomobilesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileDao automobileDao = new AutomobileDaoImpl();

    private final MalfunctionDao malfunctionDao = new MalfunctionDaoImpl();
    private final MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
    private final StatusDao technicalStatusDao = new TechnicalStatusDaoImpl();
    private final FuelTypeDao fuelTypeDao = new FuelTypeDaoImpl();
    private final AutomobileTypeDao automobileTypeDao = new AutomobileTypeDaoImpl();


    public static final Logger logger = LoggerFactory.getLogger(AutomobileServiceImpl.class);

    public AutomobileServiceImpl() throws ConnectionPoolException {
    }


    public void saveNewAutomobile(Automobile automobile) throws Exception {

        automobileDao.createAutomobile(automobile);

        List<Malfunction> malfunctionList = automobile.getMalfunctions();
        if (malfunctionList != null && !malfunctionList.isEmpty()) {
            for (Malfunction malfunction : malfunctionList) {
                malfunctionDao.createMalfunction(malfunction);
            }
        }

        List<Maintenance> maintenanceList = automobile.getMaintenanceList();
        if (maintenanceList != null && !maintenanceList.isEmpty()) {
            for (Maintenance maintenance : maintenanceList) {
                maintenanceDao.createMaintenance(maintenance);
            }
        }

    }

    public Automobile findAutomobileById(String id) throws Exception {
        return automobileDao.getAutomobile(id);
    }

    public List<Automobile> findAllAutomobiles() throws Exception {
        return automobileDao.getAllAutomobiles();
    }

    public void updateAutomobile(Automobile automobile) throws Exception {
        automobileDao.updateAutomobile(automobile);

        List<Malfunction> malfunctionList = automobile.getMalfunctions();
        if (malfunctionList != null && !malfunctionList.isEmpty()) {
            for (Malfunction malfunction : malfunctionList) {
                malfunctionDao.addOrUpdateMalfunction(malfunction);
            }
        }

        List<Maintenance> maintenanceList = automobile.getMaintenanceList();
        if (maintenanceList != null && !maintenanceList.isEmpty()) {
            for (Maintenance maintenance : maintenanceList) {
                maintenanceDao.addOrUpdateMaintenance(maintenance);
            }
        }

    }

    @Override
    public AutomobilesDto getAllAutomobilesDto() throws Exception {
        AutomobilesDto automobilesDto = new AutomobilesDto();
        automobilesDto.setAutomobiles(findAllAutomobiles());


        return automobilesDto;
    }


}
