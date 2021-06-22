package by.talai.service;

import by.talai.data.exception.DaoException;
import by.talai.model.Charterer;

import java.util.List;

public interface ChartererService {

    int addCharterer(String name, String surname, String country, String region, String locality, String street,
                     String building, String apartment) throws DaoException;

    Charterer getCharterer(int id) throws Exception;

    List<Charterer> getCharterers() throws Exception;
}
