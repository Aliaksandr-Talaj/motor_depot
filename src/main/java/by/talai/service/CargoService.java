package by.talai.service;

import by.talai.model.Cargo;

import java.util.List;

public interface CargoService {
    List<Cargo> getCargos() throws Exception;

    Cargo getCargo(int id) throws Exception;
}
