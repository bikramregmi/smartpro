package com.rubic.smartpro.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.Ledger} entity.
 */
public class LedgerDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String mailingName;

    private String mailingAddress;

    private String panNo;

    private Boolean extraField1;

    private Boolean extraField2;

    private Boolean extraField3;

    private String extraField4;

    private String extraField5;

    private String companyId;


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

    public String getMailingName() {
        return mailingName;
    }

    public void setMailingName(String mailingName) {
        this.mailingName = mailingName;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
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

    public String getExtraField4() {
        return extraField4;
    }

    public void setExtraField4(String extraField4) {
        this.extraField4 = extraField4;
    }

    public String getExtraField5() {
        return extraField5;
    }

    public void setExtraField5(String extraField5) {
        this.extraField5 = extraField5;
    }

    public Boolean getExtraField1() {
        return extraField1;
    }

    public Boolean getExtraField2() {
        return extraField2;
    }

    public Boolean getExtraField3() {
        return extraField3;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LedgerDTO)) {
            return false;
        }

        return id != null && id.equals(((LedgerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LedgerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", mailingName='" + getMailingName() + "'" +
            ", mailingAddress='" + getMailingAddress() + "'" +
            ", panNo='" + getPanNo() + "'" +
            ", extraField1='" + isExtraField1() + "'" +
            ", extraField2='" + isExtraField2() + "'" +
            ", extraField3='" + isExtraField3() + "'" +
            ", extraField4='" + getExtraField4() + "'" +
            ", extraField5='" + getExtraField5() + "'" +
            "}";
    }
}
