package by.talai.data.dao;

import by.talai.model.Status;

import java.util.Set;

public interface StatusDao {

    //create status
    void createStatus(Status status) throws Exception;

    //find status
    Status findStatus(int id) throws Exception;

    //find all
    Set<Status> findAllStatuses() throws Exception;

    //update status
    void updateStatus(Status status) throws Exception;

    //delete status
   void deleteStatus(int id) throws Exception;

}
