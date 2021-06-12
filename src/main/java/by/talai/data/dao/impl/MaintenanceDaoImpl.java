package by.talai.data.dao.impl;

import by.talai.data.dao.MaintenanceDao;
import by.talai.model.stock.Maintenance;

import java.util.List;

public class MaintenanceDaoImpl implements MaintenanceDao {

    @Override
    public String createMaintenance(Maintenance maintenance) {
        return null;
    }

    @Override
    public Maintenance getMaintenance(String id) {
        return null;
    }

    @Override
    public List<Maintenance> getAllMaintenance() {
        return null;
    }

    @Override
    public void updateMaintenance(Maintenance maintenance) {

    }

    @Override
    public boolean deleteMaintenance(String id) {
        return false;
    }

    @Override
    public List<Maintenance> getMaintenanceOfAutomobile(String id) {
        return null;
    }
}
