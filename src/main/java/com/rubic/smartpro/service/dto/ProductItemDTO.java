package com.rubic.smartpro.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.ProductItem} entity.
 */
public class ProductItemDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long groupId;

    private String units;

    private String rate;

    private String quantityPerRate;

    private String value;

    private String extraField;

    private String group;


    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getQuantityPerRate() {
        return quantityPerRate;
    }

    public void setQuantityPerRate(String quantityPerRate) {
        this.quantityPerRate = quantityPerRate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductItemDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductItemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "ProductItemDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", groupId=" + groupId +
            ", units='" + units + '\'' +
            ", rate='" + rate + '\'' +
            ", quantityPerRate='" + quantityPerRate + '\'' +
            ", value='" + value + '\'' +
            ", extraField='" + extraField + '\'' +
            ", companyId=" + companyId +
            '}';
    }
}
