<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="employeeInformation">
                <h2 class="jh-entity-heading"><span v-text="$t('smartproApp.employeeInformation.detail.title')">EmployeeInformation</span> {{employeeInformation.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.dob')">Dob</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.dob}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.gender')">Gender</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.gender}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.isMarried')">Is Married</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.isMarried}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.bloodGroup')">Blood Group</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.bloodGroup}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.parentName')">Parent Name</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.parentName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.addressline1')">Addressline 1</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.addressline1}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.addressline2')">Addressline 2</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.addressline2}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.phoneNumber')">Phone Number</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.phoneNumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.mobileNumber')">Mobile Number</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.mobileNumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.emergencyContactNumber')">Emergency Contact Number</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.emergencyContactNumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.joiningDate')">Joining Date</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.joiningDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.designation')">Designation</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.designation}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.panNumber')">Pan Number</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.panNumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.documentNumber')">Document Number</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.documentNumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('smartproApp.employeeInformation.extraField')">Extra Field</span>
                    </dt>
                    <dd>
                        <span>{{employeeInformation.extraField}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="employeeInformation.id" :to="{name: 'EmployeeInformationEdit', params: {employeeInformationId: employeeInformation.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./employee-information-details.component.ts">
</script>
