package by.talai.service;

import by.talai.model.Cargo;

import java.util.List;

/**
 * The interface Cargo service.
 */
public interface CargoService {
    /**
     * Gets cargos.
     *
     * @return the cargos
     * @throws Exception the exception
     */
    List<Cargo> getCargos() throws Exception;

    /**
     * Gets cargo.
     *
     * @param id the id
     * @return the cargo
     * @throws Exception the exception
     */
    Cargo getCargo(int id) throws Exception;

    /**
     * Save cargo.
     *
     * @param cargo the cargo
     * @throws Exception the exception
     */
    void saveCargo(Cargo cargo) throws Exception;
}
