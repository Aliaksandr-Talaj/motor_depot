package by.talai.data.dao;

import by.talai.model.Charterer;

import java.util.List;

public interface ChartererDao {

    // create charterer
    int createCharterer(Charterer charterer);

    // get charterer by id
    Charterer getCharterer(int id);

    // get all charterer
    List<Charterer> getAllCharterers();

    //update charterer
    void updateCharterer(Charterer charterer);

    // delete charterer
    boolean deleteCharterer(int id);

}
