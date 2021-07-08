package by.talai.model.stock;

import java.io.Serializable;
import java.util.Objects;

public class Equipment implements Serializable {

    private int id;
    private String name;
    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ",\n name='" + name + '\'' +
                ",\n description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return getId() == equipment.getId()
                && Objects.equals(getName(),
                equipment.getName())
                && Objects.equals(getDescription(),
                equipment.getDescription());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
