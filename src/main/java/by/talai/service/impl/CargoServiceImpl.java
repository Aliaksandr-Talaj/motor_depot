package by.talai.service.impl;

import by.talai.data.dao.CargoDao;
import by.talai.data.dao.impl.CargoDaoImpl;
import by.talai.model.Cargo;
import by.talai.service.CargoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CargoServiceImpl implements CargoService {

    private final CargoDao cargoDao = new CargoDaoImpl();

    public static final Logger logger = LoggerFactory.getLogger(ChartererServiceImpl.class);

    public CargoServiceImpl() throws Exception {
    }

    @Override
    public List<Cargo> getCargos() throws Exception {
        return cargoDao.getAllCargos();
    }

    @Override
    public Cargo getCargo(int id) throws Exception {
        return cargoDao.getCargo(id);
    }

}
