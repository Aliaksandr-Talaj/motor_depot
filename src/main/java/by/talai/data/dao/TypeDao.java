package by.talai.data.dao;

import by.talai.model.AutomobileType;

import java.util.Set;

public interface TypeDao {

    //create type
    void createType(AutomobileType AutomobileType);

    //find type
    AutomobileType findType(int id);

    //find all types
    Set<AutomobileType> findAllTypes();

    //update type
    void updateType();

    //delete type
    boolean deleteType();

}
