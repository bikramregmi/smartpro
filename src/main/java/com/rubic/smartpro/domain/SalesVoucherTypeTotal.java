package com.rubic.smartpro.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A SalesVoucherTypeTotal.
 */
@Entity
@Table(name = "sales_voucher_type_total")
public class SalesVoucherTypeTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "quantity_total")
    private Long quantityTotal;

    @Column(name = "rate_total")
    private Double rateTotal;

    @Column(name = "amount_total")
    private Double amountTotal;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "extra_field")
    private String extraField;

    @Column(name = "amount_debit_total")
    private Double debitTotal;

    @Column(name = "amount_credit_total")
    private Double creditTotal;

    @OneToMany
    private Set<SalesVoucherType> salesVoucherTypes = new HashSet<>();

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

    public SalesVoucherTypeTotal item(String item) {
        this.item = item;
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(Long quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Double getRateTotal() {
        return rateTotal;
    }

    public SalesVoucherTypeTotal rateTotal(Double rateTotal) {
        this.rateTotal = rateTotal;
        return this;
    }

    public void setRateTotal(Double rateTotal) {
        this.rateTotal = rateTotal;
    }

    public Double getAmountTotal() {
        return amountTotal;
    }

    public SalesVoucherTypeTotal amountTotal(Double amountTotal) {
        this.amountTotal = amountTotal;
        return this;
    }

    public void setAmountTotal(Double amountTotal) {
        this.amountTotal = amountTotal;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public SalesVoucherTypeTotal referenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getExtraField() {
        return extraField;
    }

    public SalesVoucherTypeTotal extraField(String extraField) {
        this.extraField = extraField;
        return this;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
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

    public Set<SalesVoucherType> getSalesVoucherTypes() {
        return salesVoucherTypes;
    }

    public SalesVoucherTypeTotal salesVoucherTypes(Set<SalesVoucherType> salesVoucherTypes) {
        this.salesVoucherTypes = salesVoucherTypes;
        return this;
    }

    public SalesVoucherTypeTotal addSalesVoucherType(SalesVoucherType salesVoucherType) {
        this.salesVoucherTypes.add(salesVoucherType);
//        salesVoucherType.setSalesVoucherTypeTotal(this);
        return this;
    }

    public SalesVoucherTypeTotal removeSalesVoucherType(SalesVoucherType salesVoucherType) {
        this.salesVoucherTypes.remove(salesVoucherType);
//        salesVoucherType.setSalesVoucherTypeTotal(null);
        return this;
    }

    public void setSalesVoucherTypes(Set<SalesVoucherType> salesVoucherTypes) {
        this.salesVoucherTypes = salesVoucherTypes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalesVoucherTypeTotal)) {
            return false;
        }
        return id != null && id.equals(((SalesVoucherTypeTotal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalesVoucherTypeTotal{" +
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
