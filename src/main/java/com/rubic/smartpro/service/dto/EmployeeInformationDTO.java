package com.rubic.smartpro.service.dto;

import com.rubic.smartpro.domain.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rubic.smartpro.domain.EmployeeInformation} entity.
 */
public class EmployeeInformationDTO implements Serializable {

    private Long id;

    private String dob;

    private String gender;

    private Boolean isMarried;

    private String bloodGroup;

    private String parentName;

    private String addressline1;

    private String addressline2;

    private String phoneNumber;

    private String mobileNumber;

    private String emergencyContactNumber;

    private String joiningDate;

    private String designation;

    private String panNumber;

    private String documentNumber;

    private String extraField;

    private String employeeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean isIsMarried() {
        return isMarried;
    }

    public void setIsMarried(Boolean isMarried) {
        this.isMarried = isMarried;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
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
        if (!(o instanceof EmployeeInformationDTO)) {
            return false;
        }

        return id != null && id.equals(((EmployeeInformationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeInformationDTO{" +
            "id=" + getId() +
            ", dob='" + getDob() + "'" +
            ", gender='" + getGender() + "'" +
            ", isMarried='" + isIsMarried() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", parentName='" + getParentName() + "'" +
            ", addressline1='" + getAddressline1() + "'" +
            ", addressline2='" + getAddressline2() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", emergencyContactNumber='" + getEmergencyContactNumber() + "'" +
            ", joiningDate='" + getJoiningDate() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", panNumber='" + getPanNumber() + "'" +
            ", documentNumber='" + getDocumentNumber() + "'" +
            ", extraField='" + getExtraField() + "'" +
            "}";
    }
}
