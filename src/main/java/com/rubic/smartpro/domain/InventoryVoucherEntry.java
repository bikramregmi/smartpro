package com.rubic.smartpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A InventoryVoucherEntry.
 */
@Entity
@Table(name = "inventory_voucher_entry")
public class InventoryVoucherEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sr_item")
    private String srItem;

    @Column(name = "sr_quantity")
    private Long srQuantity;

    @Column(name = "sr_rate")
    private Double srRate;

    @Column(name = "sr_amount")
    private Double srAmount;

    @Column(name = "des_item")
    private String desItem;

    @Column(name = "des_quantity")
    private Long desQuantity;

    @Column(name = "des_rate")
    private Double desRate;

    @Column(name = "des_amount")
    private Double desAmount;

    @Column(name = "extra_field")
    private String extraField;

    @Column(name = "voucher_number")
    private String voucherNumber;

    @Column(name = "unique_key")
    private String uniqueKey;

    @ManyToOne
    @JsonIgnoreProperties(value = "inventoryVoucherEntries", allowSetters = true)
    private InventoryVoucher inventoryVoucher;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrItem() {
        return srItem;
    }

    public InventoryVoucherEntry srItem(String srItem) {
        this.srItem = srItem;
        return this;
    }

    public void setSrItem(String srItem) {
        this.srItem = srItem;
    }

    public Long getSrQuantity() {
        return srQuantity;
    }

    public InventoryVoucherEntry srQuantity(Long srQuantity) {
        this.srQuantity = srQuantity;
        return this;
    }

    public void setSrQuantity(Long srQuantity) {
        this.srQuantity = srQuantity;
    }

    public Double getSrRate() {
        return srRate;
    }

    public InventoryVoucherEntry srRate(Double srRate) {
        this.srRate = srRate;
        return this;
    }

    public void setSrRate(Double srRate) {
        this.srRate = srRate;
    }

    public Double getSrAmount() {
        return srAmount;
    }

    public InventoryVoucherEntry srAmount(Double srAmount) {
        this.srAmount = srAmount;
        return this;
    }

    public void setSrAmount(Double srAmount) {
        this.srAmount = srAmount;
    }

    public String getDesItem() {
        return desItem;
    }

    public InventoryVoucherEntry desItem(String desItem) {
        this.desItem = desItem;
        return this;
    }

    public void setDesItem(String desItem) {
        this.desItem = desItem;
    }

    public Long getDesQuantity() {
        return desQuantity;
    }

    public InventoryVoucherEntry desQuantity(Long desQuantity) {
        this.desQuantity = desQuantity;
        return this;
    }

    public void setDesQuantity(Long desQuantity) {
        this.desQuantity = desQuantity;
    }

    public Double getDesRate() {
        return desRate;
    }

    public InventoryVoucherEntry desRate(Double desRate) {
        this.desRate = desRate;
        return this;
    }

    public void setDesRate(Double desRate) {
        this.desRate = desRate;
    }

    public Double getDesAmount() {
        return desAmount;
    }

    public InventoryVoucherEntry desAmount(Double desAmount) {
        this.desAmount = desAmount;
        return this;
    }

    public void setDesAmount(Double desAmount) {
        this.desAmount = desAmount;
    }

    public String getExtraField() {
        return extraField;
    }

    public InventoryVoucherEntry extraField(String extraField) {
        this.extraField = extraField;
        return this;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public InventoryVoucher getInventoryVoucher() {
        return inventoryVoucher;
    }

    public InventoryVoucherEntry inventoryVoucher(InventoryVoucher inventoryVoucher) {
        this.inventoryVoucher = inventoryVoucher;
        return this;
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

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventoryVoucherEntry)) {
            return false;
        }
        return id != null && id.equals(((InventoryVoucherEntry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InventoryVoucherEntry{" +
            "id=" + getId() +
            ", srItem='" + getSrItem() + "'" +
            ", srQuantity=" + getSrQuantity() +
            ", srRate=" + getSrRate() +
            ", srAmount=" + getSrAmount() +
            ", desItem='" + getDesItem() + "'" +
            ", desQuantity=" + getDesQuantity() +
            ", desRate=" + getDesRate() +
            ", desAmount=" + getDesAmount() +
            ", extraField='" + getExtraField() + "'" +
            "}";
    }
}
