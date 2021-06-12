package by.talai.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class Delivery implements Serializable {
    private String loadingPlace;
    private Date loadingDate;
    private String destination;
    private Date term;
    private Set<String> additionalRequirements;
    private List<Cargo> cargoList;

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
        return "Delivery{" +
                "loadingPlace='" + loadingPlace + '\'' +
                ",\n loadingDate=" + loadingDate +
                ",\n destination='" + destination + '\'' +
                ",\n term=" + term +
                ",\n additionalRequirements=" + additionalRequirements +
                ",\n cargoList=" + cargoList +
                '}';
    }
}
