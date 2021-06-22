package by.talai.service.impl;

import by.talai.data.dao.ChartererDao;
import by.talai.data.dao.impl.ChartererDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.Charterer;
import by.talai.service.ChartererService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ChartererServiceImpl implements ChartererService {

    private final ChartererDao chartererDao = new ChartererDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(ChartererServiceImpl.class);

    public ChartererServiceImpl() throws ConnectionPoolException {
    }


    @Override
    public void addCharterer(String name, String surname, String country, String region,
                             String locality, String street, String building, String apartment) throws DaoException {
        chartererDao.createCharterer(name, surname, country, region,
                locality, street, building, apartment);
    }

    @Override
    public Charterer getCharterer(int id) throws Exception {
        return chartererDao.getCharterer(id);
    }

    @Override
    public List<Charterer> getCharterers() throws Exception {
        return chartererDao.getAllCharterers();
    }
}
