package by.talai.model.stock;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Maintenance implements Serializable {

    private int id;
    private String type;
    private Date startTime;
    private Date finishTime;
    private Automobile automobile;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ",\n type='" + type + '\'' +
                ",\n startTime=" + startTime +
                ",\n finishTime=" + finishTime +
                ",\n automobileId='" + automobile + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maintenance)) return false;
        Maintenance that = (Maintenance) o;
        return getId() == that.getId() && Objects.equals(getType(),
                that.getType()) && Objects.equals(getStartTime(),
                that.getStartTime()) && Objects.equals(getFinishTime(),
                that.getFinishTime()) && Objects.equals(getAutomobile(),
                that.getAutomobile());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
