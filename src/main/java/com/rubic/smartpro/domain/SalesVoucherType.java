package com.rubic.smartpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SalesVoucherType.
 */
@Entity
@Table(name = "sales_voucher_type")
public class SalesVoucherType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "extra")
    private String extra;

    @Column(name = "uniqueKey")
    private String uniqueKey;

    @Column(name = "referenceNumber")
    private String referenceNumber;

    @ManyToOne
    @JsonIgnoreProperties(value = "salesVoucherTypes", allowSetters = true)
    private AccountingVoucher accountingVoucher;

    @ManyToOne
    @JsonIgnoreProperties(value = "salesVoucherTypes", allowSetters = true)
    private SalesVoucherTypeTotal salesVoucherTypeTotal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public SalesVoucherType item(String item) {
        this.item = item;
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Long getQuantity() {
        return quantity;
    }

    public SalesVoucherType quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Double getRate() {
        return rate;
    }

    public SalesVoucherType rate(Double rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAmount() {
        return amount;
    }

    public SalesVoucherType amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getExtra() {
        return extra;
    }

    public SalesVoucherType extra(String extra) {
        this.extra = extra;
        return this;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public AccountingVoucher getAccountingVoucher() {
        return accountingVoucher;
    }

    public SalesVoucherType accountingVoucher(AccountingVoucher accountingVoucher) {
        this.accountingVoucher = accountingVoucher;
        return this;
    }

    public void setAccountingVoucher(AccountingVoucher accountingVoucher) {
        this.accountingVoucher = accountingVoucher;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    public SalesVoucherTypeTotal getSalesVoucherTypeTotal() {
        return salesVoucherTypeTotal;
    }

    public void setSalesVoucherTypeTotal(SalesVoucherTypeTotal salesVoucherTypeTotal) {
        this.salesVoucherTypeTotal = salesVoucherTypeTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesVoucherType)) {
            return false;
        }
        return id != null && id.equals(((SalesVoucherType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesVoucherType{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", quantity=" + getQuantity() +
            ", rate=" + getRate() +
            ", amount=" + getAmount() +
            ", extra='" + getExtra() + "'" +
            "}";
    }
}
