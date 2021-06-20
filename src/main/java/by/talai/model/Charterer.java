package by.talai.model;

import java.io.Serializable;
import java.util.Set;

public class Charterer implements Serializable {

    private int id;
    private String name;
    private String surname;
    private Address ownAddress;

    private Set<Address> usedAddresses;

    public Set<Address> getUsedAddresses() {
        return usedAddresses;
    }

    public void setUsedAddresses(Set<Address> usedAddresses) {
        this.usedAddresses = usedAddresses;
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

    public Address getOwnAddress() {
        return ownAddress;
    }

    public void setOwnAddress(Address ownAddress) {
        this.ownAddress = ownAddress;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Charterer{" +
                "id=" + id +
                ",\n name='" + name + '\'' +
                ",\n surname='" + surname + '\'' +
                ",\n ownAddress=" + ownAddress +
                ",\n usedAddresses=" + usedAddresses +
                '}';
    }
}
