package by.talai.model;

import by.talai.model.personnel.User;
import by.talai.model.stock.Automobile;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Ride implements Serializable {

    private int id;
    private Date date;
    private Request request;
    private User dispatcher;
    private Automobile automobile;

    private Address loadingPlace;
    private Date loadingDate;
    private Address destination;
    private Date term;
    private Status executionStatus;

    private List<Cargo> cargoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public User getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(User dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public Address getLoadingPlace() {
        return loadingPlace;
    }

    public void setLoadingPlace(Address loadingPlace) {
        this.loadingPlace = loadingPlace;
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

    public Status getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(Status executionStatus) {
        this.executionStatus = executionStatus;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ",\n date=" + date +
                ",\n request=" + request +
                ",\n dispatcher=" + dispatcher +
                ",\n automobile=" + automobile +
                ",\n loadingPlace='" + loadingPlace + '\'' +
                ",\n loadingDate=" + loadingDate +
                ",\n destination='" + destination + '\'' +
                ",\n term=" + term +
                ",\n executionStatus=" + executionStatus +
                ",\n cargoList=" + cargoList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ride)) return false;
        Ride ride = (Ride) o;
        return getId() == ride.getId() && getDate().equals(ride.getDate()) && getRequest().equals(ride.getRequest())
                && getDispatcher().equals(ride.getDispatcher()) && Objects.equals(getAutomobile(), ride.getAutomobile())
                && getLoadingPlace().equals(ride.getLoadingPlace())
                && Objects.equals(getLoadingDate(), ride.getLoadingDate())
                && getDestination().equals(ride.getDestination()) && Objects.equals(getTerm(), ride.getTerm())
                && getExecutionStatus().equals(ride.getExecutionStatus()) && getCargoList().equals(ride.getCargoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getRequest(), getDispatcher(), getAutomobile(), getLoadingPlace(),
                getLoadingDate(), getDestination(), getTerm(), getExecutionStatus(), getCargoList());
    }
}
