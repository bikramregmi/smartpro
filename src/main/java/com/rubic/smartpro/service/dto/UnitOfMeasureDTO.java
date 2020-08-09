package com.rubic.smartpro.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.UnitOfMeasure} entity.
 */
public class UnitOfMeasureDTO implements Serializable {
    
    private Long id;

    private String formalName;

    private String type;

    private String symbol;

    private String decimalPlaces;

    private String extraField;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormalName() {
        return formalName;
    }

    public void setFormalName(String formalName) {
        this.formalName = formalName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(String decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnitOfMeasureDTO)) {
            return false;
        }

        return id != null && id.equals(((UnitOfMeasureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnitOfMeasureDTO{" +
            "id=" + getId() +
            ", formalName='" + getFormalName() + "'" +
            ", type='" + getType() + "'" +
            ", symbol='" + getSymbol() + "'" +
            ", decimalPlaces='" + getDecimalPlaces() + "'" +
            ", extraField='" + getExtraField() + "'" +
            "}";
    }
}
