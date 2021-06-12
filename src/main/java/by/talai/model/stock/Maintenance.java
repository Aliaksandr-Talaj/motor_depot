package by.talai.model.stock;

import java.io.Serializable;
import java.sql.Date;

public class Maintenance implements Serializable {

    private String type;
    private Date startTime;
    private Date finishTime;

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

    @Override
    public String toString() {
        return "Maintenance{" +
                "type='" + type + '\'' +
                ",\n startTime=" + startTime +
                ",\n finishTime=" + finishTime +
                '}';
    }
}
