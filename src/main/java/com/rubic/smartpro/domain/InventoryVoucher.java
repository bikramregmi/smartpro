package com.rubic.smartpro.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A InventoryVoucher.
 */
@Entity
@Table(name = "inventory_voucher")
public class InventoryVoucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sr_quantity_total")
    private Long srQuantityTotal;

    @Column(name = "sr_amount_total")
    private Double srAmountTotal;

    @Column(name = "narration")
    private String narration;

    @Column(name = "des_quantity_total")
    private Long desQuantityTotal;

    @Column(name = "des_amount_total")
    private Double desAmountTotal;

    @Column(name = "extra_field")
    private String extraField;

    @Column(name="voucher_number")
    private String voucherNumber;

    @Column(name="unique_key")
    private String uniqueKey;

    @ManyToOne
    private Company company;


    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNarration() {
        return narration;
    }

    public InventoryVoucher narration(String narration) {
        this.narration = narration;
        return this;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getExtraField() {
        return extraField;
    }

    public InventoryVoucher extraField(String extraField) {
        this.extraField = extraField;
        return this;
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

    public Long getDesQuantityTotal() {
        return desQuantityTotal;
    }

    public void setDesQuantityTotal(Long desQuantityTotal) {
        this.desQuantityTotal = desQuantityTotal;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventoryVoucher)) {
            return false;
        }
        return id != null && id.equals(((InventoryVoucher) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore


    @Override
    public String toString() {
        return "InventoryVoucher{" +
            "id=" + id +
            ", srQuantityTotal=" + srQuantityTotal +
            ", srAmountTotal=" + srAmountTotal +
            ", narration='" + narration + '\'' +
            ", desAmountTotal=" + desAmountTotal +
            ", extraField='" + extraField + '\'' +
            ", voucherNumber='" + voucherNumber + '\'' +
            ", uniqueKey='" + uniqueKey + '\'' +
            '}';
    }
}
