package com.rubic.smartpro.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.AccountingVoucher} entity.
 */
public class AccountingVoucherDTO implements Serializable {

    private Long id;

    @NotNull
    private String accountName;

    private String currentBalance;

    private String particulars;

    private Double amount;

    private String narration;

    private String total;

    private Double grandTotal;

    private String accountingVoucherType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getAccountingVoucherType() {
        return accountingVoucherType;
    }

    public void setAccountingVoucherType(String accountingVoucherType) {
        this.accountingVoucherType = accountingVoucherType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountingVoucherDTO)) {
            return false;
        }

        return id != null && id.equals(((AccountingVoucherDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountingVoucherDTO{" +
            "id=" + getId() +
            ", accountName='" + getAccountName() + "'" +
            ", currentBalance='" + getCurrentBalance() + "'" +
            ", particulars='" + getParticulars() + "'" +
            ", amount=" + getAmount() +
            ", narration='" + getNarration() + "'" +
            ", total='" + getTotal() + "'" +
            ", grandTotal=" + getGrandTotal() +
            "}";
    }
}
