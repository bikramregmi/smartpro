package com.rubic.smartpro.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.Employee} entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String eCode;

    private Boolean isActive;

    private Long employeeSalaryId;

    private String employeeSalaryDescription;

    private Long employeeInformationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getEmployeeSalaryId() {
        return employeeSalaryId;
    }

    public void setEmployeeSalaryId(Long employeeSalaryId) {
        this.employeeSalaryId = employeeSalaryId;
    }

    public String getEmployeeSalaryDescription() {
        return employeeSalaryDescription;
    }

    public void setEmployeeSalaryDescription(String employeeSalaryDescription) {
        this.employeeSalaryDescription = employeeSalaryDescription;
    }

    public Long getEmployeeInformationId() {
        return employeeInformationId;
    }

    public void setEmployeeInformationId(Long employeeInformationId) {
        this.employeeInformationId = employeeInformationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeDTO)) {
            return false;
        }

        return id != null && id.equals(((EmployeeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", eCode='" + eCode + '\'' +
                ", isActive=" + isActive +
                ", employeeSalaryId=" + employeeSalaryId +
                ", employeeSalaryDescription='" + employeeSalaryDescription + '\'' +
                ", employeeInformationId=" + employeeInformationId +
                '}';
    }
}
