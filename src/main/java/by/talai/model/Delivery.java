package by.talai.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Delivery implements Serializable {

    private int id;
    private Address loadingPlace;
    private Date loadingDate;
    private Address destination;
    private Date term;
    private Request request;
    private Status executionStatus;

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

    public Status getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(Status executionStatus) {
        this.executionStatus = executionStatus;
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
                ",\n status=" + executionStatus +
                ",\n cargoList=" + cargoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery)) return false;
        Delivery delivery = (Delivery) o;
        return getId() == delivery.getId() && getLoadingPlace().equals(delivery.getLoadingPlace())
                && Objects.equals(getLoadingDate(), delivery.getLoadingDate())
                && getDestination().equals(delivery.getDestination())
                && Objects.equals(getTerm(), delivery.getTerm()) && getRequest().equals(delivery.getRequest())
                && getExecutionStatus().equals(delivery.getExecutionStatus())
                && getCargoList().equals(delivery.getCargoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLoadingPlace(), getLoadingDate(), getDestination(), getTerm(),
                getRequest(), getExecutionStatus(), getCargoList());
    }
}
