package by.talai.service.dto;

import by.talai.model.Delivery;

import java.util.Objects;
import java.util.Set;

public class DeliveryDto {

    private Delivery delivery;
    private Set<AutomobileLoadingDto> automobileLoadingDtoSet;


    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Set<AutomobileLoadingDto> getAutomobileLoadingDtoSet() {
        return automobileLoadingDtoSet;
    }

    public void setAutomobileLoadingDtoSet(Set<AutomobileLoadingDto> automobileLoadingDtoSet) {
        this.automobileLoadingDtoSet = automobileLoadingDtoSet;
    }

    @Override
    public String toString() {
        return "DeliveryDto{" +
                "delivery=" + delivery +
                ", automobileLoadingDtoSet=" + automobileLoadingDtoSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryDto)) return false;
        DeliveryDto that = (DeliveryDto) o;
        return getDelivery().equals(that.getDelivery()) && Objects.equals(getAutomobileLoadingDtoSet(),
                that.getAutomobileLoadingDtoSet());
    }

    @Override
    public int hashCode() {
        return getDelivery().getId();
    }
}
