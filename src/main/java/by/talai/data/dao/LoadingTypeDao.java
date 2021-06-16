package by.talai.data.dao;

import by.talai.model.LoadingType;

import java.util.Set;

public interface LoadingTypeDao {

    void createLoadingType(LoadingType loadingType) throws Exception;

    LoadingType getLoadingType(int loadingTypeId) throws Exception;

    Set<LoadingType> getAllLoadingTypes() throws Exception;

    void updateLoadingType(LoadingType loadingType) throws Exception;

    void deleteLoadingType(int id) throws Exception;

}
