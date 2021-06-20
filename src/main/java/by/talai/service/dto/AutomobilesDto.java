package by.talai.service.dto;

import by.talai.model.stock.Automobile;

import java.util.List;

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
}
