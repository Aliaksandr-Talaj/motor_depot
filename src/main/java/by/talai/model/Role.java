package by.talai.model;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {

    private int id;
    private String name;

    public Role() {

    }

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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ",\n name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId() == role.getId() && getName().equals(role.getName());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
