<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="smartproApp.payrollGenerate.home.createOrEditLabel" v-text="$t('smartproApp.payrollGenerate.home.createOrEditLabel')">Create or edit a PayrollGenerate</h2>
                <div>
                    <div class="form-group" v-if="payrollGenerate.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="payrollGenerate.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.payrollGenerate.salaryMonth')" for="payroll-generate-salaryMonth">Salary Month</label>
                        <input type="text" class="form-control" name="salaryMonth" id="payroll-generate-salaryMonth"
                            :class="{'valid': !$v.payrollGenerate.salaryMonth.$invalid, 'invalid': $v.payrollGenerate.salaryMonth.$invalid }" v-model="$v.payrollGenerate.salaryMonth.$model"  required/>
                        <div v-if="$v.payrollGenerate.salaryMonth.$anyDirty && $v.payrollGenerate.salaryMonth.$invalid">
                            <small class="form-text text-danger" v-if="!$v.payrollGenerate.salaryMonth.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.payrollGenerate.description')" for="payroll-generate-description">Description</label>
                        <input type="text" class="form-control" name="description" id="payroll-generate-description"
                            :class="{'valid': !$v.payrollGenerate.description.$invalid, 'invalid': $v.payrollGenerate.description.$invalid }" v-model="$v.payrollGenerate.description.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.payrollGenerate.tax')" for="payroll-generate-tax">Tax</label>
                        <input type="text" class="form-control" name="tax" id="payroll-generate-tax"
                            :class="{'valid': !$v.payrollGenerate.tax.$invalid, 'invalid': $v.payrollGenerate.tax.$invalid }" v-model="$v.payrollGenerate.tax.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.payrollGenerate.pf')" for="payroll-generate-pf">Pf</label>
                        <input type="text" class="form-control" name="pf" id="payroll-generate-pf"
                            :class="{'valid': !$v.payrollGenerate.pf.$invalid, 'invalid': $v.payrollGenerate.pf.$invalid }" v-model="$v.payrollGenerate.pf.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.payrollGenerate.employee')" for="payroll-generate-employee">Employee</label>
                        <select class="form-control" id="payroll-generate-employee" name="employee" v-model="payrollGenerate.employeeId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="employeeOption.id" v-for="employeeOption in employees" :key="employeeOption.id">{{employeeOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('smartproApp.payrollGenerate.employeeSalary')" for="payroll-generate-employeeSalary">Employee Salary</label>
                        <select class="form-control" id="payroll-generate-employeeSalary" name="employeeSalary" v-model="payrollGenerate.employeeSalaryId">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="employeeSalaryOption.id" v-for="employeeSalaryOption in employeeSalaries" :key="employeeSalaryOption.id">{{employeeSalaryOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.payrollGenerate.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./payroll-generate-update.component.ts">
</script>
