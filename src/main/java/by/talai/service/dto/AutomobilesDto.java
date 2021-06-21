package by.talai.service.dto;

import by.talai.model.stock.Automobile;

import java.util.List;
import java.util.Objects;

public class AutomobilesDto {
    private List<Automobile> automobiles;
    private Integer amount;

    public List<Automobile> getAutomobiles() {
        return automobiles;
    }

    public void setAutomobiles(List<Automobile> automobiles) {
        this.automobiles = automobiles;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AutomobilesDto{" +
                "automobiles=" + automobiles +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutomobilesDto)) return false;
        AutomobilesDto that = (AutomobilesDto) o;
        return getAutomobiles().equals(that.getAutomobiles()) && Objects.equals(getAmount(), that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAutomobiles(), getAmount());
    }
}
