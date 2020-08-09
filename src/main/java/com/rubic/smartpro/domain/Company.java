package com.rubic.smartpro.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "fy", nullable = false)
    private LocalDate fy;

    @NotNull
    @Column(name = "book_date", nullable = false)
    private LocalDate bookDate;

    @Column(name = "currency_string")
    private String currencyString;

    @Column(name = "currency_symbol")
    private String currencySymbol;

    @Column(name = "currency_sub_string")
    private String currencySubString;

    @NotNull
    @Column(name = "dealer_type", nullable = false)
    private String dealerType;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "type")
    private String type;

    @Column(name = "extra_field")
    private String extraField;

    @Column(name = "extrafield_1")
    private String extrafield1;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Company companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public Company address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public Company email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFy() {
        return fy;
    }

    public Company fy(LocalDate fy) {
        this.fy = fy;
        return this;
    }

    public void setFy(LocalDate fy) {
        this.fy = fy;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    public Company bookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
        return this;
    }

    public void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }

    public String getCurrencyString() {
        return currencyString;
    }

    public Company currencyString(String currencyString) {
        this.currencyString = currencyString;
        return this;
    }

    public void setCurrencyString(String currencyString) {
        this.currencyString = currencyString;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public Company currencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        return this;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencySubString() {
        return currencySubString;
    }

    public Company currencySubString(String currencySubString) {
        this.currencySubString = currencySubString;
        return this;
    }

    public void setCurrencySubString(String currencySubString) {
        this.currencySubString = currencySubString;
    }

    public String getDealerType() {
        return dealerType;
    }

    public Company dealerType(String dealerType) {
        this.dealerType = dealerType;
        return this;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public Company taxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getType() {
        return type;
    }

    public Company type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtraField() {
        return extraField;
    }

    public Company extraField(String extraField) {
        this.extraField = extraField;
        return this;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public String getExtrafield1() {
        return extrafield1;
    }

    public Company extrafield1(String extrafield1) {
        this.extrafield1 = extrafield1;
        return this;
    }

    public void setExtrafield1(String extrafield1) {
        this.extrafield1 = extrafield1;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return id != null && id.equals(((Company) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Company{" +
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
