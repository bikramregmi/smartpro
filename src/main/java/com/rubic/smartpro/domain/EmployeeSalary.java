package com.rubic.smartpro.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A EmployeeSalary.
 */
@Entity
@Table(name = "employee_salary")
public class EmployeeSalary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "basic_salary")
    private String basicSalary;

    @Column(name = "allowance")
    private String allowance;

    @Column(name = "ot")
    private String ot;

    @Column(name = "bonus")
    private String bonus;

    @Column(name = "description")
    private String description;

    @Column(name = "tax")
    private String tax;

    @Column(name = "pf")
    private String pf;

    @Column(name = "extra")
    private String extra;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public EmployeeSalary basicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
        return this;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getAllowance() {
        return allowance;
    }

    public EmployeeSalary allowance(String allowance) {
        this.allowance = allowance;
        return this;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }

    public String getOt() {
        return ot;
    }

    public EmployeeSalary ot(String ot) {
        this.ot = ot;
        return this;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getBonus() {
        return bonus;
    }

    public EmployeeSalary bonus(String bonus) {
        this.bonus = bonus;
        return this;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getDescription() {
        return description;
    }

    public EmployeeSalary description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTax() {
        return tax;
    }

    public EmployeeSalary tax(String tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getPf() {
        return pf;
    }

    public EmployeeSalary pf(String pf) {
        this.pf = pf;
        return this;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getExtra() {
        return extra;
    }

    public EmployeeSalary extra(String extra) {
        this.extra = extra;
        return this;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeSalary)) {
            return false;
        }
        return id != null && id.equals(((EmployeeSalary) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeSalary{" +
            "id=" + getId() +
            ", basicSalary='" + getBasicSalary() + "'" +
            ", allowance='" + getAllowance() + "'" +
            ", ot='" + getOt() + "'" +
            ", bonus='" + getBonus() + "'" +
            ", description='" + getDescription() + "'" +
            ", tax='" + getTax() + "'" +
            ", pf='" + getPf() + "'" +
            ", extra='" + getExtra() + "'" +
            "}";
    }
}
