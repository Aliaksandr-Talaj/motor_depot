package by.talai.data.dao;

import by.talai.model.stock.TechnicalStatus;

import java.util.Set;

public interface TechnicalStatusDao {

    //create technical status
    void createTechnicalStatus(TechnicalStatus technicalStatus);

    //find technical status
    TechnicalStatus findTechnicalStatus(int id);

    //find all
    Set<TechnicalStatus> findAllTechnicalStatuses();

    //update technical status
    void updateTechnicalStatus();

    //delete technical status
    boolean deleteTechnicalStatus();

}
