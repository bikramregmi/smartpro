package com.rubic.smartpro.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.EmployeeSalary} entity.
 */
public class EmployeeSalaryDTO implements Serializable {

    private Long id;

    private String basicSalary;

    private String allowance;

    private String ot;

    private String bonus;

    private String description;

    private String tax;

    private String pf;

    private String extra;

    private String employeeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getAllowance() {
        return allowance;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
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
