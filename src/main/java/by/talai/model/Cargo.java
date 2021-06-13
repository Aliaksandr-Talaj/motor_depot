package by.talai.model;

import java.io.Serializable;

public class Cargo implements Serializable {

    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ",\n name='" + name + '\'' +
                ",\n unit=" + unit +
                ",\n quantity=" + quantity +
                '}';
    }
}
