package by.talai.data.dao;

import by.talai.data.exception.DaoException;
import by.talai.model.Address;
import by.talai.model.Charterer;

import java.util.List;
import java.util.Set;

public interface ChartererDao {

    // create charterer
    void createCharterer(Charterer charterer) throws Exception;

    // get charterer by id
    Charterer getCharterer(int id) throws Exception;

    // get all charterer
    List<Charterer> getAllCharterers() throws Exception;

    //update charterer
    void updateCharterer(Charterer charterer) throws Exception;

    // delete charterer
    void deleteCharterer(int id) throws Exception;


    // add address
    void addAddressToCharterer(int addressId, int chartererId) throws Exception;

    // find used addresses
    Set<Address> findUsedAddresses(int chartererId) throws Exception;

    // delete the usage of the address
    void deleteUsageOfAddress(int addressId, int chartererId) throws Exception;

    int createCharterer(String name, String surname, Address address) throws DaoException;

    int createCharterer(String name, String surname, String country, String region,
                        String locality,String street, String building, String apartment) throws DaoException;
}
