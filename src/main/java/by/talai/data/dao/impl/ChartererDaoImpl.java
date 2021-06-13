package by.talai.data.dao.impl;

import by.talai.data.dao.ChartererDao;
import by.talai.model.Address;
import by.talai.model.Charterer;

import java.util.List;
import java.util.Set;

public class ChartererDaoImpl implements ChartererDao {
    @Override
    public void createCharterer(Charterer charterer) {

    }

    @Override
    public Charterer getCharterer(int id) {
        return null;
    }

    @Override
    public List<Charterer> getAllCharterers() {
        return null;
    }

    @Override
    public void updateCharterer(Charterer charterer) {

    }

    @Override
    public void deleteCharterer(int id) {

    }

    @Override
    public void addAddressToCharterer(int addressId, int chartererId) {

    }

    @Override
    public Set<Address> findUsedAddresses(int chartererId) {
        return null;
    }

    @Override
    public void deleteUsageOfAddress(int addressId, int chartererId) {

    }
}
