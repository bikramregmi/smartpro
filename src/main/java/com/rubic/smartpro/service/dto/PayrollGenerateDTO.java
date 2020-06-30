package com.rubic.smartpro.service.dto;

import com.rubic.smartpro.domain.Employee;
import com.rubic.smartpro.domain.EmployeeSalary;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.PayrollGenerate} entity.
 */
public class PayrollGenerateDTO implements Serializable {

    private Long id;

    @NotNull
    private String salaryMonth;

    private String description;

    private String tax;

    private String pf;

    private String EmployeeName;

    private Set<Employee> employee;

    private Set<EmployeeSalary> employeeSalary;

    private String salaryTotal;

    private Long employeeId;

    private Long employeeSalaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeSalaryId() {
        return employeeSalaryId;
    }

    public void setEmployeeSalaryId(Long employeeSalaryId) {
        this.employeeSalaryId = employeeSalaryId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    public Set<EmployeeSalary> getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Set<EmployeeSalary> employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getSalaryTotal() {
        return salaryTotal;
    }

    public void setSalaryTotal(String salaryTotal) {
        this.salaryTotal = salaryTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayrollGenerateDTO)) {
            return false;
        }

        return id != null && id.equals(((PayrollGenerateDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayrollGenerateDTO{" +
            "id=" + getId() +
            ", salaryMonth='" + getSalaryMonth() + "'" +
            ", description='" + getDescription() + "'" +
            ", tax='" + getTax() + "'" +
            ", pf='" + getPf() + "'" +
            ", employeeId=" + getEmployeeId() +
            ", employeeSalaryId=" + getEmployeeSalaryId() +
            "}";
    }
}
