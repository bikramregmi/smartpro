package com.rubic.smartpro.domain;


import com.rubic.smartpro.enumConstants.AccountingVoucherType;
import com.rubic.smartpro.enumConstants.VoucherTypeLedger;

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

    @Column(name = "narration")
    private String narration;

    @Column(name = "debit_total")
    private Double debitTotal;

    @Column(name = "credit_total")
    private Double creditTotal;

    @Column(name = "grand_total")
    private Double grandTotal;

    @Column(name= "accounting_voucher_type")
    @Enumerated(EnumType.STRING)
    private AccountingVoucherType AccountingVoucherType;

    @Column(name= "voucher_type_ledger")
    @Enumerated(EnumType.STRING)
    private VoucherTypeLedger voucherTypeLedger;

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

    public Double getGrandTotal() {
        return grandTotal;
    }

    public AccountingVoucher grandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
        return this;
    }

    public VoucherTypeLedger getVoucherTypeLedger() {
        return voucherTypeLedger;
    }

    public void setVoucherTypeLedger(VoucherTypeLedger voucherTypeLedger) {
        this.voucherTypeLedger = voucherTypeLedger;
    }

    public Double getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(Double debitTotal) {
        this.debitTotal = debitTotal;
    }

    public Double getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(Double creditTotal) {
        this.creditTotal = creditTotal;
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
            ", narration='" + getNarration() + "'" +
            ", grandTotal=" + getGrandTotal() +
            "}";
    }
}
