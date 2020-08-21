package com.rubic.smartpro.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.SalesVoucherTypeTotal} entity.
 */
public class SalesVoucherTypeTotalDTO implements Serializable {
    
    private Long id;

    private String item;

    private String quantityTotal;

    private Double rateTotal;

    private Double amountTotal;

    private String referenceNumber;

    private String extraField;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(String quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Double getRateTotal() {
        return rateTotal;
    }

    public void setRateTotal(Double rateTotal) {
        this.rateTotal = rateTotal;
    }

    public Double getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Double amountTotal) {
        this.amountTotal = amountTotal;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesVoucherTypeTotalDTO)) {
            return false;
        }

        return id != null && id.equals(((SalesVoucherTypeTotalDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesVoucherTypeTotalDTO{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", quantityTotal='" + getQuantityTotal() + "'" +
            ", rateTotal=" + getRateTotal() +
            ", amountTotal=" + getAmountTotal() +
            ", referenceNumber='" + getReferenceNumber() + "'" +
            ", extraField='" + getExtraField() + "'" +
            "}";
    }
}
