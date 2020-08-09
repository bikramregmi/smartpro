package com.rubic.smartpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Ledger.
 */
@Entity
@Table(name = "ledger")
public class Ledger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mailing_name")
    private String mailingName;

    @Column(name = "mailing_address")
    private String mailingAddress;

    @Column(name = "pan_no")
    private String panNo;

    @Column(name = "extra_field_1")
    private Boolean extraField1;

    @Column(name = "extra_field_2")
    private Boolean extraField2;

    @Column(name = "extra_field_3")
    private Boolean extraField3;

    @Column(name = "extra_field_4")
    private String extraField4;

    @Column(name = "extra_field_5")
    private String extraField5;

//    @OneToMany(mappedBy = "ledger")
//    private Set<Groups> groups = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "company", allowSetters = true)
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

    public Ledger name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailingName() {
        return mailingName;
    }

    public Ledger mailingName(String mailingName) {
        this.mailingName = mailingName;
        return this;
    }

    public void setMailingName(String mailingName) {
        this.mailingName = mailingName;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public Ledger mailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
        return this;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getPanNo() {
        return panNo;
    }

    public Ledger panNo(String panNo) {
        this.panNo = panNo;
        return this;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public Boolean isExtraField1() {
        return extraField1;
    }

    public Ledger extraField1(Boolean extraField1) {
        this.extraField1 = extraField1;
        return this;
    }

    public void setExtraField1(Boolean extraField1) {
        this.extraField1 = extraField1;
    }

    public Boolean isExtraField2() {
        return extraField2;
    }

    public Ledger extraField2(Boolean extraField2) {
        this.extraField2 = extraField2;
        return this;
    }

    public void setExtraField2(Boolean extraField2) {
        this.extraField2 = extraField2;
    }

    public Boolean isExtraField3() {
        return extraField3;
    }

    public Ledger extraField3(Boolean extraField3) {
        this.extraField3 = extraField3;
        return this;
    }

    public void setExtraField3(Boolean extraField3) {
        this.extraField3 = extraField3;
    }

    public String getExtraField4() {
        return extraField4;
    }

    public Ledger extraField4(String extraField4) {
        this.extraField4 = extraField4;
        return this;
    }

    public void setExtraField4(String extraField4) {
        this.extraField4 = extraField4;
    }

    public String getExtraField5() {
        return extraField5;
    }

    public Ledger extraField5(String extraField5) {
        this.extraField5 = extraField5;
        return this;
    }

    public void setExtraField5(String extraField5) {
        this.extraField5 = extraField5;
    }
//
//    public Set<Groups> getGroups() {
//        return groups;
//    }
//
//    public Ledger groups(Set<Groups> groups) {
//        this.groups = groups;
//        return this;
//    }

  /*  public Ledger addGroups(Groups groups) {
        this.groups.add(groups);
        groups.setLedger(this);
        return this;
    }

    public Ledger removeGroups(Groups groups) {
        this.groups.remove(groups);
        groups.setLedger(null);
        return this;
    }*/

//    public void setGroups(Set<Groups> groups) {
//        this.groups = groups;
//    }

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
        if (!(o instanceof Ledger)) {
            return false;
        }
        return id != null && id.equals(((Ledger) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ledger{" +
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
