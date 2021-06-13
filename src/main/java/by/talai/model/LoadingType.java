package by.talai.model;

import java.io.Serializable;

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
}
