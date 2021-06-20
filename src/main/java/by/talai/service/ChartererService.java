package by.talai.service;

import by.talai.data.exception.DaoException;

public interface ChartererService {
    void addCharterer(String name, String surname, String country, String region, String locality, String street,
                      String building, String apartment) throws DaoException;
}
