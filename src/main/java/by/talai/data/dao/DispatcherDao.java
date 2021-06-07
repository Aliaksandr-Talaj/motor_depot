package by.talai.data.dao;


import by.talai.model.personnel.Dispatcher;

import java.util.List;

public interface DispatcherDao {
    // create dispatcher
    String createDispatcher(Dispatcher dispatcher);

    // get dispatcher by id
    Dispatcher getDispatcher(String id);

    // get all cargo
    List<Dispatcher> getAllDispatchers();

    //update dispatcher
    void updateDispatcher(Dispatcher dispatcher);

    // delete dispatcher
    boolean deleteDispatcher(String id);
}
