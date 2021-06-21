package by.talai.data.dao;

import by.talai.model.stock.AutomobileType;

import java.util.Set;

public interface AutomobileTypeDao {

    //create type
    void createAutomobileType(AutomobileType automobileType) throws Exception;

    //find type
    AutomobileType findAutomobileType(int id) throws Exception;

    //find all types
    Set<AutomobileType> findAllAutomobileTypes() throws Exception;

    //update type
    void updateAutomobileType(AutomobileType automobileType) throws Exception;

    //delete type
    void deleteAutomobileType(int id) throws Exception;

}
