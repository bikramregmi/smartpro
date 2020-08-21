package com.rubic.smartpro.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.SalesVoucherType} entity.
 */
public class SalesVoucherTypeDTO implements Serializable {

    private Long id;

    private String item;

    private Long quantity;

    private Double rate;

    private Double amount;

    private String extra;

    private String referenceNumber;


    private Long accountingVoucherId;

    private SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO;

    private String uniqueKey;

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Long getAccountingVoucherId() {
        return accountingVoucherId;
    }

    public void setAccountingVoucherId(Long accountingVoucherId) {
        this.accountingVoucherId = accountingVoucherId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public SalesVoucherTypeTotalDTO getSalesVoucherTypeTotalDTO() {
        return salesVoucherTypeTotalDTO;
    }

    public void setSalesVoucherTypeTotalDTO(SalesVoucherTypeTotalDTO salesVoucherTypeTotalDTO) {
        this.salesVoucherTypeTotalDTO = salesVoucherTypeTotalDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesVoucherTypeDTO)) {
            return false;
        }

        return id != null && id.equals(((SalesVoucherTypeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "SalesVoucherTypeDTO{" +
            "id=" + id +
            ", item='" + item + '\'' +
            ", quantity=" + quantity +
            ", rate=" + rate +
            ", amount=" + amount +
            ", extra='" + extra + '\'' +
            ", referenceNumber='" + referenceNumber + '\'' +
            ", accountingVoucherId=" + accountingVoucherId +
            ", salesVoucherTypeTotalDTO=" + salesVoucherTypeTotalDTO +
            '}';
    }
}
