package by.talai.model.stock;

import java.io.Serializable;

public class RideStatus implements Serializable {

    private int id;
    private String status;

    public RideStatus() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RideStatus{" +
                "id=" + id +
                ",\n status='" + status + '\'' +
                '}';
    }
}
