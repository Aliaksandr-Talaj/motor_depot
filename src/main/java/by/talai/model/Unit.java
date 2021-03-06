package by.talai.model;

import java.io.Serializable;
import java.util.Objects;

public class Unit implements Serializable {

    private int id;
    private String type;
    private int length;
    private int width;
    private double height;
    private double weight;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ",\n type='" + type + '\'' +
                ",\n length=" + length +
                ",\n width=" + width +
                ",\n height=" + height +
                ",\n weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return getId() == unit.getId() && getLength() == unit.getLength() && getWidth() == unit.getWidth()
                && Double.compare(unit.getHeight(), getHeight()) == 0
                && Double.compare(unit.getWeight(), getWeight()) == 0 && getType().equals(unit.getType());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
