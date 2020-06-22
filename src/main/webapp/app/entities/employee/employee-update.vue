<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.employee.home.createOrEditLabel" v-text="$t('smartproApp.employee.home.createOrEditLabel')">Create or edit a Employee</h2>
                <div>
                    <div class="form-group" v-if="employee.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="employee.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employee.fullName')" for="employee-fullName">Full Name</label>
                        <input type="text" class="form-control" name="fullName" id="employee-fullName"
                            :class="{'valid': !$v.employee.fullName.$invalid, 'invalid': $v.employee.fullName.$invalid }" v-model="$v.employee.fullName.$model"  required/>
                        <div v-if="$v.employee.fullName.$anyDirty && $v.employee.fullName.$invalid">
                            <small class="form-text text-danger" v-if="!$v.employee.fullName.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employee.email')" for="employee-email">Email</label>
                        <input type="text" class="form-control" name="email" id="employee-email"
                            :class="{'valid': !$v.employee.email.$invalid, 'invalid': $v.employee.email.$invalid }" v-model="$v.employee.email.$model"  required/>
                        <div v-if="$v.employee.email.$anyDirty && $v.employee.email.$invalid">
                            <small class="form-text text-danger" v-if="!$v.employee.email.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employee.eCode')" for="employee-eCode">E Code</label>
                        <input type="text" class="form-control" name="eCode" id="employee-eCode"
                            :class="{'valid': !$v.employee.eCode.$invalid, 'invalid': $v.employee.eCode.$invalid }" v-model="$v.employee.eCode.$model"  required/>
                        <div v-if="$v.employee.eCode.$anyDirty && $v.employee.eCode.$invalid">
                            <small class="form-text text-danger" v-if="!$v.employee.eCode.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employee.isActive')" for="employee-isActive">Is Active</label>
                        <input type="checkbox" class="form-check" name="isActive" id="employee-isActive"
                            :class="{'valid': !$v.employee.isActive.$invalid, 'invalid': $v.employee.isActive.$invalid }" v-model="$v.employee.isActive.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employee.employeeSalary')" for="employee-employeeSalary">Employee Salary</label>
                        <select class="form-control" id="employee-employeeSalary" name="employeeSalary" v-model="employee.employeeSalaryId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="employeeSalaryOption.id" v-for="employeeSalaryOption in employeeSalaries" :key="employeeSalaryOption.id">{{employeeSalaryOption.fullName}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.employee.employeeInformation')" for="employee-employeeInformation">Employee Information</label>
                        <select class="form-control" id="employee-employeeInformation" name="employeeInformation" v-model="employee.employeeInformationId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="employeeInformationOption.id" v-for="employeeInformationOption in employeeInformations" :key="employeeInformationOption.id">{{employeeInformationOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.employee.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./employee-update.component.ts">
</script>
