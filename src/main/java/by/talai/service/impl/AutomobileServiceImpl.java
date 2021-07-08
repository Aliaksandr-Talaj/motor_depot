package by.talai.service.impl;

import by.talai.data.dao.*;
import by.talai.data.dao.impl.*;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.model.Request;
import by.talai.model.stock.*;
import by.talai.service.AutomobileService;
import by.talai.service.dto.AutomobilesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileDao automobileDao = new AutomobileDaoImpl();

    private final MalfunctionDao malfunctionDao = new MalfunctionDaoImpl();
    private final MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
    private final StatusDao technicalStatusDao = new TechnicalStatusDaoImpl();


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

    @Override
    public void addNewAutomobile(String id, String brand, String model, String fuel, String carrying,
                                 String type, String equipment1, String equipment2, String equipment3,
                                 String top, String rear, String side,
                                 String platformLength, String platformWidth, String heightLimit, String volumeLimit)
            throws Exception {
        Automobile automobile = new Automobile();

        automobile.setId(id);
        automobile.setBrand(brand);
        automobile.setModel(model);
        FuelTypeDao fuelTypeDao = new FuelTypeDaoImpl();
        int fuelTypeId = Integer.parseInt(fuel);
        automobile.setFuelType(fuelTypeDao.findFuelType(fuelTypeId));
        int carr = Integer.parseInt(carrying);
        automobile.setCarrying(carr);
        AutomobileTypeDao automobileTypeDao = new AutomobileTypeDaoImpl();
        int typeId = Integer.parseInt(type);
        automobile.setAutomobileType(automobileTypeDao.findAutomobileType(typeId));
        Set<Equipment> equipmentSet = new HashSet<>();
        Set<Integer> idSet = new HashSet<>(3);

        try {
            idSet.add(Integer.parseInt(equipment1));
        } catch (NumberFormatException e) {
            idSet.add(0);
        }
        try {
            idSet.add(Integer.parseInt(equipment2));
        } catch (NumberFormatException e) {
            idSet.add(0);
        }
        try {
            idSet.add(Integer.parseInt(equipment3));
        } catch (NumberFormatException e) {
            idSet.add(0);
        }

        EquipmentDao equipmentDao = new EquipmentDaoImpl();

        for (Integer equipId : idSet) {
            if (equipId != 0) {
                equipmentSet.add(equipmentDao.getEquipment(equipId));
            }
        }

        automobile.setEquipmentSet(equipmentSet);
        idSet.clear();
        int topMarker = Integer.parseInt(top.trim());
        int rearMarker = Integer.parseInt(rear.trim());
        int sideMarker = Integer.parseInt(side.trim());
        LoadingTypeDao loadingTypeDao = new LoadingTypeDaoImpl();
        Set<LoadingType> loadingTypes = new HashSet<>(3);
        if (topMarker == 1) {
            loadingTypes.add(loadingTypeDao.getLoadingType(1));
        }
        if (rearMarker == 1) {
            loadingTypes.add(loadingTypeDao.getLoadingType(2));
        }
        if (sideMarker == 1) {
            loadingTypes.add(loadingTypeDao.getLoadingType(3));
        }
        automobile.setLoadingTypes(loadingTypes);

        automobile.setPlatformLength(Integer.parseInt(platformLength));
        automobile.setPlatformWidth(Integer.parseInt(platformWidth));
        automobile.setCargoHeightLimit(Double.parseDouble(heightLimit));
        if (volumeLimit != null && !"".equals(volumeLimit)) {
            automobile.setCargoVolumeLimit(Double.parseDouble(volumeLimit));
        }
        automobile.setTechnicalStatus(technicalStatusDao.findStatus(1));

        saveNewAutomobile(automobile);

    }

    @Override
    public Set<Automobile> findSuitableAutomobiles(Request request) throws Exception {
        List<Automobile> automobiles = automobileDao.getAllReadyAutomobiles();
        if (automobiles.isEmpty()) {
            return new HashSet<>();
        }
        Set<Automobile> automobileSet = new HashSet<>();


        for (Automobile automobile : automobiles) {

            if (automobileHasRequiredType(automobile, request)
                    && automobileHasRequiredLoadingType(automobile, request)
                    && automobileHasRequiredEquipment(automobile, request)) {
                automobileSet.add(automobile);
            }
        }

        return automobileSet;
    }


    private boolean automobileHasRequiredType(Automobile automobile, Request request) {
        AutomobileType requiredType = request.getRequiredAutomobileType();

        if (requiredType != null) {
            return requiredType.getType() == null || (requiredType.equals(automobile.getAutomobileType()));
        }

        return false;
    }

    private boolean automobileHasRequiredLoadingType(Automobile automobile, Request request) {
        LoadingType requiredLoadingType = request.getRequiredLoadingType();
        if (requiredLoadingType.getType() == null) {
            return true;
        }
        Set<LoadingType> loadingTypes = automobile.getLoadingTypes();
        if (loadingTypes != null && !loadingTypes.isEmpty()) {

            for (LoadingType loadingType : loadingTypes) {
                if (requiredLoadingType.equals(loadingType)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean automobileHasRequiredEquipment(Automobile automobile, Request request) {

        Set<Equipment> requestEquipmentSet = request.getEquipmentSet();
        Set<Equipment> automobileEquipmentSet = automobile.getEquipmentSet();
        if (requestEquipmentSet == null) {
            return true;
        }
        if (automobileEquipmentSet == null
                || requestEquipmentSet.size() > automobileEquipmentSet.size()) {
            return false;
        }
        if (Objects.equals(automobileEquipmentSet, requestEquipmentSet)) {
            return true;
        }

        int counter = 0;
        for (Equipment requiredEquipment : requestEquipmentSet) {
            for (Equipment automobileEquipment : automobileEquipmentSet) {
                if (automobileEquipment.equals(requiredEquipment)) {
                    counter++;
                }
            }
        }
        return counter == requestEquipmentSet.size();
    }


}
