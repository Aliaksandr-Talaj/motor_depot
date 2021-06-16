package by.talai.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Delivery implements Serializable {

    private int id;
    private Address loadingPlace;
    private Date loadingDate;
    private Address destination;
    private Date term;
    private Request request;
    private Status status;

    private List<Cargo> cargoList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getLoadingPlace() {
        return loadingPlace;
    }

    public void setLoadingPlace(Address loadingPlace) {
        this.loadingPlace = loadingPlace;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public Address getDestination() {
        return destination;
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ",\n loadingPlace=" + loadingPlace +
                ",\n loadingDate=" + loadingDate +
                ",\n destination=" + destination +
                ",\n term=" + term +
                ",\n request=" + request +
                ",\n status=" + status +
                ",\n cargoList=" + cargoList +
                '}';
    }
}
