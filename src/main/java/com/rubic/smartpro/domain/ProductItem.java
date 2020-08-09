package com.rubic.smartpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ProductItem.
 */
@Entity
@Table(name = "product_item")
public class ProductItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "jhi_group")
    private String group;

    @Column(name = "units")
    private String units;

    @Column(name = "rate")
    private String rate;

    @Column(name = "quantity_per_rate")
    private String quantityPerRate;

    @Column(name = "value")
    private String value;

    @Column(name = "extra_field")
    private String extraField;

    @ManyToOne
    @JsonIgnoreProperties(value = "productItems", allowSetters = true)
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ProductItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public ProductItem group(String group) {
        this.group = group;
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUnits() {
        return units;
    }

    public ProductItem units(String units) {
        this.units = units;
        return this;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getRate() {
        return rate;
    }

    public ProductItem rate(String rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getQuantityPerRate() {
        return quantityPerRate;
    }

    public ProductItem quantityPerRate(String quantityPerRate) {
        this.quantityPerRate = quantityPerRate;
        return this;
    }

    public void setQuantityPerRate(String quantityPerRate) {
        this.quantityPerRate = quantityPerRate;
    }

    public String getValue() {
        return value;
    }

    public ProductItem value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExtraField() {
        return extraField;
    }

    public ProductItem extraField(String extraField) {
        this.extraField = extraField;
        return this;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public Company getCompany() {
        return company;
    }

    public ProductItem company(Company company) {
        this.company = company;
        return this;
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
        if (!(o instanceof ProductItem)) {
            return false;
        }
        return id != null && id.equals(((ProductItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductItem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", group='" + getGroup() + "'" +
            ", units='" + getUnits() + "'" +
            ", rate='" + getRate() + "'" +
            ", quantityPerRate='" + getQuantityPerRate() + "'" +
            ", value='" + getValue() + "'" +
            ", extraField='" + getExtraField() + "'" +
            "}";
    }
}
