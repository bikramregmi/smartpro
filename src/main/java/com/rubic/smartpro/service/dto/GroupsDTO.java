package com.rubic.smartpro.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.Groups} entity.
 */
public class GroupsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    private String group;

    private Boolean extraField1;

    private Boolean extraField2;

    private Boolean extraField3;

    private Boolean extraField4;

    private String extraField5;

    private String extraField6;

    
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean isExtraField1() {
        return extraField1;
    }

    public void setExtraField1(Boolean extraField1) {
        this.extraField1 = extraField1;
    }

    public Boolean isExtraField2() {
        return extraField2;
    }

    public void setExtraField2(Boolean extraField2) {
        this.extraField2 = extraField2;
    }

    public Boolean isExtraField3() {
        return extraField3;
    }

    public void setExtraField3(Boolean extraField3) {
        this.extraField3 = extraField3;
    }

    public Boolean isExtraField4() {
        return extraField4;
    }

    public void setExtraField4(Boolean extraField4) {
        this.extraField4 = extraField4;
    }

    public String getExtraField5() {
        return extraField5;
    }

    public void setExtraField5(String extraField5) {
        this.extraField5 = extraField5;
    }

    public String getExtraField6() {
        return extraField6;
    }

    public void setExtraField6(String extraField6) {
        this.extraField6 = extraField6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupsDTO)) {
            return false;
        }

        return id != null && id.equals(((GroupsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupsDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", group='" + getGroup() + "'" +
            ", extraField1='" + isExtraField1() + "'" +
            ", extraField2='" + isExtraField2() + "'" +
            ", extraField3='" + isExtraField3() + "'" +
            ", extraField4='" + isExtraField4() + "'" +
            ", extraField5='" + getExtraField5() + "'" +
            ", extraField6='" + getExtraField6() + "'" +
            "}";
    }
}
