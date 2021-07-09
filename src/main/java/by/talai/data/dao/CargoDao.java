package by.talai.data.dao;

import by.talai.model.Cargo;
import by.talai.model.Delivery;

import java.util.List;

/**
 * The interface Cargo dao.
 */
public interface CargoDao {

    /**
     * Create cargo.
     *
     * @param cargo the cargo
     * @throws Exception the exception
     */
// create cargo
    void createCargo(Cargo cargo) throws Exception;

    /**
     * Gets cargo.
     *
     * @param id the id
     * @return the cargo
     * @throws Exception the exception
     */
// get cargo by id
    Cargo getCargo(int id) throws Exception;

    /**
     * Gets all cargos.
     *
     * @return the all cargos
     * @throws Exception the exception
     */
// get all cargo
    List<Cargo> getAllCargos() throws Exception;

    /**
     * Update cargo.
     *
     * @param cargo the cargo
     * @throws Exception the exception
     */
//update cargo
    void updateCargo(Cargo cargo) throws Exception;

    /**
     * Delete cargo.
     *
     * @param id the id
     * @throws Exception the exception
     */
// delete cargo
    void deleteCargo(int id) throws Exception;

    /**
     * Gets all cargos of ride.
     *
     * @param rideId the ride id
     * @return the all cargos of ride
     * @throws Exception the exception
     */
    List<Cargo> getAllCargosOfRide(int rideId) throws Exception;

    /**
     * Gets all cargos of delivery.
     *
     * @param delivery the delivery
     * @return the all cargos of delivery
     * @throws Exception the exception
     */
    List<Cargo> getAllCargosOfDelivery(Delivery delivery) throws Exception;

    /**
     * Add or update cargo.
     *
     * @param cargo the cargo
     * @throws Exception the exception
     */
    void addOrUpdateCargo(Cargo cargo) throws Exception;
}
