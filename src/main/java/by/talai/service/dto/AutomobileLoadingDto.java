package by.talai.service.dto;

import by.talai.model.Cargo;
import by.talai.model.stock.Automobile;

import java.util.Map;
import java.util.Objects;

public class AutomobileLoadingDto {

    private Automobile automobile;
    private Map<Cargo, Double> fitQuantityOfCargoMap;
    private double loadingPercent;


    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    public Map<Cargo, Double> getFitQuantityOfCargoMap() {
        return fitQuantityOfCargoMap;
    }

    public void setFitQuantityOfCargoMap(Map<Cargo, Double> fitQuantityOfCargoMap) {
        this.fitQuantityOfCargoMap = fitQuantityOfCargoMap;
    }

    public double getLoadingPercent() {
        return loadingPercent;
    }

    public void setLoadingPercent(double loadingPercent) {
        this.loadingPercent = loadingPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutomobileLoadingDto that = (AutomobileLoadingDto) o;
        return Double.compare(that.loadingPercent, loadingPercent) == 0 && automobile.equals(that.automobile)
                && Objects.equals(fitQuantityOfCargoMap, that.fitQuantityOfCargoMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(automobile, fitQuantityOfCargoMap);
    }

    @Override
    public String toString() {
        return "AutomobileLoadingDto{" +
                "automobile=" + automobile +
                ", fitQuantityOfCargoMap=" + fitQuantityOfCargoMap +
                ", loadingPercent=" + loadingPercent +
                '}';
    }

}
