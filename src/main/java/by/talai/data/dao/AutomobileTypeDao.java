package by.talai.data.dao;

import by.talai.model.stock.AutomobileType;

import java.util.Set;

/**
 * The interface Automobile type dao.
 */
public interface AutomobileTypeDao {

    /**
     * Create automobile type.
     *
     * @param automobileType the automobile type
     * @throws Exception the exception
     */
//create type
    void createAutomobileType(AutomobileType automobileType) throws Exception;

    /**
     * Find automobile type.
     *
     * @param id the id
     * @return the automobile type
     * @throws Exception the exception
     */
//find type
    AutomobileType findAutomobileType(int id) throws Exception;

    /**
     * Find all automobile types set.
     *
     * @return the set
     * @throws Exception the exception
     */
//find all types
    Set<AutomobileType> findAllAutomobileTypes() throws Exception;

    /**
     * Update automobile type.
     *
     * @param automobileType the automobile type
     * @throws Exception the exception
     */
//update type
    void updateAutomobileType(AutomobileType automobileType) throws Exception;

    /**
     * Delete automobile type.
     *
     * @param id the id
     * @throws Exception the exception
     */
//delete type
    void deleteAutomobileType(int id) throws Exception;

}
