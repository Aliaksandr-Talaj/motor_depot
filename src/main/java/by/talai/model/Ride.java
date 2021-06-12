package by.talai.model;

import by.talai.model.personnel.Dispatcher;
import by.talai.model.stock.Automobile;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class Ride implements Serializable {
    private int id;
    private Date date;
    private Request request;
    private Dispatcher dispatcher;
    private Automobile automobile;

    private String loadingPlace;
    private Date loadingDate;
    private String destination;
    private Date term;
    private Set<String> additionalRequirements;
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

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public String getLoadingPlace() {
        return loadingPlace;
    }

    public void setLoadingPlace(String loadingPlace) {
        this.loadingPlace = loadingPlace;
    }

    public Date getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public Set<String> getAdditionalRequirements() {
        return additionalRequirements;
    }

    public void setAdditionalRequirements(Set<String> additionalRequirements) {
        this.additionalRequirements = additionalRequirements;
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
                ",\n additionalRequirements=" + additionalRequirements +
                ",\n cargoList=" + cargoList +
                '}';
    }
}
