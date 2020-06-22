package com.rubic.smartpro.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.rubic.smartpro.domain.Employee} entity. This class is used
 * in {@link com.rubic.smartpro.web.rest.EmployeeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /employees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmployeeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fullName;

    private StringFilter email;

    private StringFilter eCode;

    private BooleanFilter isActive;

    private LongFilter employeeSalaryId;

    private LongFilter employeeInformationId;

    public EmployeeCriteria() {
    }

    public EmployeeCriteria(EmployeeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.eCode = other.eCode == null ? null : other.eCode.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.employeeSalaryId = other.employeeSalaryId == null ? null : other.employeeSalaryId.copy();
        this.employeeInformationId = other.employeeInformationId == null ? null : other.employeeInformationId.copy();
    }

    @Override
    public EmployeeCriteria copy() {
        return new EmployeeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter geteCode() {
        return eCode;
    }

    public void seteCode(StringFilter eCode) {
        this.eCode = eCode;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public LongFilter getEmployeeSalaryId() {
        return employeeSalaryId;
    }

    public void setEmployeeSalaryId(LongFilter employeeSalaryId) {
        this.employeeSalaryId = employeeSalaryId;
    }

    public LongFilter getEmployeeInformationId() {
        return employeeInformationId;
    }

    public void setEmployeeInformationId(LongFilter employeeInformationId) {
        this.employeeInformationId = employeeInformationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmployeeCriteria that = (EmployeeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(eCode, that.eCode) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(employeeSalaryId, that.employeeSalaryId) &&
            Objects.equals(employeeInformationId, that.employeeInformationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fullName,
        email,
        eCode,
        isActive,
        employeeSalaryId,
        employeeInformationId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fullName != null ? "fullName=" + fullName + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (eCode != null ? "eCode=" + eCode + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (employeeSalaryId != null ? "employeeSalaryId=" + employeeSalaryId + ", " : "") +
                (employeeInformationId != null ? "employeeInformationId=" + employeeInformationId + ", " : "") +
            "}";
    }

}
