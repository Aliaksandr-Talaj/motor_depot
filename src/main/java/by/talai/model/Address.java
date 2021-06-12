package by.talai.model;

import java.io.Serializable;

public class Address implements Serializable {

    private int id;
    private String country;
    private String region;
    private String locality;
    private String street;
    private String building;
    private String apartment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ",\n country='" + country + '\'' +
                ",\n region='" + region + '\'' +
                ",\n locality='" + locality + '\'' +
                ",\n street='" + street + '\'' +
                ",\n building='" + building + '\'' +
                ",\n apartment='" + apartment + '\'' +
                '}';
    }
}
