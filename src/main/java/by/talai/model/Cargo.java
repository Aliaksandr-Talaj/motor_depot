package by.talai.model;

import java.io.Serializable;

public class Cargo implements Serializable {

    private String name;

    private Unit unit;
    private double quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "name='" + name + '\'' +
                ",\n unit=" + unit +
                ",\n quantity=" + quantity +
                '}';
    }
}
