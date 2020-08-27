package com.rubic.smartpro.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.InventoryVoucher} entity.
 */
public class InventoryVoucherDTO implements Serializable {

    private Long id;

    private Long srQuantityTotal;

    private Double srAmountTotal;

    private String narration;

    private Long desQuantityTotal;

    private String extraField;

    private Double desAmountTotal;

    private String voucherNumber;

    private String uniqueKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public Long getSrQuantityTotal() {
        return srQuantityTotal;
    }

    public void setSrQuantityTotal(Long srQuantityTotal) {
        this.srQuantityTotal = srQuantityTotal;
    }

    public Double getSrAmountTotal() {
        return srAmountTotal;
    }

    public void setSrAmountTotal(Double srAmountTotal) {
        this.srAmountTotal = srAmountTotal;
    }

    public Long getDesQuantityTotal() {
        return desQuantityTotal;
    }

    public void setDesQuantityTotal(Long desQuantityTotal) {
        this.desQuantityTotal = desQuantityTotal;
    }

    public Double getDesAmountTotal() {
        return desAmountTotal;
    }

    public void setDesAmountTotal(Double desAmountTotal) {
        this.desAmountTotal = desAmountTotal;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventoryVoucherDTO)) {
            return false;
        }

        return id != null && id.equals(((InventoryVoucherDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore


    @Override
    public String toString() {
        return "InventoryVoucherDTO{" +
            "id=" + id +
            ", srQuantityTotal=" + srQuantityTotal +
            ", srAmountTotal=" + srAmountTotal +
            ", narration='" + narration + '\'' +
            ", desQuantityTotal=" + desQuantityTotal +
            ", extraField='" + extraField + '\'' +
            ", desAmountTotal=" + desAmountTotal +
            ", voucherNumber='" + voucherNumber + '\'' +
            ", uniqueKey='" + uniqueKey + '\'' +
            '}';
    }
}
