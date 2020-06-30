package com.rubic.smartpro.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Set;

/**
 * A PayrollGenerate.
 */
@Entity
@Table(name = "payroll_generate")
public class PayrollGenerate extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @NotNull
    @Column(name = "salary_month", nullable = false)
    private String salaryMonth;

    @Column(name = "salary_total")
    private String salaryTotal;

    @Column(name = "description")
    private String description;

    @Column(name = "tax")
    private String tax;

    @Column(name = "pf")
    private String pf;

    @ManyToMany
    @JoinTable(
            name = "payroll_employee_info",
            joinColumns = {@JoinColumn(name = "payroll_generate_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "payroll_employee_name", referencedColumnName = "full_name")})
    private Set<Employee> employee;

    @ManyToMany
    @JoinTable(
            name = "payroll_employee_salary_info",
            joinColumns = {@JoinColumn(name = "payroll_generate_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "payroll_employee_salary", referencedColumnName = "basic_salary")})
    private Set<EmployeeSalary> employeeSalary;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalaryMonth() {
        return salaryMonth;
    }

    public PayrollGenerate salaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
        return this;
    }

    public void setSalaryMonth(String salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public String getDescription() {
        return description;
    }

    public PayrollGenerate description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTax() {
        return tax;
    }

    public PayrollGenerate tax(String tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getPf() {
        return pf;
    }

    public PayrollGenerate pf(String pf) {
        this.pf = pf;
        return this;
    }

    public void setPf(String pf) {
        this.pf = pf;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayrollGenerate)) {
            return false;
        }
        return id != null && id.equals(((PayrollGenerate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayrollGenerate{" +
            "id=" + getId() +
            ", salaryMonth='" + getSalaryMonth() + "'" +
            ", description='" + getDescription() + "'" +
            ", tax='" + getTax() + "'" +
            ", pf='" + getPf() + "'" +
            "}";
    }
}
