package by.talai.model.stock;

import java.io.Serializable;
import java.util.Objects;

public class FuelType implements Serializable {

    private int id;
    private String type;

    public FuelType() {
    }

    public FuelType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FuelType{" +
                "id=" + id +
                ",\n type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuelType)) return false;
        FuelType fuelType = (FuelType) o;
        return getId() == fuelType.getId() && Objects.equals(getType(), fuelType.getType());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
