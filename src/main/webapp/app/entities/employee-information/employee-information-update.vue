<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.employeeInformation.home.createOrEditLabel" v-text="$t('smartproApp.employeeInformation.home.createOrEditLabel')">Create or edit a EmployeeInformation</h2>
                <div>
                    <div class="form-group" v-if="employeeInformation.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="employeeInformation.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.dob')" for="employee-information-dob">Dob</label>
                        <input type="text" class="form-control" name="dob" id="employee-information-dob"
                            :class="{'valid': !$v.employeeInformation.dob.$invalid, 'invalid': $v.employeeInformation.dob.$invalid }" v-model="$v.employeeInformation.dob.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.gender')" for="employee-information-gender">Gender</label>
                        <input type="text" class="form-control" name="gender" id="employee-information-gender"
                            :class="{'valid': !$v.employeeInformation.gender.$invalid, 'invalid': $v.employeeInformation.gender.$invalid }" v-model="$v.employeeInformation.gender.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.isMarried')" for="employee-information-isMarried">Is Married</label>
                        <input type="checkbox" class="form-check" name="isMarried" id="employee-information-isMarried"
                            :class="{'valid': !$v.employeeInformation.isMarried.$invalid, 'invalid': $v.employeeInformation.isMarried.$invalid }" v-model="$v.employeeInformation.isMarried.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.bloodGroup')" for="employee-information-bloodGroup">Blood Group</label>
                        <input type="text" class="form-control" name="bloodGroup" id="employee-information-bloodGroup"
                            :class="{'valid': !$v.employeeInformation.bloodGroup.$invalid, 'invalid': $v.employeeInformation.bloodGroup.$invalid }" v-model="$v.employeeInformation.bloodGroup.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.parentName')" for="employee-information-parentName">Parent Name</label>
                        <input type="text" class="form-control" name="parentName" id="employee-information-parentName"
                            :class="{'valid': !$v.employeeInformation.parentName.$invalid, 'invalid': $v.employeeInformation.parentName.$invalid }" v-model="$v.employeeInformation.parentName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.addressline1')" for="employee-information-addressline1">Addressline 1</label>
                        <input type="text" class="form-control" name="addressline1" id="employee-information-addressline1"
                            :class="{'valid': !$v.employeeInformation.addressline1.$invalid, 'invalid': $v.employeeInformation.addressline1.$invalid }" v-model="$v.employeeInformation.addressline1.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.addressline2')" for="employee-information-addressline2">Addressline 2</label>
                        <input type="text" class="form-control" name="addressline2" id="employee-information-addressline2"
                            :class="{'valid': !$v.employeeInformation.addressline2.$invalid, 'invalid': $v.employeeInformation.addressline2.$invalid }" v-model="$v.employeeInformation.addressline2.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.phoneNumber')" for="employee-information-phoneNumber">Phone Number</label>
                        <input type="text" class="form-control" name="phoneNumber" id="employee-information-phoneNumber"
                            :class="{'valid': !$v.employeeInformation.phoneNumber.$invalid, 'invalid': $v.employeeInformation.phoneNumber.$invalid }" v-model="$v.employeeInformation.phoneNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.mobileNumber')" for="employee-information-mobileNumber">Mobile Number</label>
                        <input type="text" class="form-control" name="mobileNumber" id="employee-information-mobileNumber"
                            :class="{'valid': !$v.employeeInformation.mobileNumber.$invalid, 'invalid': $v.employeeInformation.mobileNumber.$invalid }" v-model="$v.employeeInformation.mobileNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.emergencyContactNumber')" for="employee-information-emergencyContactNumber">Emergency Contact Number</label>
                        <input type="text" class="form-control" name="emergencyContactNumber" id="employee-information-emergencyContactNumber"
                            :class="{'valid': !$v.employeeInformation.emergencyContactNumber.$invalid, 'invalid': $v.employeeInformation.emergencyContactNumber.$invalid }" v-model="$v.employeeInformation.emergencyContactNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.joiningDate')" for="employee-information-joiningDate">Joining Date</label>
                        <input type="text" class="form-control" name="joiningDate" id="employee-information-joiningDate"
                            :class="{'valid': !$v.employeeInformation.joiningDate.$invalid, 'invalid': $v.employeeInformation.joiningDate.$invalid }" v-model="$v.employeeInformation.joiningDate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.designation')" for="employee-information-designation">Designation</label>
                        <input type="text" class="form-control" name="designation" id="employee-information-designation"
                            :class="{'valid': !$v.employeeInformation.designation.$invalid, 'invalid': $v.employeeInformation.designation.$invalid }" v-model="$v.employeeInformation.designation.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.panNumber')" for="employee-information-panNumber">Pan Number</label>
                        <input type="text" class="form-control" name="panNumber" id="employee-information-panNumber"
                            :class="{'valid': !$v.employeeInformation.panNumber.$invalid, 'invalid': $v.employeeInformation.panNumber.$invalid }" v-model="$v.employeeInformation.panNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.documentNumber')" for="employee-information-documentNumber">Document Number</label>
                        <input type="text" class="form-control" name="documentNumber" id="employee-information-documentNumber"
                            :class="{'valid': !$v.employeeInformation.documentNumber.$invalid, 'invalid': $v.employeeInformation.documentNumber.$invalid }" v-model="$v.employeeInformation.documentNumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employeeInformation.extraField')" for="employee-information-extraField">Extra Field</label>
                        <input type="text" class="form-control" name="extraField" id="employee-information-extraField"
                            :class="{'valid': !$v.employeeInformation.extraField.$invalid, 'invalid': $v.employeeInformation.extraField.$invalid }" v-model="$v.employeeInformation.extraField.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.employeeInformation.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./employee-information-update.component.ts">
</script>
