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
    private long basicSalary;

    @Column(name = "allowance")
    private long allowance;

    @Column(name = "ot")
    private long ot;

    @Column(name = "bonus")
    private long bonus;

    @Column(name = "description")
    private long description;

    @Column(name = "tax")
    private long tax;

    @Column(name = "pf")
    private long pf;

    @Column(name = "extra")
    private String extra;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public long getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(long basicSalary) {
        this.basicSalary = basicSalary;
    }

    public long getAllowance() {
        return allowance;
    }

    public void setAllowance(long allowance) {
        this.allowance = allowance;
    }

    public long getOt() {
        return ot;
    }

    public void setOt(long ot) {
        this.ot = ot;
    }

    public long getBonus() {
        return bonus;
    }

    public void setBonus(long bonus) {
        this.bonus = bonus;
    }

    public long getDescription() {
        return description;
    }

    public void setDescription(long description) {
        this.description = description;
    }

    public long getTax() {
        return tax;
    }

    public void setTax(long tax) {
        this.tax = tax;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPf() {
        return pf;
    }

    public void setPf(long pf) {
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
            "id=" + id +
            ", basicSalary=" + basicSalary +
            ", allowance=" + allowance +
            ", ot=" + ot +
            ", bonus=" + bonus +
            ", description=" + description +
            ", tax=" + tax +
            ", pf=" + pf +
            ", extra='" + extra + '\'' +
            '}';
    }
}
