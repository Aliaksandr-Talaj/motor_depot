package by.talai.data.dao;

import by.talai.model.LoadingType;

import java.util.Set;

public interface LoadingTypeDao {

    // add loading type to the automobile
    void addLoadingTypeToAutomobile(int loadingTypeId, String automobileId);

    // get  all loading types of the automobile
    Set<LoadingType> getAllLoadingTypesOfAutomobile(String automobileId);

    //remove loading type from the automobile
    void removeLoadingTypeFromAutomobile(int loadingTypeId, String automobileId);

}
