package com.rubic.smartpro.service.dto;

import com.rubic.smartpro.domain.InventoryVoucher;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.InventoryVoucherEntry} entity.
 */
public class InventoryVoucherEntryDTO implements Serializable {

    private Long id;

    private String srItem;

    private Long srQuantity;

    private Double srRate;

    private Double srAmount;

    private String desItem;

    private Long desQuantity;

    private Double desRate;

    private Double desAmount;

    private String extraField;

    private String voucherNumber;

    private InventoryVoucher inventoryVoucher;

    private String uniqueKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrItem() {
        return srItem;
    }

    public void setSrItem(String srItem) {
        this.srItem = srItem;
    }

    public Long getSrQuantity() {
        return srQuantity;
    }

    public void setSrQuantity(Long srQuantity) {
        this.srQuantity = srQuantity;
    }

    public Double getSrRate() {
        return srRate;
    }

    public void setSrRate(Double srRate) {
        this.srRate = srRate;
    }

    public Double getSrAmount() {
        return srAmount;
    }

    public void setSrAmount(Double srAmount) {
        this.srAmount = srAmount;
    }

    public String getDesItem() {
        return desItem;
    }

    public void setDesItem(String desItem) {
        this.desItem = desItem;
    }

    public Long getDesQuantity() {
        return desQuantity;
    }

    public void setDesQuantity(Long desQuantity) {
        this.desQuantity = desQuantity;
    }

    public Double getDesRate() {
        return desRate;
    }

    public void setDesRate(Double desRate) {
        this.desRate = desRate;
    }

    public Double getDesAmount() {
        return desAmount;
    }

    public void setDesAmount(Double desAmount) {
        this.desAmount = desAmount;
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public InventoryVoucher getInventoryVoucher() {
        return inventoryVoucher;
    }

    public void setInventoryVoucher(InventoryVoucher inventoryVoucher) {
        this.inventoryVoucher = inventoryVoucher;
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
        if (!(o instanceof InventoryVoucherEntryDTO)) {
            return false;
        }

        return id != null && id.equals(((InventoryVoucherEntryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "InventoryVoucherEntryDTO{" +
            "id=" + id +
            ", srItem='" + srItem + '\'' +
            ", srQuantity=" + srQuantity +
            ", srRate=" + srRate +
            ", srAmount=" + srAmount +
            ", desItem='" + desItem + '\'' +
            ", desQuantity=" + desQuantity +
            ", desRate=" + desRate +
            ", desAmount=" + desAmount +
            ", extraField='" + extraField + '\'' +
            ", voucherNumber='" + voucherNumber + '\'' +
            ", inventoryVoucher=" + inventoryVoucher +
            '}';
    }
}
