package com.rubic.smartpro.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Groups.
 */
@Entity
@Table(name = "jhi_groups")
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "jhi_group")
    private String group;

    @Column(name = "extra_field_1")
    private Boolean extraField1;

    @Column(name = "extra_field_2")
    private Boolean extraField2;

    @Column(name = "extra_field_3")
    private Boolean extraField3;

    @Column(name = "extra_field_4")
    private Boolean extraField4;

    @Column(name = "extra_field_5")
    private String extraField5;

    @Column(name = "extra_field_6")
    private String extraField6;

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

    public Groups name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public Groups group(String group) {
        this.group = group;
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean isExtraField1() {
        return extraField1;
    }

    public Groups extraField1(Boolean extraField1) {
        this.extraField1 = extraField1;
        return this;
    }

    public void setExtraField1(Boolean extraField1) {
        this.extraField1 = extraField1;
    }

    public Boolean isExtraField2() {
        return extraField2;
    }

    public Groups extraField2(Boolean extraField2) {
        this.extraField2 = extraField2;
        return this;
    }

    public void setExtraField2(Boolean extraField2) {
        this.extraField2 = extraField2;
    }

    public Boolean isExtraField3() {
        return extraField3;
    }

    public Groups extraField3(Boolean extraField3) {
        this.extraField3 = extraField3;
        return this;
    }

    public void setExtraField3(Boolean extraField3) {
        this.extraField3 = extraField3;
    }

    public Boolean isExtraField4() {
        return extraField4;
    }

    public Groups extraField4(Boolean extraField4) {
        this.extraField4 = extraField4;
        return this;
    }

    public void setExtraField4(Boolean extraField4) {
        this.extraField4 = extraField4;
    }

    public String getExtraField5() {
        return extraField5;
    }

    public Groups extraField5(String extraField5) {
        this.extraField5 = extraField5;
        return this;
    }

    public void setExtraField5(String extraField5) {
        this.extraField5 = extraField5;
    }

    public String getExtraField6() {
        return extraField6;
    }

    public Groups extraField6(String extraField6) {
        this.extraField6 = extraField6;
        return this;
    }

    public void setExtraField6(String extraField6) {
        this.extraField6 = extraField6;
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

    public Boolean getExtraField4() {
        return extraField4;
    }

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
        if (!(o instanceof Groups)) {
            return false;
        }
        return id != null && id.equals(((Groups) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Groups{" +
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
