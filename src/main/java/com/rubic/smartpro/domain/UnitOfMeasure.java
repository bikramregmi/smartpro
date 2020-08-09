package com.rubic.smartpro.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A UnitOfMeasure.
 */
@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "formal_name")
    private String formalName;

    @Column(name = "type")
    private String type;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "decimal_places")
    private String decimalPlaces;

    @Column(name = "extra_field")
    private String extraField;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormalName() {
        return formalName;
    }

    public UnitOfMeasure formalName(String formalName) {
        this.formalName = formalName;
        return this;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    public String getType() {
        return type;
    }

    public UnitOfMeasure type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public UnitOfMeasure symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDecimalPlaces() {
        return decimalPlaces;
    }

    public UnitOfMeasure decimalPlaces(String decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
        return this;
    }

    public void setDecimalPlaces(String decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public String getExtraField() {
        return extraField;
    }

    public UnitOfMeasure extraField(String extraField) {
        this.extraField = extraField;
        return this;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitOfMeasure)) {
            return false;
        }
        return id != null && id.equals(((UnitOfMeasure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnitOfMeasure{" +
            "id=" + getId() +
            ", formalName='" + getFormalName() + "'" +
            ", type='" + getType() + "'" +
            ", symbol='" + getSymbol() + "'" +
            ", decimalPlaces='" + getDecimalPlaces() + "'" +
            ", extraField='" + getExtraField() + "'" +
            "}";
    }
}
