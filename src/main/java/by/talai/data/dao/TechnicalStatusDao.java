package by.talai.data.dao;

import by.talai.model.stock.TechnicalStatus;

import java.util.Set;

public interface TechnicalStatusDao {

    //create technical status
    void createTechnicalStatus(TechnicalStatus technicalStatus) throws Exception;

    //find technical status
    TechnicalStatus findTechnicalStatus(int id) throws Exception;

    //find all
    Set<TechnicalStatus> findAllTechnicalStatuses() throws Exception;

    //update technical status
    void updateTechnicalStatus(TechnicalStatus technicalStatus) throws Exception;

    //delete technical status
   void deleteTechnicalStatus(int id) throws Exception;

}
