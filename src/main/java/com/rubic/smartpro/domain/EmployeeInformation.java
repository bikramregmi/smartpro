package com.rubic.smartpro.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A EmployeeInformation.
 */
@Entity
@Table(name = "employee_information")
public class EmployeeInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dob")
    private String dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "is_married")
    private Boolean isMarried;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "addressline_1")
    private String addressline1;

    @Column(name = "addressline_2")
    private String addressline2;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "emergency_contact_number")
    private String emergencyContactNumber;

    @Column(name = "joining_date")
    private String joiningDate;

    @Column(name = "designation")
    private String designation;

    @Column(name = "pan_number")
    private String panNumber;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "extra_field")
    private String extraField;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public EmployeeInformation dob(String dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public EmployeeInformation gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean isIsMarried() {
        return isMarried;
    }

    public EmployeeInformation isMarried(Boolean isMarried) {
        this.isMarried = isMarried;
        return this;
    }

    public void setIsMarried(Boolean isMarried) {
        this.isMarried = isMarried;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public EmployeeInformation bloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getParentName() {
        return parentName;
    }

    public EmployeeInformation parentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public EmployeeInformation addressline1(String addressline1) {
        this.addressline1 = addressline1;
        return this;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public EmployeeInformation addressline2(String addressline2) {
        this.addressline2 = addressline2;
        return this;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EmployeeInformation phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public EmployeeInformation mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public EmployeeInformation emergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
        return this;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public EmployeeInformation joiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
        return this;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getDesignation() {
        return designation;
    }

    public EmployeeInformation designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public EmployeeInformation panNumber(String panNumber) {
        this.panNumber = panNumber;
        return this;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public EmployeeInformation documentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getExtraField() {
        return extraField;
    }

    public EmployeeInformation extraField(String extraField) {
        this.extraField = extraField;
        return this;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeInformation)) {
            return false;
        }
        return id != null && id.equals(((EmployeeInformation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeInformation{" +
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
