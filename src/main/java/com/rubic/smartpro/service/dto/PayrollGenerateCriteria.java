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
 * Criteria class for the {@link com.rubic.smartpro.domain.PayrollGenerate} entity. This class is used
 * in {@link com.rubic.smartpro.web.rest.PayrollGenerateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /payroll-generates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PayrollGenerateCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter employeeName;

    private StringFilter salaryMonth;

    private StringFilter description;

    private StringFilter tax;

    private StringFilter pf;

    private LongFilter employeeId;

    private LongFilter employeeSalaryId;

    public PayrollGenerateCriteria() {
    }

    public PayrollGenerateCriteria(PayrollGenerateCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.employeeName=other.employeeName== null ? null: other.employeeName.copy();
        this.salaryMonth = other.salaryMonth == null ? null : other.salaryMonth.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.tax = other.tax == null ? null : other.tax.copy();
        this.pf = other.pf == null ? null : other.pf.copy();
        this.employeeId = other.employeeId == null ? null : other.employeeId.copy();
        this.employeeSalaryId = other.employeeSalaryId == null ? null : other.employeeSalaryId.copy();
    }

    @Override
    public PayrollGenerateCriteria copy() {
        return new PayrollGenerateCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(StringFilter salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getTax() {
        return tax;
    }

    public void setTax(StringFilter tax) {
        this.tax = tax;
    }

    public StringFilter getPf() {
        return pf;
    }

    public void setPf(StringFilter pf) {
        this.pf = pf;
    }

    public LongFilter getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(LongFilter employeeId) {
        this.employeeId = employeeId;
    }

    public LongFilter getEmployeeSalaryId() {
        return employeeSalaryId;
    }

    public void setEmployeeSalaryId(LongFilter employeeSalaryId) {
        this.employeeSalaryId = employeeSalaryId;
    }

    public StringFilter getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(StringFilter employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PayrollGenerateCriteria that = (PayrollGenerateCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(employeeName, that.employeeName) &&
            Objects.equals(salaryMonth, that.salaryMonth) &&
            Objects.equals(description, that.description) &&
            Objects.equals(tax, that.tax) &&
            Objects.equals(pf, that.pf) &&
            Objects.equals(employeeId, that.employeeId) &&
            Objects.equals(employeeSalaryId, that.employeeSalaryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        employeeName,
        salaryMonth,
        description,
        tax,
        pf,
        employeeId,
        employeeSalaryId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayrollGenerateCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (employeeName!= null ? "employeeName=" + employeeName+ ", " : "")+
                (salaryMonth != null ? "salaryMonth=" + salaryMonth + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (tax != null ? "tax=" + tax + ", " : "") +
                (pf != null ? "pf=" + pf + ", " : "") +
                (employeeId != null ? "employeeId=" + employeeId + ", " : "") +
                (employeeSalaryId != null ? "employeeSalaryId=" + employeeSalaryId + ", " : "") +
            "}";
    }

}
