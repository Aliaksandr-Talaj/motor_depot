package by.talai.data.dao;

import by.talai.model.stock.LoadingType;

import java.util.Set;

/**
 * The interface Loading type dao.
 */
public interface LoadingTypeDao {

    /**
     * Create loading type.
     *
     * @param loadingType the loading type
     * @throws Exception the exception
     */
    void createLoadingType(LoadingType loadingType) throws Exception;

    /**
     * Gets loading type.
     *
     * @param loadingTypeId the loading type id
     * @return the loading type
     * @throws Exception the exception
     */
    LoadingType getLoadingType(int loadingTypeId) throws Exception;

    /**
     * Gets all loading types.
     *
     * @return the all loading types
     * @throws Exception the exception
     */
    Set<LoadingType> getAllLoadingTypes() throws Exception;

    /**
     * Update loading type.
     *
     * @param loadingType the loading type
     * @throws Exception the exception
     */
    void updateLoadingType(LoadingType loadingType) throws Exception;

    /**
     * Delete loading type.
     *
     * @param id the id
     * @throws Exception the exception
     */
    void deleteLoadingType(int id) throws Exception;

}
