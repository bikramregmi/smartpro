package com.rubic.smartpro.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.Company} entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;

    @NotNull
    private String companyName;

    @NotNull
    private String address;

    @NotNull
    private String email;

    private LocalDate fy;

    private LocalDate bookDate;

    private String currencyString;

    private String currencySymbol;

    private String currencySubString;

    @NotNull
    private String dealerType;

    private Double taxRate;

    private String type;

    private String extraField;

    private String extrafield1;

    private String strBookDate;

    private String strFy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFy() {
        return fy;
    }

    public void setFy(LocalDate fy) {
        this.fy = fy;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    public void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }

    public String getCurrencyString() {
        return currencyString;
    }

    public void setCurrencyString(String currencyString) {
        this.currencyString = currencyString;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencySubString() {
        return currencySubString;
    }

    public void setCurrencySubString(String currencySubString) {
        this.currencySubString = currencySubString;
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public String getExtrafield1() {
        return extrafield1;
    }

    public void setExtrafield1(String extrafield1) {
        this.extrafield1 = extrafield1;
    }

    public String getStrBookDate() {
        return strBookDate;
    }

    public void setStrBookDate(String strBookDate) {
        this.strBookDate = strBookDate;
    }

    public String getStrFy() {
        return strFy;
    }

    public void setStrFy(String strFy) {
        this.strFy = strFy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompanyDTO)) {
            return false;
        }

        return id != null && id.equals(((CompanyDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", fy='" + getFy() + "'" +
            ", bookDate='" + getBookDate() + "'" +
            ", currencyString='" + getCurrencyString() + "'" +
            ", currencySymbol='" + getCurrencySymbol() + "'" +
            ", currencySubString='" + getCurrencySubString() + "'" +
            ", dealerType='" + getDealerType() + "'" +
            ", taxRate=" + getTaxRate() +
            ", type='" + getType() + "'" +
            ", extraField='" + getExtraField() + "'" +
            ", extrafield1='" + getExtrafield1() + "'" +
            "}";
    }
}
