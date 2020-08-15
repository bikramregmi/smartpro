package com.rubic.smartpro.domain;


import com.rubic.smartpro.enumConstants.AccountingVoucherType;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AccountingVoucher.
 */
@Entity
@Table(name = "accounting_voucher")
public class AccountingVoucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "current_balance")
    private String currentBalance;

    @Column(name = "particulars")
    private String particulars;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "narration")
    private String narration;

    @Column(name = "total")
    private String total;

    @Column(name = "grand_total")
    private Double grandTotal;

    @Column(name= "accounting_voucher_type")
    private AccountingVoucherType AccountingVoucherType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountingVoucher accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public AccountingVoucher currentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
        return this;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getParticulars() {
        return particulars;
    }

    public AccountingVoucher particulars(String particulars) {
        this.particulars = particulars;
        return this;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public Double getAmount() {
        return amount;
    }

    public AccountingVoucher amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public AccountingVoucher narration(String narration) {
        this.narration = narration;
        return this;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getTotal() {
        return total;
    }

    public AccountingVoucher total(String total) {
        this.total = total;
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public AccountingVoucher grandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
        return this;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public AccountingVoucherType getAccountingVoucherType() {
        return AccountingVoucherType;
    }

    public void setAccountingVoucherType(AccountingVoucherType accountingVoucherType) {
        AccountingVoucherType = accountingVoucherType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountingVoucher)) {
            return false;
        }
        return id != null && id.equals(((AccountingVoucher) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountingVoucher{" +
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
