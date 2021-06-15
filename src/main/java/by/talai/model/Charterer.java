package by.talai.model;

import java.io.Serializable;

public class Charterer implements Serializable {

    private int id;
    private String name;
    private Address ownAddress;

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

    public Address getOwnAddress() {
        return ownAddress;
    }

    public void setOwnAddress(Address ownAddress) {
        this.ownAddress = ownAddress;
    }

    @Override
    public String toString() {
        return "Charterer{" +
                "id=" + id +
                ",\n name='" + name + '\'' +
                ",\n ownAddress='" + ownAddress + '\'' +
                '}';
    }
}
