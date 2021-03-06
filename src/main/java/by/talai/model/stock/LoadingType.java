package by.talai.model.stock;

import java.io.Serializable;
import java.util.Objects;

public class LoadingType implements Serializable {

    private int id;
    private String type;

    public LoadingType() {
    }

    public LoadingType(int id, String type) {
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
        return "LoadingType{" +
                "id=" + id +
                ",\n type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoadingType)) return false;
        LoadingType that = (LoadingType) o;
        return getId() == that.getId() && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
