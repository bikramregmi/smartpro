package com.rubic.smartpro.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.EmployeeSalary} entity.
 */
public class EmployeeSalaryDTO implements Serializable {

    private Long id;

    private long basicSalary;

    private long allowance;

    private long ot;

    private long bonus;

    private long description;

    private long tax;

    private long pf;

    private String extra;

    private String employeeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getPf() {
        return pf;
    }

    public void setPf(long pf) {
        this.pf = pf;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeSalaryDTO)) {
            return false;
        }

        return id != null && id.equals(((EmployeeSalaryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeSalaryDTO{" +
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
