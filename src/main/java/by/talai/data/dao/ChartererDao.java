package by.talai.data.dao;

import by.talai.model.Address;
import by.talai.model.Charterer;

import java.util.List;
import java.util.Set;

public interface ChartererDao {

    // create charterer
    void createCharterer(Charterer charterer);

    // get charterer by id
    Charterer getCharterer(int id);

    // get all charterer
    List<Charterer> getAllCharterers();

    //update charterer
    void updateCharterer(Charterer charterer);

    // delete charterer
    void deleteCharterer(int id);


    // add address
    void addAddressToCharterer(int addressId, int chartererId);

    // find used addresses
    Set<Address> findUsedAddresses(int chartererId);

    // delete the usage of the address
    void deleteUsageOfAddress(int addressId, int chartererId);

}
