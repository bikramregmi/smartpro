package com.rubic.smartpro.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "e_code", nullable = false, unique = true)
    private String eCode;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(unique = true)
    private EmployeeSalary employeeSalary;

    @OneToOne
    @JoinColumn(unique = true)
    private EmployeeInformation employeeInformation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public Employee fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String geteCode() {
        return eCode;
    }

    public Employee eCode(String eCode) {
        this.eCode = eCode;
        return this;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Employee isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public EmployeeSalary getEmployeeSalary() {
        return employeeSalary;
    }

    public Employee employeeSalary(EmployeeSalary employeeSalary) {
        this.employeeSalary = employeeSalary;
        return this;
    }

    public void setEmployeeSalary(EmployeeSalary employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public EmployeeInformation getEmployeeInformation() {
        return employeeInformation;
    }

    public Employee employeeInformation(EmployeeInformation employeeInformation) {
        this.employeeInformation = employeeInformation;
        return this;
    }

    public void setEmployeeInformation(EmployeeInformation employeeInformation) {
        this.employeeInformation = employeeInformation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", email='" + getEmail() + "'" +
            ", eCode='" + geteCode() + "'" +
            ", isActive='" + isIsActive() + "'" +
            "}";
    }
}
